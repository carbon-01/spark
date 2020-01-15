package com.atguigu.chapter04

object Test_01 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("Hello Hadoop Hive Kafka", 4),
      ("Hello Hadoop Hive", 3),
      ("Hello Hadoop", 2),
      ("Hello", 1))
    var list02 = list.flatMap(kv => {
      val strings = kv._1.split(" ")
      val tuples = strings.map(s => (s, kv._2))
      tuples
    })
    val stringToTuples = list02.groupBy(_._1)
    val stringToInt = stringToTuples.map(kv => {
      val ints = kv._2.map(_._2)
      (kv._1, ints.sum)
    })
    println(stringToInt.toList.sortWith((left, right) => left._2 > right._2).take(3))


  }
}
