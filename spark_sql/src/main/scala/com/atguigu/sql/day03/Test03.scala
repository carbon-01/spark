package com.atguigu.sql.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test03")
    val scc = new StreamingContext(conf,Seconds(2))
    //设置检查点
    scc.checkpoint("./ck3")
    //获取socket中的数据
    val sourceStream: ReceiverInputDStream[String] = scc.socketTextStream("hadoop103", 9999)
    //先进行开窗，窗口长度4s，步长10s
    val windowStream: DStream[String] = sourceStream.window(Seconds(4), Seconds(10))
    //对数据进行操作
    val resultStream: DStream[(String, Int)] = windowStream.flatMap(_.split("\\W+")).map((_, 1)).reduceByKey(_ + _)
    resultStream.print(100)
    scc.start()
    scc.awaitTermination()
  }
}
