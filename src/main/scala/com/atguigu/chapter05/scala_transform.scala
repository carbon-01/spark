package com.atguigu.chapter05

object scala_transform {
  def main(args: Array[String]): Unit = {
    val user = new User()
    user.init
    user.update
  }
  class User(){
    def init: Unit = {
      println("init ......")
    }

  }
  implicit class SubUser(u : User){
    def update{
      println("update......")
    }

  }
}
