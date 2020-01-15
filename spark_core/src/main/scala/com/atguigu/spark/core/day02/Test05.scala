package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test05 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("Test05"))
    val rdd1: RDD[Int] = sc.parallelize(10 :: 10 :: 21 :: 31 :: 41 :: Nil,2)

    val i1: Int = rdd1.aggregate(1)(_ + _, _ + _)
    println(i1)

    val ints: Array[Int] = rdd1.collect()
    val l: Long = rdd1.count()
    //    println(l)
    val arr: Array[Int] = rdd1.take(3)
    //println(arr.mkString(","))
    val i: Int = rdd1.first()
    //println(i)
    val ints1: Array[Int] = rdd1.takeOrdered(3)
    //println(ints1.mkString(","))
    val rdd2: RDD[(Int, Int)] = rdd1.map((_, 1))
    val map: collection.Map[Int, Long] = rdd2.countByKey()
    //println(map)
    //rdd1.collect().foreach(println)
    println("-----------------------")
    //rdd1.foreach(println)

  }
}
