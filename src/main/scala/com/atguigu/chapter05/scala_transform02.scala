package com.atguigu.chapter05

import sun.security.util.Password

object scala_transform02 {
  def main(args: Array[String]): Unit = {
    def past(implicit password: String = "000000"): Unit ={
      println(s"password=$password")
    }
    implicit val passwoed : String = "111111"
    past() //000000
    past //111111
  }
}
