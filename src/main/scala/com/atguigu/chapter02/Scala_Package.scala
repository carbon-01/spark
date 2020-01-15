package com.atguigu.chapter02
import java.util

import scala.util.control.Breaks._
import java.util.{List,Set,Map,_}
import java.util.Date
import java.sql.{Date=>_,Array=>_,_}

import java.util.{HashMap=>JavaHashMap}

package object Name{
  private var name : String = "zhangsan"
  var mid : Int = _

  def test()={
    val date = new Date
    println("date" + date)
    val map = new JavaHashMap[Int,S]()
    map.put(1,"zhangsan")
    map.put(2,"lisi" )
    var hMap  = new util.HashMap()

    type S = String

  }
}
package xx {

  import scala.beans.BeanProperty

  class User{
    @BeanProperty
    var id : Int = 18

    def test()={
      println("test...........")
    }
    private [chapter02] var name : String = "zhangsan"
    protected val age : Int = 18
  }
  package yy{
    object Scala_Package {
      def main(args: Array[String]): Unit = {
        new User().test()
        new User().name;
        Name.test();
        val user = new User()
        user.setId(20)
        println(user.getId())
        println(f2(10))
      }
      def f2() = {
        10
      }
      def f2(i : Int )= {
        20
      }

    }

  }
}
