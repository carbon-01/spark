package com.atguigu.spark.core.day02


import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf1: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test02")
    val sc: SparkContext = new SparkContext(conf1)
    val rdd1: RDD[(String, Int)] = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
    val rdd2 = rdd1.groupByKey().mapValues(_.sum)
    val rdd4: RDD[(String, (Int, Option[Int]))] = rdd1.leftOuterJoin(rdd2)
    val rdd5: RDD[(String, (Option[Int], Int))] = rdd1.rightOuterJoin(rdd2)
    rdd2.collect().foreach(println)
  }
}
