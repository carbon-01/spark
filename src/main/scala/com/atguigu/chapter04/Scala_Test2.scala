package com.atguigu.chapter04

object Scala_Test2 {
  def main(args: Array[String]): Unit = {
    val list = List( "Hello World", "Hello World", "Hello Scala", "Hello Scala Hadoop" )
    //println(list.map(_.split(" ")).flatten)
    val wordToCount = list.flatMap(_.split(" ")).groupBy(word => word).map(kv => (kv._1, kv._2.length)).toList.sortBy(_._2).takeRight(3).reverse
    println(wordToCount)


    val wordList = list.flatMap(_.split(" "))
    val wordToList = wordList.groupBy(str => str)
    val wordToCountMap = wordToList.map(list => (list._1, list._2.size))
    val tuples = wordToCountMap.toList.sortBy(_._2)(Ordering.Int.reverse).take(3)
    println(tuples)
  }
}
