package com.atguigu.sql.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object Test05 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test05")
    val ssc = new StreamingContext(conf, Seconds(6))
    //设置检查点保存数据到本地磁盘，注意：如果修改代码，则必须重新设置目录
    ssc.checkpoint(directory = "ck8")
    val sourceStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop103", 9999)
    val wordCount: DStream[(String, Int)] = sourceStream.flatMap(_.split("\\W+").map((_, 1)))
    wordCount.updateStateByKey((seq: Seq[Int], opt: Option[Int]) => {
      Some(seq.sum + opt.getOrElse(0))
    }).print(100)
    ssc.start()
    ssc.awaitTermination()
  }
}
