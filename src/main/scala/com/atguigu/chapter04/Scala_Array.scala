package com.atguigu.chapter04

import scala.collection.mutable.ArrayBuffer

object Scala_Array {
   def main(args: Array[String]): Unit = {
   val array = new Array[String](3);
    array(0) = "jack"
     val array2 = array.+:(4)
//    array(1) = "tom"
//    println(array)
//    //String[] array = new String[];
//    val array02: Array[Int] = Array[Int](1, 2, 3)
//    val array22  = Array.apply[Int](1,2,3)
//    println(array22.mkString(","))
//    println(array02)
//    for(s <- array02){
//      println(s)
//    }
//    array02.foreach(s => println(s))
//    array02.foreach(println(_))
//    println(array02.mkString(","))
//
//    val array03 : Array[Int] = array02 :+ 4
//    println(array03.mkString(","))
//    val array04 = 5 +: array02
//    println(array04.mkString(","))
//    val array05 = array03:+ 5
//    array05.foreach(println)

     val buffer = ArrayBuffer[Int](1, 2, 3, 4)
//     //println(buffer.mkString("-"))
//
//     //增
     buffer.append(5)
//     println(buffer.mkString(","))
//     buffer += 6
//     println(buffer)
//     //删
//     val buffer02 = buffer.drop(2)
       val buffer06 = buffer - (5)
     println(buffer06)
//     val buffer03 = buffer.dropRight(2)
//     //println(buffer02.mkString(","))
//     buffer.remove(0)
//     println(buffer.mkString(","))
//     //改
//     buffer(0) = 0
//     buffer.update(0,1)
//     buffer.insert(0,-3,-2,-1)
//     val array1 = buffer.toArray
//     val buffer1 = array.toBuffer

     val array1 = Array.ofDim[Int](4, 4)
     array1(3)(3)=3
     for( i <- array1){
       println(i.mkString(","))
     }

















  }
}
