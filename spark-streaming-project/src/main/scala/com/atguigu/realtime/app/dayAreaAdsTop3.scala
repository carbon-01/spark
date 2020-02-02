package com.atguigu.realtime.app
import com.atguigu.realtime.bean.AdsInfo
import com.atguigu.realtime.util.RedisUtil
import org.apache.spark.streaming.dstream.DStream
import org.json4s.jackson.JsonMethods
import redis.clients.jedis.Jedis
//继承模板App
object dayAreaAdsTop3 extends App {
  override def doSomething(adsInfoStream: DStream[AdsInfo]): Unit = {
    //获取每天每地区每广告的点击，进行有状态的转换，并且将点击量(每个批次的value值)进行相加
    //key=day_area_ads    value=count
    val dayAreaAdsCount: DStream[((String, String, String), Int)] = adsInfoStream.map(info => ((info.dayString, info.area, info.adsId), 1))
      .updateStateByKey((seq: Seq[Int], opt: Option[Int]) => Some(seq.sum + opt.getOrElse(0)))
    //转换格式，按照每天没地区进行分组
    val dayAreaAdsCountGroup: DStream[((String, String), Iterable[(String, Int)])] = dayAreaAdsCount.map {
      case ((day, area, ads), count) => ((day, area), (ads, count))
    }.groupByKey()
    //按照点击量进行排序，并且获取前三名
    val resultStream: DStream[((String, String), List[(String, Int)])] = dayAreaAdsCountGroup.map(it => (it._1,it._2.toList.sortBy(-_._2).take(3)))
    //将得到的结果，写到redis进行保存(输出操作，使用行动算子foreachRDD)
    resultStream.foreachRDD(rdd => {
      //按照分区写出数据 key= "dayAreaAds:" + day  field = area  value = json字符串
      rdd.foreachPartition{ it => {
        //获取redis的客户端
        val client: Jedis = RedisUtil.getJedisClient
        //遍历写出
        it.foreach{
          case ((day,area),adsCount) =>
            val key = "dayAreaAds:" + day
            val field = area
            //通过隐式转换将Scala类型转换为JValue类型的值
            import org.json4s.JsonDSL._
            val value: String = JsonMethods.compact(JsonMethods.render(adsCount))
            //将数据写入到redis
            client.hset(key,field,value)
        }
        //关闭客户端
        client.close()
      }
      }
    })
  }
}
