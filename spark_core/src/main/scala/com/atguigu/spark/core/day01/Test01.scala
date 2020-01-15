package com.atguigu.spark.core.day01

import javafx.stage.Stage
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.reflect.ClassTag

object Test01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[3]").setAppName("Test01")
    val sc: SparkContext = new SparkContext(conf)
   // val list: Array[Int] = Array(10, 20, 30, 40,5,6,7,5,6,7)
   val list: Array[String] = Array("aaaa", "dddd", "fff", "dddee", "abcd", "ace")
   // val rdd1: RDD[Int] = sc.parallelize(list, 2)
   val rdd1: RDD[String] = sc.makeRDD(list, 2)
    //val rdd1: RDD[Int] = sc.makeRDD(list, 4)
    //val rdd2: RDD[Int] = rdd1.map(x => x * x)
    //val rdd2: RDD[Int] = rdd1.mapPartitions(it => it.map(x => x * x))
    //val rdd2: RDD[(Int, Int)] = rdd1.mapPartitionsWithIndex((index, it) => {
//      it.map(x => (index, x))
//    })
    //val rdd2: RDD[(Int, Int)] = rdd1.mapPartitionsWithIndex((index, it) => it.map((index, _)))
    //val rdd2: RDD[Int] = rdd1.flatMap(x => Array(x, x * x))
    //过滤
    //val rdd2: RDD[Int] = rdd1.flatMap(x => if (x == 10) Array(x) else Array[Int]())
    //val rdd2: RDD[Int] = rdd1.filter(_ == 10)
//    val rdd2: RDD[Int] = rdd1.collect {
//      case x: Int if (x == 10) => x * x
//    }
    //val rdd2: RDD[Array[Int]] = rdd1.glom()
    //val rdd2: RDD[(Int, Iterable[Int])] = rdd1.groupBy(x => x % 2)

   // val rdd2: RDD[Int] = rdd1.sample(false, 0.1)
    //val rdd2: RDD[Int] = rdd1.sample(true, 1.3)
    //val rdd2: RDD[Int] = rdd1.distinct(2)
    //val rdd2: RDD[Int] = rdd1.coalesce(2,true)
    //val rdd2: RDD[Int] = rdd1.repartition(8)
    //val rdd2: RDD[Int] = rdd1.sortBy(x => x, ascending = false)
    val rdd2: RDD[String] = rdd1.sortBy(x => (x.length, x))(Ordering.Tuple2(Ordering.Int.reverse, Ordering.String), ClassTag(classOf[(Int, String)]))
    println(rdd2.collect().mkString(","))
//    val rdd3: RDD[Array[Int]] = rdd2.glom()
//    rdd3.foreach(arr => println(arr.mkString(",")))

  }

}
