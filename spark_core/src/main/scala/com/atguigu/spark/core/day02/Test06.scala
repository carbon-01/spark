package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test06 {
  def main(args: Array[String]): Unit = {
    val sc1 = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("Test05"))
    val rdd1: RDD[(Int,Int)] = sc1.parallelize((10,1) :: (10,1) :: (10,1) :: (10,1) :: (10,1) :: Nil)
    //val i: Int = rdd1.aggregate(1)(_ + _, _ + _)
    //val i: Int = rdd1.fold(1)(_ + _)
    val tuple: (Int, Int) = rdd1.reduce({
      case ((x, y), (a, b)) => (x + a, y + b)
    })
    //val i : Int = rdd1.reduce(_+_)
   // println(tuple)
    rdd1.foreachPartition(println)
    Thread.sleep(100000)
  }
}