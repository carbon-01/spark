package com.atguigu.chapter04

import scala.collection.mutable

object Scala_Map {
  def main(args: Array[String]): Unit = {
    val map: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)

    val muMap: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
//    muMap.put("d",4)
//    muMap.update("a",0)
//    muMap.remove("d")
//    println(muMap)
//    println(muMap.mkString(","))
//    muMap.foreach(println)
//    val result: Option[Int] = muMap.get("a")
//    println(result)
//
//    val result02: Option[Int] = muMap.get("f")
//    println(result02)
//
//    val result03: Int = muMap.get("a").get
//    println(result03)
//
//    val result04: Int = muMap.getOrElse("f", -1)
//    println(result04)

    val keys: Iterable[String] = muMap.keys
    val values: Iterable[Int] = muMap.values
    val iterator: Iterator[String] = muMap.keysIterator
    //println(iterato
    while(iterator.hasNext){
      println(iterator.next())
    }
    val iterator03: Iterator[Int] = muMap.valuesIterator
    println(keys)
    println(values)
    println(iterator.mkString(","))
    val iterator02: Iterator[(String, Int)] = muMap.iterator
    println(iterator02.mkString(","))


  }
}
