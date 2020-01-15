package com.atguigu.chapter04

object Scala_test10 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("Hello Hadoop Hive Kafka", 4),
      ("Hello Hadoop Hive", 3),
      ("Hello Hadoop", 2),
      ("Hello", 1)
    )
    val tuples = list.flatMap(kv => {
      val words = kv._1.split(" ")
      (words.map(str => (str,kv._2)))
    })
    val stringToTuples = tuples.groupBy(kv => kv._1)
    stringToTuples.mapValues(kv => {
      kv.map(kv => kv._2)
    })
    println(stringToTuples)
    val iterable = stringToTuples.map(kv => {
      kv._2.map(_._2)
    })
    println(iterable)

  }
}
