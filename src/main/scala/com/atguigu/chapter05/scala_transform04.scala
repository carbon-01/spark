package com.atguigu.chapter05

object scala_transform04 {
  def main(args: Array[String]): Unit = {
   def test(implicit i : Int){ println(i)}
    List()

    val b = implicitly[Int]
    test(b)
  }
  implicit var a = 1
}
