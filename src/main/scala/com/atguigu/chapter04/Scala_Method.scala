package com.atguigu.chapter04

import scala.collection.JavaConverters._

object Scala_Method {
  def main(args: Array[String]): Unit = {
   // 集合 - 常用方法
    val list01 = List(1,2,3,4)

    println(list01.groupBy(_ % 2 == 0))





    val list02 = List(5, 6, 7, 8)
    val list03 = List(list01,list02)
//    println(list03)
//    println(list03.flatten)

    //将数据先乘以2，在扁平化
//    println(list03.flatten.map(_ * 2))
//
//    println(list03.map(list => list.map(_ * 2)).flatten)
//
//    println(list03.flatMap(_.map(_ * 2)))

//
//    // 功能
//            println(list.sum)
//            println(list.max)
//            println(list.min)
//            println(list.product) // 乘积
//
//    // 功能 - 简单
//    // TODO 是否包含指定元素
//    println(list.contains("2"))
//
//    // TODO 反转
//    println(list.reverse)
//
//    // TODO 取数据
  //  println(list.take(3))
//
//    // TODO 从右边开始取数据
//    println(list.takeRight(2))
//
//    // TODO 去重
//    println(list.distinct)
//
//    // TODO 根据条件采集数据
    //println(list.collect( ))

//
  //  println(list.map(_ * 2))
//    //        list.flatten
//    //        list.flatMap()
//
//    //        list.filter()
//    //        list.groupBy()
//    //        list.sortWith()
//    //        list.sortBy()
//
//
//    // 多元数据操作
//    //        list.sliding()
//    //        list.union()
//    //        list.intersect()
//    //        list.diff()
//    //        list.sliding()
//    //        list.zip()
//
//
//    // 计算
//    //        list.reduce()
//    //        list.reduceLeft()
//    //        list.reduceRight()
//    //        list.fold()
//    //        list.foldLeft()
//    //        list.foldRight()
//    //        list.scan()
//    //        list.scanLeft()
//    //        list.scanRight()
//
  }
}
