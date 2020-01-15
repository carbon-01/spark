package com.atguigu.spark.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test02 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Double")
    val sc = new SparkContext(conf)
    val list1: Array[Int] = Array(1, 2, 3, 4, 5,6,7,8)
    val list2: Array[Int] = Array(1, 2, 3, 4, 5)
    val rdd1: RDD[Int] = sc.parallelize(list1, 2)
    val rdd2: RDD[Int] = sc.parallelize(list2, 2)
    //val rdd3: RDD[Int] = rdd1.union(rdd2)
    //val rdd3: RDD[Int] = rdd1.intersection(rdd2)
    //val rdd3: RDD[Int] = rdd2.subtract(rdd1)
   // val rdd3: RDD[(Int, Int)] = rdd1.zip(rdd2)
    //val rdd3: RDD[(Int, Long)] = rdd1.zipWithIndex()
   // val rdd3: RDD[(Int, Int)] = rdd1.zipPartitions(rdd2)((it1, it2) => {
      //it1.zip(it2)
//      it1.zipAll(it2,-1,-2)
//    })
    val rdd3: RDD[(Int, Int)] = rdd1.cartesian(rdd2)
    println(rdd3.collect().mkString(","))
  }
}
