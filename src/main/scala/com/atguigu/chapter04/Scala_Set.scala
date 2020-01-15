package com.atguigu.chapter04

import scala.collection.mutable

object Scala_Set {
  def main(args: Array[String]): Unit = {
    val set: Set[Int] = Set[Int](1, 2, 3, 4, 5)
    //println(set01)

    val muSet: mutable.Set[Int] = mutable.Set[Int](1, 2, 3, 4)
    muSet.add(5)
    println(muSet.mkString(","))

    muSet.update(6,true)
    println(muSet)
    muSet.update(6,false)
    println(muSet)
    muSet.remove(1)
    println(muSet)

    muSet.foreach(println)




  }
}
