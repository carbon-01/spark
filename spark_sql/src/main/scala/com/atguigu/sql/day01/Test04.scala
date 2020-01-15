package com.atguigu.sql.day01

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, IntegerType, LongType, StructField, StructType}

object Test04{
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("Test04")
      .getOrCreate()
    val df: DataFrame = spark.read.json("e:/user.json")
      .filter(row => !(row.isNullAt(0) || row.isNullAt(1)))
    df.createOrReplaceTempView("user")
    spark.udf.register("my_sum",MySumAvg)
    spark.sql("select my_sum(age) from user" ).show()
    spark.stop()
  }
}
object MySumAvg extends UserDefinedAggregateFunction{
  // 输入数据的数据类型
  override def inputSchema: StructType = StructType(StructField("age",LongType)::Nil)
  //缓冲区的数据类型
  override def bufferSchema: StructType = StructType(StructField("sum",LongType)::StructField("count",LongType)::Nil)
  //最终输出的数据类型
  override def dataType: DataType = StructType(StructField("sum",LongType)::StructField("avg",LongType)::Nil)
  //相同的输入是否应该有相同的输出
  override def deterministic: Boolean = true
  //初始化缓冲区
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L
    buffer(1) = 0L
  }
  //分区内聚合数据
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    //判断传入的值是否为空
    if(!input.isNullAt(0)) {
      //将buffer中的数据进行更新
      buffer(0) = buffer.getLong(0) + input.getLong(0)
      buffer(1) = (buffer.getLong(1) + 1)
    }
  }
  //分区间的合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    //判断传入的分区的值是否为空，如果初始化缓存区，则不需要判断
    if(!(buffer2.isNullAt(0)&&buffer2.isNullAt(1))) {
      buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
      buffer1(1) = (buffer1.getLong(1) + buffer2.getLong(1))
    }
  }
  //返回最终的数值
  override def evaluate(buffer: Row): Any = {
    println(buffer(0))
    println(buffer(1))
    Row( buffer.getLong(0),buffer.getLong(0) / buffer.getLong(1))
  }

}
