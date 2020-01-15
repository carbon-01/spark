package com.atguigu.spark.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object CreateRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("CreateRDD")
    val sc: SparkContext = new SparkContext(conf)
    val arr1: Array[Int] = Array(30, 50, 70, 60, 10, 20)
    //val rdd1: RDD[Int] = sc.parallelize(arr1)
    val rdd1 = sc.makeRDD(arr1)
    rdd1.collect().foreach(println)
    sc.stop()
  }
}
