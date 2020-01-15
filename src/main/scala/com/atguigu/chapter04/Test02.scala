package com.atguigu.chapter04

object Test02 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("Hello Hadoop Hive Kafka", 4),
      ("Hello Hadoop Hive", 3),
      ("Hello Hadoop", 2),
      ("Hello", 1))
    val list02 = list.map(kv => (kv._1 + " ") * kv._2)
    val strings = list02.flatMap(s => s.split(" "))
    val stringToStrings = strings.groupBy(s => s)
    val stringToInt = stringToStrings.map(kv => (kv._1, kv._2.size))
    val tuples = stringToInt.toList.sortWith((left, right) => {
      left._2 > right._2
    }).take(3)
    println(tuples)
  }
}
