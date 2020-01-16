package com.atguigu.sql.day03

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object Test07 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test07")
    val scc = new StreamingContext(conf,Seconds(4))
    val sourceStream: ReceiverInputDStream[String] = scc.socketTextStream("hadoop103", 9999)
    val spark: SparkSession = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()
    import spark.implicits._
    val properties = new Properties()
    properties.put("user","root")
    properties.put("password","rootroot")
    sourceStream.flatMap(_.split("\\W+").map((_, 1))).reduceByKey(_ + _)
      .foreachRDD(rdd => {
        rdd.toDF("word","count")
          .write.mode("append")
          .jdbc(url = "jdbc:mysql://hadoop103:3306/mydb",table = "t3",connectionProperties = properties)
      })
    scc.start()
    scc.awaitTermination()
  }
}
