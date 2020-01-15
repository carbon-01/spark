package com.atguigu.chapter04

import scala.collection.mutable

object Scala_Tuple {
  def main(args: Array[String]): Unit = {
    val tuple: (Int, String, Char, Int, Float, Double) = (1, "a", 'a', 22, 3.4f, 45.0)
//    println(tuple._1)
//    println(tuple._2)

    val iterator: Iterator[Any] = tuple.productIterator
//    while(iterator.hasNext){
//      println(iterator.next())
//    }
//    for(i <- iterator){
//      println(i)
//    }
//    println(tuple.productElement(0))


   // val tuple02: ((String, Int), Int) = tuple01.->(2)
   // println(tuple02)
   val tuple10: (String, Int) = "a".->(1)
    val tuple11: (String, Int) ="b" -> 1

    val muMap: mutable.Map[String, Int] = mutable.Map(("a", 1), ("b", 2), ("c", 3))
    for(kv <- muMap){
      val value : String = kv._1
      val value02 : Int = kv._2
      println(kv._1)
      println(kv._2)
    }













  }

}
