package com.atguigu.chapter05

import scala.beans.BeanProperty

object scala_Test06 {
  def main (args: Array[String]): Unit = {
    //    val a = new String ()
    //    var b = 1
    //    val c = 1
    //    val user = new User("zhangsan")
    //    //user.age = 18;
    //
    //    println(a)
    //    println(b)
    //  }
    //  class User(name : String){
    //    val age : Int = 10;
    //    var gender = "x"
    //  }
    //
    //  var x : Double = 10.0
    //  var y = 3
    //  println((10.0 / 3).formatted("%.2f"))

    def test2() ={

      def test3(name:String):Unit={
        println("函数可以嵌套定义")
      }
      test3 _
    }

    test2()("")

  }
}
