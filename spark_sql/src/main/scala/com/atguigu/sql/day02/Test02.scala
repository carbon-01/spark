package com.atguigu.sql.day02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test02 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test02")
    //设置时间间隔为2S
    val scc = new StreamingContext(conf, Seconds(2))
    //读取netcat数据
    val sourceDStream: ReceiverInputDStream[String] = {
      scc.socketTextStream(hostname="hadoop103",port=9999)
    }
    //处理数据
    val wordCount: DStream[(String, Int)] = {
      sourceDStream.flatMap(_.split("\\W+")).map((_, 1)).reduceByKey(_ + _)
    }
    //打印结果
    wordCount.print(1000)
    //启动DStream流处理
    scc.start()
    //阻塞main线程
    scc.awaitTermination()


  }
}
