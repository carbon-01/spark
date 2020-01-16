package com.atguigu.sql.day03

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test08 {
  def createSSC()={
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test08")
    val ssc = new StreamingContext(conf, Seconds(3))
    //保存本次消费后的offerSet到checkpoint目录中
    ssc.checkpoint("./ck1")
    val paraments: Map[String, String] = Map[String, String](
      ConsumerConfig.GROUP_ID_CONFIG -> "0830", //指定消费者组
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092" //指定连接的kafka服务器
    )
    KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](
      ssc,
      paraments,
      Set("topic_stream01","topic_stream02")
    ).map(_._2).flatMap(_.split("\\W+").map((_,1))).reduceByKey(_+_).print(100)
    ssc
  }

  def main(args: Array[String]): Unit = {
    //从checkpoint中读取上次消费后offerSet的位置
    val ssc: StreamingContext = StreamingContext.getActiveOrCreate("./ck1", createSSC)
    ssc.start()
    ssc.awaitTermination()
  }
}
