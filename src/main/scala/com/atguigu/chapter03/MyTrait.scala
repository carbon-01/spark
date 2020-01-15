package com.atguigu.chapter03
object Scala_Trait{
  def main(args: Array[String]): Unit = {
   new User21()
  }
}
trait MyTrait {
  var num : Int = _
}
trait MyTrait02{

}
trait Trait211 {
  println("trait 211....")
}
trait Trait21 extends Trait211{
  // 特质主体
  println("trait 21....")
}
class Parent21 extends Trait211{
  println("parent...")
}
class User21 extends Parent21 with Trait21 {
  println("user...")
}
