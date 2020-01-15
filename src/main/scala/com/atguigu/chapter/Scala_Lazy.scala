package com.atguigu.chapter

object Scala_Lazy {
  def main(args: Array[String]): Unit = {
    //var result = sum(10,20)
    //println(result)
//    def f1(f : => Boolean) (op : () => Unit):Unit={
//      if(f){
//        op
//        f1(f)(op)
//      }
//    }
//    f1(0<1){println("*********")}
    def whilex(f : => Boolean)( op :  => Unit ): Unit = {
      if ( f ) {
        op
        whilex(f)(op)
      } else {

      }
    }

    whilex {1>0} {
      println("xxxx")
    }



  }
//  def sum(i : Int,j : Int) = {
//    i + j
//  }
}
