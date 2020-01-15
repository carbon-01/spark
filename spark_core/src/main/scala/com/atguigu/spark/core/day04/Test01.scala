package com.atguigu.spark.core.day04

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

object Test01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test01")
    val sc = new SparkContext(conf)
    val rdd1: RDD[Int] = sc.parallelize(Array(10, 20, 30, 40, 50), 2)
    //    val acc = new MapAcc
    //    sc.register(acc,"MyAccFirst")
    //    rdd1.map(x => {
    //      acc.add(x)
    //      x
    //    }).collect()
    //    println(acc.value)
    val set = Set(10, 20)
    val bc: Broadcast[Set[Int]] = sc.broadcast(set)
    val result: Array[Int] = rdd1.filter(bc.value.contains(_)).collect()
    println(result.mkString(","))
    println(bc.value)
  }
}
