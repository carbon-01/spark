package com.atguigu.chapter05

object Test01 extends App {
  val list = List(
    ("Hello Hadoop Hive Kafka", 4),
    ("Hello Hadoop Hive", 3),
    ("Hello Hadoop", 2),
    ("Hello", 1))
  private val wordCount: List[(String, Int)] = list.flatMap(kv => {
    val words = kv._1.split(" ")
    words.map(s => (s, kv._2))
  })
  private val stringToInt: Map[String, Int] = wordCount.groupBy(_._1).map(kv => {

    (kv._1, kv._2.map(_._2).sum)
  })
  println(stringToInt.toList.sortWith((left, right) => {
    left._2 > right._2
  }).take(3))



}
