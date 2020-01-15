package com.atguigu.spark.core.day04

import org.apache.spark.util.AccumulatorV2

class MapAcc extends AccumulatorV2[Long,Map[String,Double]]{
  //返回sum，count，avg的集合
  private var map = Map[String,Double]()
  //判断初始值是否为空
  override def isZero: Boolean = map.isEmpty
  //各分区获取累加器对象
  override def copy(): AccumulatorV2[Long, Map[String, Double]] = {
    val acc = new MapAcc
    acc.map = map
    acc
  }
  //分区重置初始值
  override def reset(): Unit = {
    map = Map[String,Double]()
  }
  //各分区累加数据到
  override def add(v: Long): Unit = {
    map += "sum" -> (map.getOrElse("sum",0D) + v)
    map += "count" -> (map.getOrElse("count",0D) + 1)
  }
  //合并各个分区的数据
  override def merge(other: AccumulatorV2[Long, Map[String, Double]]): Unit = other match{
    case o : MapAcc => {
      map += "sum" -> (o.map.getOrElse("sum",0D) + this.map.getOrElse("sum",0D))
      map += "count" -> (o.map.getOrElse("count",0D) + this.map.getOrElse("count",0D))
    }
  }
  //返回统计的结果
  override def value: Map[String, Double] = {
    map += "avg" -> map.getOrElse("sum",0D)/map.getOrElse("count",1D)
    map
  }
}
