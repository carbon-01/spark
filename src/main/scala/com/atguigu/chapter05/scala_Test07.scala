package com.atguigu.chapter05

object scala_Test07 {
  def main(args: Array[String]): Unit = {
    def f = () => {
      println("xxxxx")
    }
    def f1(a: => Unit): Unit = {
        println(a)
    }
    f1(f())
    println(User20.name)
  }
}
object User20 {
  var name: String = "zhangsan"
}

//（2）伴生对象对应的类称之为伴生类，伴生对象的名称应该和伴生类名一致。
class User20 {
  var age: Int = 18
}

