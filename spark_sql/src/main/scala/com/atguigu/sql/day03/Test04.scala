package com.atguigu.sql.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test04 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test04")
    val ssc = new StreamingContext(conf, Seconds(5))
    val sourceStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop103", 9999)
    sourceStream.transform{
      rdd => rdd.flatMap(_.split("\\W+").map((_,1))).reduceByKey(_+_)
    }.print(100)
    ssc.start()
    ssc.awaitTermination()
  }
}
