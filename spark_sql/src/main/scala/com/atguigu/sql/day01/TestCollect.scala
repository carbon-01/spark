package com.atguigu.sql.day01

object TestCollect {
  def main(args: Array[String]): Unit = {
    var map = Map((1, 2), (2, 3))
    val map1 = Map((3, 1), (4, 2))
    val map2: Map[Int, Int] = map ++ map1
    map = map + ((1, 4))
    map = map + ((3,4))
    println(map)
  }

}
