package com.atguigu.spark.core.day01.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Test02 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test02")
    val sc = new SparkContext(conf)
    val rdd = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
    //    val rdd2: RDD[(String, (Int, Int))] = rdd.aggregateByKey((Int.MinValue, Int.MaxValue))({
    //      case ((max, min), v) => (max.max(v), min.min(v))
    //    }, {
    //      case ((max1, min1), (max2, min2)) => ((max1 + max2), (min1 + min2))
    //    })
//    val rdd2: RDD[(String, Double)] = rdd.aggregateByKey((0, 0))({
//      case ((sum, count), value) => (sum + value, count + 1)
//    }, {
//      case ((sum1, count1), (sum2, count2)) => (sum1 + sum2, count1 + count2)
//    }).map(kv => (kv._1, kv._2._1.toDouble / kv._2._2))
    val rdd2: RDD[(String, Double)] = rdd.aggregateByKey((0, 0))({
      case ((sum, count), value) => (sum + value, count + 1)
    }, {
      case ((sum1, count1), (sum2, count2)) => (sum1 + sum2, count1 + count2)
    }).map{
      case (k,(sum,count)) => (k,sum.toDouble / count)
    }
//    var a  = {
//      case b : Int => println(s"b:$b")
//    }
//    a(10)
    rdd2.collect.foreach(println)
  }

}
