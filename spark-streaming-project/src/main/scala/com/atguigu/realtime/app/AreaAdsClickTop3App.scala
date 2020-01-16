package com.atguigu.realtime.app

import com.atguigu.realtime.bean.AdsInfo
import com.atguigu.realtime.util.RedisUtil
import org.apache.spark.streaming.dstream.DStream
import org.json4s.jackson.JsonMethods
import redis.clients.jedis.Jedis

object AreaAdsClickTop3App extends App {
  override def doSomething(adsInfoStream: DStream[AdsInfo]): Unit = {
    //每天每地区的点击量
    val dayAreaAdsCount: DStream[((String, String, String), Int)] = adsInfoStream.transform(rdd => {
      rdd.map(cityInfo =>
        ((cityInfo.dayString, cityInfo.area, cityInfo.adsId), 1))
    }).updateStateByKey((seq: Seq[Int], opt: Option[Int]) =>
      Some(seq.sum + opt.getOrElse(0)))
    //每天每地区
    val dayAreaCount: DStream[((String, String), Iterable[(String, Int)])] = dayAreaAdsCount.map {
      case ((day, area, adsId), count) => ((day, area), (adsId, count))
    }.groupByKey()
    //Top3
    val resultStream: DStream[((String, String), List[(String, Int)])] = dayAreaCount.map(it => (it._1, it._2.toList.sortBy(-_._2).take(3)))

    resultStream.foreachRDD(rdd => {

      rdd.foreachPartition(it => {
        import org.json4s.JsonDSL._
        val client: Jedis = RedisUtil.getJedisClient
        it.map {
          case ((day, area), list) =>
            val key: String = "day_area_ads:" + day
            val filed: String = area
            val value: String = JsonMethods.compact(JsonMethods.render(list))
            client.hset(key, filed, value)
        }
        client.close()
      })




    })
  }
}
