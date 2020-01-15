package com.atguigu.chapter04

import com.atguigu.chapter02.xx.User

object Scala_method02 {
  def main(args: Array[String]): Unit = {
    val list4 = List("Spark", "Scala", "Hadoop")
//    println(list4.filter(s => s.startsWith("S")))
//    println(list4.filter(_.startsWith("S")))
//
//    println(list4.groupBy(_.charAt(0)))

    val user01 = new Student()
    val user02 = new Student()
    user01.id = 1
    user02.id = 1
    user01.name = "aaa"
    user02.name = "bbb"
    val uList = List(user01, user02)
//    println(uList.sortBy(_.id))
//    println(uList.sortBy(u => u.name + u.id))
//    println(uList.sortBy(_.name)(Ordering.String.reverse))
    println(uList.sortWith(
      (left, right) => {
        if (left.id < right.id) {
          true
        } else if (left.id > right.id) {
          false
        } else {
          left.name < right.name
        }
        false
      }
    ))










  }
}
class Student {
  var id : Int = _
  var name : String = _

  override def toString: String = {
    "user("+id+", "+name+")"
  }
}
