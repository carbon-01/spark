package com.atguigu.chapter

object Scala_Function2 {
  def main(args : Array[String]): Unit ={
//    def f1 : Int = {
//      println("zhangsan")
//      1
//    }
//    var f2 = f1 _
//    println(f2())
//
//    def f3( x : Int , y : Int ) : Int  = {
//      x + y
//    }
//
//    def f4(f : (Int , Int ) => Int) : Int = {
//      f(20,30)
//    }
////
////    println(f4(_+_))
//
//    println(f4(f3))

    def f11  = 10
    println(f11)
    var f1 = f11 _
    println(f1())
    def f2(f : (Int,Int) => Int): Int ={
      f(10,20)
    }
    f2((_*_))
    def f22 (a : Int , b : Int) : Int = {
      a + b
    }











  }
}
