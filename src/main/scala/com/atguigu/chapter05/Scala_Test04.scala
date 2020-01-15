package com.atguigu.chapter05

import scala.collection.mutable

object Scala_Test04 {
  def main(args: Array[String]): Unit = {
    def test(num : Double , r : Double) : Double = {
      if(num == 1){
        r
      }else{
        test(num - 1,num * r)
      }
    }

   // println(test(100, 1))

    def test1( num : Long ): Long = {
                  if ( num <= 1 ) {
                      1
                  } else {
                      num * test1(num-1)
                  }
              }

    //println(test1(1000000000000000L))
    val queue = new mutable.Queue[String]()
    val unit = queue.enqueue("a", "b", "c")
    println(queue.dequeue())
    println(queue.dequeue())

    (0 to 99).map{ case _ => Thread.currentThread().getName() }
    val strings = (0 to 99).par.map {
      case _ => Thread.currentThread().getName()
    }
    println(strings)












  }
}
