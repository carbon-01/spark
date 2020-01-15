package com.atguigu.spark.core.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Map {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("CreateRDD")
    val sc: SparkContext = new SparkContext(conf)
    val arr1: Array[Int] = Array(30, 50, 70, 60, 10, 20,100,40,80,90,110,120,130)
    val rdd1: RDD[Int] = sc.parallelize(arr1,2)
    //println(rdd1.map(x => x * x).collect().mkString(","))
    //println(rdd1.mapPartitions(iterator => iterator.map(x => x * x)).collect().mkString(","))
    //rdd1.mapPartitionsWithIndex((index,it) => it.map((index,_))).collect().foreach(println)
    //val tuples: Array[(Int, Int)] = rdd1.mapPartitionsWithIndex((index, it) => it.map((index, _))).collect()
    //println(tuples.mkString(","))
    //println(rdd1.flatMap(x => Array(x, x * x, x * x * x)).collect.mkString(","))
    //println(rdd1.filter(_ > 30).collect.mkString(","))
    rdd1.glom().collect.foreach(x => println(x.mkString(",")))
    sc.stop()
  }
}
