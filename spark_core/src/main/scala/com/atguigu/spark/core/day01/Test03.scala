package com.atguigu.spark.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.ClassTag

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test03")
    val sc: SparkContext = new SparkContext(conf)
    val list: Array[String] = Array("aaa", "abc", "addd", "dddddff", "fff", "dfv")
    val rdd1: RDD[String] = sc.parallelize(list, 3)
    val rdd2: RDD[String] = rdd1.sortBy(s => (s.length, s))(Ordering.Tuple2(Ordering.Int.reverse, Ordering.String), ClassTag(classOf[(Int, String)]))
    println(rdd2.collect.mkString(","))

  }
}
