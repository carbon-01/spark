package com.atguigu.realtime.app

import com.atguigu.realtime.bean.AdsInfo
import com.atguigu.realtime.util.RedisUtil
import org.apache.spark.streaming.{Minutes, Seconds}
import org.apache.spark.streaming.dstream.DStream
import org.json4s.jackson.JsonMethods
import redis.clients.jedis.Jedis

object lastHourAdsClick extends App {
  override def doSomething(adsInfoStream: DStream[AdsInfo]): Unit = {
    //设置窗口为1H，滑动步长为10S
    val windowStream: DStream[AdsInfo] = adsInfoStream.window(Minutes(60), Seconds(10))
    //得到窗口时间内的每个广告，每分钟的点击量
    val adsHMCount: DStream[((String, String), Int)] = windowStream.map(info => ((info.adsId, info.hmString), 1)).reduceByKey(_ + _)
    //转换格式，将近1h内的数据，按照每个广告进行分组
    val adsHMGroup: DStream[(String, Iterable[(String, Int)])] = adsHMCount.map {
      case ((ads, hm), count) => (ads, (hm, count))
    }.groupByKey()
    //输出结果到redis
    adsHMGroup.foreachRDD(rdd => {
      //隐式转换将Scala的类型转换为JValue的类型
      import org.json4s.JsonDSL._
      //隐式转换，将Java和Scala的集合进行转换
      import scala.collection.JavaConversions._
      //按照分区进行写出
      rdd.foreachPartition(it => {
        //获取redis的客户端
        val client: Jedis = RedisUtil.getJedisClient
        //定义key为"last:ads:hour:"
        val key = "last:ads:hour:"
        //将iterator类型转换为Scala的map类型
        //如果不转换的话，直接使用迭代器类型判断是否为空，数据已经被使用，当进行转换操作时，数据已经为空
        val map1: Map[String, Iterable[(String, Int)]] = it.toMap
        //判断map是否为空，不判断就会报错
        // JedisDataException: ERR wrong number of arguments for 'hmset' command
        if (map1.nonEmpty) {
          val map: Map[String, String] = map1.map {
            case (ads, iterator) => {
              //将同一个ads的所有结果封装到map中
              (ads, JsonMethods.compact(JsonMethods.render(iterator)))
            }
          }
          //按批写入数据到redis
          client.hmset(key, map)
        }
        //关闭redis的客户端
        client.close()
    })
  }
  )
}
}
