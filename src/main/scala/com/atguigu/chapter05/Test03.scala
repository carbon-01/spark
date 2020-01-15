package com.atguigu.chapter05

import scala.collection.mutable


object Test03 {
  def main(args: Array[String]): Unit = {

    var name = "zhangsan"
    var age = 18
    printf("name = %s age = %d \n",name,age)
    println(s"name = $name,age = $age")
    println(
      s"""
        |${name}
        |$age
        |""".stripMargin)

    println(5 / 2)



    val map1 = mutable.Map( ("a", 1), ("b", 2), ("c", 3) )
    val map2 = mutable.Map( ("a", 4), ("d", 5), ("c", 6) )
    println(map1.foldLeft(map2)((map, kv) => {
      map.update(kv._1, map.getOrElse(kv._1, 0) + kv._2)
      map
    }))
    map1.foldLeft(map2)((map,kv) =>{
      map.update(kv._1,map.getOrElse(kv._1,0)+kv._2)
      map
    })
    mutable.Map((1,"a"),(2,"b"))


    var list = List(1,2,3,4)
    println(list.foldLeft("")((s, i) => {
      s + i
    }))

    def f(i : Int)(j : Int)(f : (Int,Int) => Int)= {
      f(i,j)
    }

  }
}
