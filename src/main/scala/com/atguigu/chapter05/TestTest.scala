package com.atguigu.chapter05

import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object TestTest {
  def main(args: Array[String]): Unit = {
    val time1 = List(
      ( "anheqiao", 1549044122, 10.0 ),
      ( "shengbeilu", 1549044122, 32.0 ),
      ( "pinganjie", 1549044122,25.0 )
    )
    val time2 = List(
      ( "anheqiao", 1549044123, 13.0 ),
      ( "shengbeilu", 1549044123, 34.0 ),
      ( "pinganjie", 1549044123,27.0 )
    )
    val time3 = List(
      ( "anheqiao", 1549130522, 14.0 ),
      ( "shengbeilu", 1549130522, 33.0 ),
      ( "pinganjie", 1549130522,26.0 )
    )
    val time4 = List(
      ( "anheqiao", 1549130523, 11.0 ),
      ( "shengbeilu", 1549130523, 32.0 ),
      ( "pinganjie", 1549130523,23.0 )
    )
    var list = List(time1,time2,time3,time4)
    val mapahq = mutable.Map[String, Double]()
    //var tuplesToTuples: ListBuffer[(String, Double)] = ListBuffer[(String, Double)]
   // tuplesToTuples.append(("a",1.2))
      for(list <- list){

      list.collect {
        case (a, t, h) => {
          if (a == "anheqiao") {
            val str = tranTimeToString(t)
            ("anheqiao"+str,h)
           // list01.append("anheqiao"+str,h)
          }else if(a == "shengbeilu"){
            val str = tranTimeToString(t)
            mapahq.put("shengbeilu"+str,h)
          }else{
            val str = tranTimeToString(t)
            mapahq.put("pinganjie"+str,h)
          }
        }
      }
    }
    println(mapahq)


  }
  def tranTimeToString(tm:Int) :String={
    val fm = new SimpleDateFormat("yyyy-MM-dd")
    val tim = fm.format(new Date(tm.toLong))
    tim
  }

}
