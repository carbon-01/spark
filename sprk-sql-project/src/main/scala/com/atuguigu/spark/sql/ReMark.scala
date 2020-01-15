package com.atuguigu.spark.sql

import java.text.DecimalFormat

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, LongType, MapType, StringType, StructField, StructType}

object ReMark extends UserDefinedAggregateFunction{
  //输入数据类型--城市名称
  override def inputSchema: StructType = StructType(StructField("cityName",StringType)::Nil)
  //缓存区数据类型
  override def bufferSchema: StructType =
    StructType(StructField("map",MapType(StringType,LongType))::StructField("city_count",LongType)::Nil)
  //输出的数据类型
  override def dataType: DataType = StringType
  //如果输入数据相同，则输出相同的数据
  override def deterministic: Boolean = true
  //初始化缓存区
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = Map[String,Long]()
    buffer(1) = 0L
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if(!input.isNullAt(0)){
      buffer(0) = buffer.getMap[String,Long](0) +
        (input.getString(0) ->
        (buffer.getMap[String,Long](0).getOrElse(input.getString(0),0L) + 1L))
      buffer(1) = buffer.getLong(1) + 1L
    }
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    val map1: collection.Map[String, Long] = buffer1.getMap[String,Long](0)
    val map2: collection.Map[String, Long] = buffer2.getMap[String,Long](0)
    buffer1(0) = map2.foldLeft(map1){
      case (map,(cityName,count)) => map +
        (cityName -> (map.getOrElse(cityName,0L) + count))
    }
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)
  }
  //返回值
  override def evaluate(buffer: Row): Any = {
    val total: Long = buffer.getLong(1)
    val map: collection.Map[String,Long] = buffer.getMap[String,Long](0)
    val cityTop2: List[(String, Long)] = map.toList.sortBy(_._2)(Ordering.Long.reverse).take(2)
    val df = new DecimalFormat(".00%")
    val marks: List[Mark] = cityTop2.map {
      case (cityName, count) => Mark(cityName, count.toDouble / total)
    }
    //marks.foldLeft(1D) == (1D /: marks)
    val marks1: List[Mark] = marks :+ Mark("其他",(1D /: marks)(_-_.rate))
    val reMark: List[(String, String)] = marks1.map {
      case m: Mark => (m.cityName, df.format(m.rate))
    }
    reMark.mkString(",")
  }
}
case class Mark(cityName : String,rate : Double){
  override def toString: String = super.toString
}
