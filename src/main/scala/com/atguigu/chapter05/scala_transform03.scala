package com.atguigu.chapter05

object scala_transform03 {
  def main(args: Array[String]): Unit = {
    implicit def test(i : Double ): Int ={
      i.toInt
    }
    implicit def test01(i : Double) : Float={
      i.toFloat
    }
    var i : Float = 2.2
    println(i) //2

  }
}
