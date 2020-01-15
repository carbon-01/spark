package com.atguigu.chapter03

object Scala_Tri {
  def main(args: Array[String]): Unit = {
    //new User444().f1()
  }
}
trait user111 extends user333{
  override def f1()={
    println("1111")
    super.f1()
  }
}
trait user222  extends user333 {
    override def f1()={
    println("2222")
   super.f1()
  }
}
trait user333 {
  def f1()={
    println("3333")
  }
}
class User444 extends user333 with user222 with user111{
  override def f1(): Unit ={
    println("4444")
    super.f1()
  }
}
  trait user666 {
    def f2 = {
      println("6666")
    }
  }
    trait user777 extends user666 {
      override def f2 = {
        println("7777")
      }
    }

    class Person555 extends user666 with user777 {
      override def f2 = {
        println("55555")
        super[user666].f2
      }
    }
object Person005{
  def main(args: Array[String]): Unit = {
    new Person555().f2
  }
}
