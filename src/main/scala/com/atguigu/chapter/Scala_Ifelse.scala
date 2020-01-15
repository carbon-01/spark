package com.atguigu.chapter

import scala.util.control.Breaks._

object Scala_Ifelse {
  def main(args : Array[String]) : Unit = {
    var flag = true
    //if(flag)
    //  println("true")
    //println("false")

//    var i = if(flag){
//      20
//      200  //flag=true;return 200
//    }else {
//      return "flase"  //flag = false;return flase;
//    }
//    var a = if(flag) 20 else 30
//    for (i <- 1 to 3){
//      println(i)
//    }
//
//    for (a : Int <- 1 until 3) println(a)
//
//    for(b <- 1 to 5 by 2) println(s"b:$b")
//    for(c <- 1 to 5 if c != 2) println(s"c:$c")

//    for(i <- 1 to 3;j <- 1 to 3)
//      println(s"i:$i",s"j:$j")
//
//    for{
//      a <- 1 to 3
//      b = a + 1
//    }{
//      print(s"a:$a",s"b:$b ")
//    }
   var result = for(a <- 1 to 3 reverse) yield a
    println(result)
//    for(i <- 1 to 17 by 2;j = (18-i)/2 ){
//      println(" "*j + "*"*i)
//    }

    breakable{
      for(i <- 1 to 5){
        if(i == 3){
          break
        }else{
         // println(i)
        }
      }
    }
    //println("执行完毕！")












  }
}
