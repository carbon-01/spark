package com.atguigu.chapter03

object Scala_Extends {
  def main(args: Array[String]): Unit = {
    new Person11()
  }
}
class User11(){
  println("1111")
  def this(name : String){
    this()
    println("2222")
  }
}
class Person11(name : String) extends User11(name){
  println("3333")
  def this(){
    this("zaneta's")
    println("4444")
  }
}