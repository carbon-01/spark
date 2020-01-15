package com.atguigu.chapter

import scala.io.StdIn

object Scala11_TestUnitType {
 def main(args : Array[String]){
////    for ( i <- new Range(1,18,2) ; j = (18 - i) / 2){
////      println (" " * j + "*" * i)
////    }
//    var name : String = "zhangsan"
//    var age = 18
//    println("{\"username\":"+name+ " \"age\":"+ age + "\"}\"")
//    println(s"name:$name",s"age:$age")
//    println(s"name:${name.toUpperCase}",s"age:$age")
//
//    println(
//      s"""
//        |name:$name
//        |age:$age
//        |""".stripMargin)
//        var str = StdIn.readLine("请输入数值：")
//          println(str)
        var str1 : String = "abc"
        var str2 : String = new String("abc")
   println(str1==str2) //true
   println( str1.eq(str2))  //false
   var a : Int = 2
   var b : Int = 2
   println(a==b)

















  }
}
