package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test04 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("test4")
    val sc = new SparkContext(conf)
    val rdd1: RDD[(Int, String)] = sc.parallelize((1, "a") :: (3, "c") ::(2, "b") ::Nil)
    val rdd2: RDD[(Int, String)] = sc.parallelize((1, "aa") :: (2, "bb") :: (4, "dd") :: Nil)
    val rdd3: RDD[(Int, (String, String))] = rdd1.join(rdd2)
    val rdd4: RDD[(Int, (String, Option[String]))] = rdd1.leftOuterJoin(rdd2)
    val rdd5: RDD[(Int, (Option[String], String))] = rdd1.rightOuterJoin(rdd2)
    val rdd6: RDD[(Int, (Option[String], Option[String]))] = rdd1.fullOuterJoin(rdd2)
    val rdd7: RDD[(Int, (Iterable[String], Iterable[String]))] = rdd1.cogroup(rdd2)
    rdd7.collect().foreach(println)
  }
}
