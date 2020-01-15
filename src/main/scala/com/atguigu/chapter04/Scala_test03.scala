package com.atguigu.chapter04

import scala.collection.mutable

object Scala_test03 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("Hello Hadoop Hive Kafka", 4),
      ("Hello Hadoop Hive", 3),
      ("Hello Hadoop", 2),
      ("Hello", 1)
    )
    val strings1 = list.map(kv => {
      (kv._1+" ") * kv._2
    })
    println("strings1"+strings1)
    val strings2 = strings1.flatMap(kv => kv.split(" "))
    println(strings2)
    val stringToStrings = strings2.groupBy(s => s)
    val stringToInt = stringToStrings.map(kv => (kv._1, kv._2.size))
    println(stringToInt)

    println(stringToInt.toList.sortWith((left, right) => {
      left._2 > right._2
    }).take(3))

//    val tuples = list.flatMap(kv => {
//      val strings = kv._1.split(" ")
//      //println(strings)
//      strings.map(word => (word, kv._2))
//    })
//    val stringToTuples = tuples.groupBy(kv => kv._1)
//    //println(stringToTuples)
//    val values: Iterable[List[(String, Int)]] = stringToTuples.values
//    println(values)


  }
}
