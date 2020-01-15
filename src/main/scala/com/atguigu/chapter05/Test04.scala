package com.atguigu.chapter05

object Test04 {
  def main(args: Array[String]): Unit = {
    var list = List(1,2,3,4)
    //println(list.init)
   // println(list.sortBy(i => i)(Ordering.Int.reverse))
    def f9 = (x:String)=>{println("wusong")}

    def f10(f:String=>Unit) = {
      f("")
    }

    f10(f9)
    println(f10((x:String)=>{println("wusong")}))

  }
}
