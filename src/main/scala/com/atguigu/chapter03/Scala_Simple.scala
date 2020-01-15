package com.atguigu.chapter03

object Scala_Simple{
  def main (args: Array[String] ): Unit = {
    val user00 = User100.apply("jack")
    println(user00)
    //可以省略apply
    val user11 = User100("tom")
    println(user11)
    //com.atguigu.chapter03.User100@4ec6a292
    //不可以省略括号，否则就会生成半生类的对象
    val user22 = User100
    println(user22)
    //com.atguigu.chapter03.User100$@ea4a92b

    println(new Person100().name)
  }
}
object User100{
  def apply(name : String): User100 = new User100()
}
class User100 private (){

}

private class Person100{
    var name : String = "tony"
}