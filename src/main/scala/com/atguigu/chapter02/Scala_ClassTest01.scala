package com.atguigu.chapter02

package computer {

  import com.atguigu.chapter02.computer.myComputer.Scala_Scall

  import scala.beans.BeanProperty

  package object Info{
    @BeanProperty
    var mid : Int = _
  }
  class Scala_ClassTest {
    @BeanProperty
    var userId : Int = _
    private var name : String = null
    private var age : Int = _
    def f1 (a : Int ) : Int = {
      age = 20
      12
    }
    def f2 = 10
    println(f2)
    def f3 {"张三"}

  }
  object Scala_ClassTest01{
    private val test = new Scala_ClassTest
    test.setUserId(11)

    def main(args: Array[String]): Unit = {

      val value = new Scala_Scall()
      value.mai()
      Info.setMid(22)
      println(Info.mid)
      println(Info.getMid)
    }
  }
  package myComputer{
    class Scala_Scall{
      def mai(): Unit = {
        Info.setMid(1)
        val test = new Scala_ClassTest()
        test.setUserId(1)
        println(test.userId)
        println(test.getUserId)
    }
  }
}

}
