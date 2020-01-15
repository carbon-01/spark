package com.atguigu.chapter02;

object  Scala_View01 {
    def main(args: Array[String]): Unit = {
        lazy val result = sum(10 , 20)
        println(10 + 20)
        println (result)
        f2(sum(10,20))
        println(f4(f3(10)))
    }
    def sum(i: Int, i1: Int):Int={
        println("****************")
        i + i1
    }
    def f2 (a : Int ): Unit ={
        println(s"a:$a")
    }
    def f1(f : (Int , Int) => Int) = {
        f(20,30)
    }
    def f3(a : Int) : (Int) => Int = (int) => {
        if (a > 10 ){
            println("you are old")
            10
        }else {
            println("young")
            10
        }
    }
    def f4(f : (Int) => Int) = {
        f(1)
    }
}
