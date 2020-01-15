package com.atguigu.chapter03

object Scala_Equals {
  def main(args: Array[String]): Unit = {
    val d1 = new Dog().id = 1
    val d2 = new Dog().id = 1
    println(d1.equals(d2))

    println(classOf[Dog])
    println(new Dog().getClass)

    println(Dog2.RED)
  }
}
class Dog{
  var id : Int = _
  override def equals(o: Any): Boolean = {
      if (o.isInstanceOf[Dog]) {
        val other = o.asInstanceOf[Dog]
        this.id == other.id
      }else{
        false
      }
  }
}
object Dog2 extends Enumeration{
  val RED = Value("red")
  val BLUE = Value("blue")
}

object MyApp extends App{
  println("hello word")
  println("nice to meet you")
}
