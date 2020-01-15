package com.atguigu.sql.day02

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object Test01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test01")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //val sourceStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop103", 9999)
    //val wordCount: DStream[(String, Int)] = sourceStream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    //wordCount.print(1000)
    val queue: mutable.Queue[RDD[Int]] = mutable.Queue[RDD[Int]]()
    val rddStream: InputDStream[Int] = ssc.queueStream(queue, oneAtATime = false)
    rddStream.reduce(_+_).print()
    ssc.start()
    while (true){
      val value: RDD[Int] = ssc.sparkContext.parallelize(1 to 10000)
      queue.enqueue(value)
      Thread.sleep(1000)
      println(queue.length)
    }
    ssc.awaitTermination() //阻塞方法，阻塞main线程

  }

}
