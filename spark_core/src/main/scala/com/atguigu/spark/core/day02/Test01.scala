package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test01 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Test01"))
    val lineRDD: RDD[String] = sc.textFile("e:/agent.log")
    val result: RDD[(String, List[(String, Int)])] = lineRDD.map(x => {
      val words: Array[String] = x.split(" ")
      ((words(1), (words(4))), 1)
    }).reduceByKey(_ + _).map {
      case ((pro, ads), count) => (pro, (ads, count))
    }.groupByKey().map {
      case (pro, it) => (pro, it.toList.sortBy(_._2)(Ordering.Int.reverse).take(3))
    }.sortBy(_._1.toInt)
    result.collect().foreach(println)
  }
}
