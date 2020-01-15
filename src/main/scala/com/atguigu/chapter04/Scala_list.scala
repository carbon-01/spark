package com.atguigu.chapter04

import scala.collection.mutable.ListBuffer

object Scala_list {
  def main(args: Array[String]): Unit = {
    //new List()
//    val list : List[Int] = List(1, 2, 3, 4)
////    val list04 = list.updated(3, 5)
//    println(list)
//    val list02 = list :+ 5
//    println(list02)
////    val lsit03 = 0 +: list
//    val list03 = 0 +: list
//    println(list03)
//
//    val list04 = -3 :: -2 :: -1 :: 0 :: list
//    println(list04)
//    val list1 = 1 :: 2 :: 3 :: 4 :: List()
//    val list2: List[Int] = 1 :: 2 :: 3 :: 4 :: Nil
//
//    val list3 = list1.+:(list)
//
//    val list05 = list ::: list
//    println(list05)
//    val list06 = list :: list
//    println(list06)

    val buffer: ListBuffer[Int] = ListBuffer(1, 2, 3, 4)
    buffer.append(5,6,7,8)
    buffer.insert(8,9,10)
    buffer(0)=0
    val buffer02 = buffer.updated(0, 0)
    println(buffer)

    val buffer03 = buffer.drop(3)
    val buffer04 = buffer.dropRight(3)
    println(buffer03.mkString(","))
    buffer.remove(0,1)
    println(buffer.mkString(","))

  }

}
