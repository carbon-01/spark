package com.atguigu.spark.core

import org.apache.spark.{SparkConf, SparkContext}

object Hello {
  def main(args: Array[String]): Unit = {
    //1.创建SparkContext
    val conf = new SparkConf().setAppName("hello")
    val sc = new SparkContext(conf)
    //2.创建RDD并进行操作
    val value = sc.textFile("hdfs://hadoop102:9000/input").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    //3.执行一个行动算子
    val result = value.collect()
    result.foreach(println)
    //4.关闭SparkContext
    sc.stop()



  }
}
