package com.atguigu.sql.day03

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test01 {
  def createSSC() = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("test01")
    val ssc = new StreamingContext(conf, Seconds(5))
    //保存每次启动后的offerset
    ssc.checkpoint("./ck1")
    KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc,
      Map[String, String](
        "group.id" -> "0830",
        "bootstrap.servers" -> "hadoop102:9092"),
      Set[String]("topic_stream01","topic_stream02")
    ).flatMap {
      case (_, v) => v.split("\\W+").map((_, 1))
    }.reduceByKey(_+_).print(1000)
    ssc
  }

  def main(args: Array[String]): Unit = {
    val ssc: StreamingContext = StreamingContext.getActiveOrCreate("./ck1", createSSC)
    ssc.start()
    ssc.awaitTermination()
  }
}
