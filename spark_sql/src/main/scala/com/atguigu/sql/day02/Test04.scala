package com.atguigu.sql.day02


import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test04 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test04")
    val ssc = new StreamingContext(conf, Seconds(10))
    val params: Map[String, String] = Map[String, String](
      ConsumerConfig.GROUP_ID_CONFIG -> "0830", //指定消费者组
      "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092" //指定连接的kafka服务器
    )
    KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](
      ssc,
      params,
      Set("topic_stream01","topic_stream02")
    ).print(1000)
    ssc.start()
    ssc.awaitTermination()
  }
}
