package com.atguigu.chapter

object Scala_Function04 {
  def main(args: Array[String]): Unit = {
//    def f1(i : Int) : Int = i + 10
//    def f2 (a : Int) : Unit = println(a)
//    f2(f1(10))
    def f1 (f :() => Int) = {
      println("test")
      println(f())
    }
    def f2 (a : Int) =()=> {
      if(a > 10){
        println("max")
        10
      }else{
        println("min")
        0
      }
    }
    f1(f2(10))


  }
}
