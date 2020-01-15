package com.atguigu.sql.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test03")
    val scc = new StreamingContext(conf,Seconds(2))
    scc.checkpoint("./ck3")

    val sourceStream: ReceiverInputDStream[String] = scc.socketTextStream("hadoop103", 9999)
    val windowStream: DStream[String] = sourceStream.window(Seconds(4), Seconds(10))
    val resultStream: DStream[(String, Int)] = windowStream.flatMap(_.split("\\W+")).map((_, 1)).reduceByKey(_ + _)
    resultStream.print(100)
    scc.start()
    scc.awaitTermination()
  }
}
