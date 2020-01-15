package com.atguigu.chapter03

object Scala_Abstract {
  def main(args: Array[String]): Unit = {
    println("100000000000")
    new Person002().f()

    new User002 {
      override def test(): Int = {
        345
      }

      override var age: Int = _
    }
  }
}
abstract class User002{
  val name : String = "jack"
  def test(): Int
  var age : Int
  def f()={
    println("1221")
  }
}
class Person002 extends User002{
  override val name : String = "tom"
  var age : Int = 20
  override def test(): Int = {

    12345
  }

  override def f()= {
    println("4554")
  }
}
