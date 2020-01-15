package com.atguigu.chapter05

object Test02 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("Hello Hadoop Hive Kafka", 4),
      ("Hello Hadoop Hive", 3),
      ("Hello Hadoop", 2),
      ("Hello", 1))
    val strings: List[String] = list.map(kv => {
      (kv._1 + " ") * kv._2
    })
    val strings1: List[String] = strings.flatMap(s => {
      s.split(" ")
    })
    val stringToStrings = strings1.groupBy(s => s)
    val stringToInts = stringToStrings.map(kv => {
      (kv._1, kv._2.size)
    })
    println(stringToInts.toList.sortWith((left, right) => left._2 > right._2).take(3))

  }
}
