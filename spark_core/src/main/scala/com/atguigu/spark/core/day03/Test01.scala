package com.atguigu.spark.core.day03

object Test01 {
//  def main(args: Array[String]): Unit = {
//   println(max(1, 2)(Ordering.Int))
//    println(max(1,2))
//  }
//  def max[T:Ordering](a : T,b : T) : T = {
//    val ord: Ordering[T] = implicitly[Ordering[T]]
//    ord.max(a,b)
//  }
//  def max[T](a: T, b: T)(implicit ord: Ordering[T]): T = {
//     ord.max(a,b)
//  }

//  def max[T <: Comparable[T]](a: T, b: T): T = {
//    if (a.compareTo(b) >= 0) a else b
//  }
  implicit var ord : Ordering[User] = new Ordering[User] {
    override def compare(x: User, y: User): Int = x.age - y.age
}
  def main(args: Array[String]): Unit = {
    println(max(User("a", 10), User("b", 11)))
  }
  def max[T : Ordering](user1 : T,user2 : T) : T = {
    val ord: Ordering[T] = implicitly[Ordering[T]]
    //ord.max(user1,user2)
    if(ord.compare(user1,user2) >= 0) user1 else user2
  }
}
case class User(name : String,age : Int)

