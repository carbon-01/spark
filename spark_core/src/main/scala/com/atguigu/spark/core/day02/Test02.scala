package com.atguigu.spark.core.day02

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Test02 {
  implicit val ord : Ordering[User] = new Ordering[User] {
    override def compare(x: User, y: User): Int = x.age - y.age
  }
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test02")
    val sc: SparkContext = new SparkContext(conf)
    //    val list: Array[Int] = Array(20,70,60,10,30,40,50)
    //    val rdd1: RDD[(Int, Int)] = sc.parallelize(list.map((_,1)), 2)
    //    val rdd2: RDD[(Int, Int)] = rdd1.partitionBy(new HashPartitioner(3))
    //    val rdd3: RDD[Array[(Int, Int)]] = rdd2.glom()
    //    rdd3.collect().foreach(_.foreach(println))
    //val rdd1: RDD[(String, Int)] = sc.parallelize(List(("female", 1), ("male", 5), ("female", 5), ("male", 2)))
   // val rdd1 = sc.parallelize(List(("a", 3), ("a", 2), ("c", 4), ("b", 3), ("c", 6), ("c", 8)), 2)
    //val rdd2: RDD[(String, Int)] = rdd1.reduceByKey(_ + _)
    //val rdd2: RDD[(String, Iterable[Int])] = rdd1.groupByKey()
    //    val rdd2: RDD[(String, Iterable[Int])] = rdd1.groupBy(_._1).map {
    //      case (gender, kv) => (gender, kv.map(_._2))
    //    }

    //    val a : PartialFunction[Int , Unit] = {
    //      case b : Int => println(b + 1)
    //    }
    //    a(10)
    //  val rdd2: RDD[(String, Int)] = rdd1.aggregateByKey(Int.MinValue)(_.max(_), _ + _)
//    val rdd2: RDD[(String, (Int, Int))] = rdd1.aggregateByKey((Int.MinValue, Int.MaxValue))({
//      case ((max, min), v) => (max.max(v), min.min(v))
//    }, {
//      case ((max1, min1), (max2, min2)) => (max1 + max2, min1 + min2)
//    })
//    val rdd2: RDD[(String, (Int, Int))] = rdd1.aggregateByKey((0, 0))({
//      case ((sum, count), v) => (sum + v, count + 1)
//    }, {
//      case ((sum1, count1), (sum2, count2)) => (sum1 + sum2, count1 + count2)
//    })
   // val rdd2: RDD[(String, Int)] = rdd1.foldByKey(0)(_ + _)
    //val rdd2: RDD[(String, Int)] = rdd1.combineByKey(x => x, (_: Int) + _, (_: Int) * _)
    //val rdd2: RDD[(String, Int)] = rdd1.sortByKey()
    val rdd1: RDD[User] = sc.parallelize(User("A", 18) :: User("B", 19) :: User("C", 20) :: User("D", 21) :: Nil,2)
    val rdd2: RDD[User] = rdd1.map((_, 1)).sortByKey(false).map(_._1)
    rdd2.collect().foreach(println)
    sc.stop()

  }

}
case class User(name : String,age : Int)
