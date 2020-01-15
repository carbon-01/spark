package com.atguigu.chapter04

import scala.collection.mutable

object Scala_Method03 {
  def main(args: Array[String]): Unit = {
   val list1 = List(1,2,3,4)

    val array: Array[Any] = Array(1, 2, "a")
    println(list1.foldRight(5)(_ - _))
    list1.foldLeft(0)(_-_)
//    val list2 = List(4,5,6,7,8)
//    val list3 = List(1,2,3,4,5,6,7,8)
//    println(list2.union(list3))
//    println(list2.intersect(list3))
//    println(list2.diff(list3))
//    println(list3.diff(list2))
//    println(list1.zip(list2))
//    println(list2.zip(list3))
//    list3.sliding(3, 2).foreach(println)
//    println("----------------")
//    list3.sliding(3).foreach(println)

//    val list1 = List(1,2,3,4,5)
//    println(list1.scan(0)(_ - _))
//    println(list1.scanLeft(10)(_ - _))
//    println(list1.scanRight(20)(_ - _))

    //    println(list1.fold(0)(_-_))
   println(list1.foldLeft("")(_ + _))
//    println(list1.foldRight(20)(_ - _))




//    (((1,2),3),4)
//    (1,(2,(3,4)))
//    println(list1.reduce((x, y) => x + y))
//    println(list1.reduce(_ - _))
//    println(list1.reduceLeft(_ - _))
//    println(list1.reduceRight(_ - _))

    val map1 = mutable.Map( ("a", 1), ("b", 2), ("c", 3) )
    val map2 = mutable.Map( ("a", 4), ("d", 5), ("c", 6) )
    val stringToInt = map1.foldLeft(map2)((map, kv) => {
      val i = map.getOrElse(kv._1, 0)
      println(i)
      map.update(kv._1, i + kv._2)
      map
    })
    println(stringToInt)

















  }
}
