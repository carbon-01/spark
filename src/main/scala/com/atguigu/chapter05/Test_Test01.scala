package com.atguigu.chapter05

import java.text.SimpleDateFormat
import java.util.Date

import scala.collection.immutable

object Test_Test01 extends App {
  val time1 = List(
    ("anheqiao", 1549044122, 10.0),
    ("shengbeilu", 1549044122, 32.0),
    ("pinganjie", 1549044122, 25.0)
  )
  val time2 = List(
    ("anheqiao", 1549044123, 13.0),
    ("shengbeilu", 1549044123, 34.0),
    ("pinganjie", 1549044123, 27.0)
  )
  val time3 = List(
    ("anheqiao", 1549130522, 14.0),
    ("shengbeilu", 1549130522, 33.0),
    ("pinganjie", 1549130522, 26.0)
  )
  val time4 = List(
    ("anheqiao", 1549130523, 11.0),
    ("shengbeilu", 1549130523, 32.0),
    ("pinganjie", 1549130523, 23.0)
  )

  val list = List(time1, time2, time3, time4)
  private val dataList: List[(String, Int, Double)] = list.flatMap(list => list)
  private val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
  private val tuples: List[(String, Double)] = dataList.map(kv => {
    (kv._1 + "_" + dateFormat.format(new Date(kv._2 * 1000)), kv._3)
  })
  dataList.map {
    case (add, time, hight) => (add + "_" + dateFormat.format(new Date(time * 1000)), hight)
  }
  private val stringToTuples: Map[String, List[(String, Double)]] = tuples.groupBy(_._1)
  private val stringToDouble: Map[String, Double] = stringToTuples.mapValues(kv => {
    kv.map(_._2).sum / kv.size
  })
  //println(stringToDouble)
  private val result: immutable.Iterable[(String, String, Double)] = stringToDouble.map {
    case (addAndtime, higtSum) => {
      val strings = addAndtime.split("_")
      (strings(0), strings(1), higtSum)
    }
  }
  println(result.groupBy(_._1))
}
