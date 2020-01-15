package com.atguigu.chapter

import java.util.Date

import sun.text.UCompactIntArray

object Scala_Function3 {
  def main(args: Array[String]): Unit = {
//    def f1 ():(String) => Unit = {
//      def f11( s : String) = {
//        println(s + "F11")
//      }
//      f11 _
//    }
//    f1()("println=>")
//
//    def f22(s : String) { println(s + "F22")}
//    var f2 = f22 _
//    f2("println=>")

//    def f1(i : Int) ={
//      def f2 (j : Int) ={
//        def f3 (f: (Int ,Int) => Int)  ={
//          f(i,j)
//        }
//        f3 _
//      }s
//      f2 _
//    }
//    println(f1(10)(20)(_+_))

//    def f1(i : Int)(j : Int)(f : (Int,Int) => Int) = {
//        f(i,j)
//    }
//    println(f1(10)(20)(_*_))
//      def f1(i : Int) : Int  ={
//        if( i == 1){
//         i
//       }else{
//         i * f1 (i - 1)
//       }
//      }
//    println(f1(5))
    lazy val resu = sum (a = 10, b = 20)
    println("-----------------")
    println(resu)
    def sum (a : Int , b : Int ) :Int ={
      println( "sum.......")
      return a + b
    }
//    def f(i : Int) = {
//      def f1() = {
//        i + 10
//      }
//      f1 _
//    }
//    println(f(10)())


















  }
}
