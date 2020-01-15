package com.atguigu.chapter05

object Test05 {
  def main(args: Array[String]): Unit = {
    val person = new Person() with User{
      override def run: Unit = super.run
    }
    person.run
  }
}
trait User{
    def run{
      println("run .......")
    }
}
class Person {

}
