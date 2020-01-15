package com.atguigu.sql.day01

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, StructField, StructType}

import scala.collection.immutable.Nil

class MySum extends UserDefinedAggregateFunction{
  override def inputSchema: StructType = StructType(StructField("a",DoubleType)::Nil)

  override def bufferSchema: StructType = StructType(StructField("sum",DoubleType)::Nil)

  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = buffer(0) = 0D

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    val value: Double = input.getDouble(0)
    buffer(0) = buffer.getDouble(0) + value
  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    if(!buffer2.isNullAt(0)){
      buffer1(0) = buffer1.getDouble(0) + buffer2.getDouble(0)
    }
  }

  override def evaluate(buffer: Row): Any = buffer(0)
}
