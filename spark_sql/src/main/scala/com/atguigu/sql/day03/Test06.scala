package com.atguigu.sql.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test06 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test06")
    val scc = new StreamingContext(conf,Seconds(6))
    val sourceStream: ReceiverInputDStream[String] = scc.socketTextStream("hadoop103", 9999)
    sourceStream.flatMap(_.split("\\W+").map((_,1))).reduceByKey(_+_)
      .saveAsTextFiles("log")
    scc.start()
    scc.awaitTermination()
  }
}
