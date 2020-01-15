package com.atguigu.chapter04

object Scala_Test {
  def main(args: Array[String]): Unit = {
    val list = List( "Hello World", "Hello World", "Hello Scala", "Hello Scala Hadoop" )
    val s1: List[String] = list.flatMap(_.split(" "))
    println(s1)
    val s2 = s1.groupBy(word => word)
    println(s2)
    val s3 = s2.map(kv => (kv._1, kv._2.size))
    println(s3)
    println(s3.toList)
    val s4 = s3.toList.sortWith(
      (left, right) => {
        if (left._2 > right._2) {
          true
        } else {
          false
        }
      }
    ).take(3)
    println(s4)
  }
}
