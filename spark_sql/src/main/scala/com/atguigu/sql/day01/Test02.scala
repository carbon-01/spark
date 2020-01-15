package com.atguigu.sql.day01

import org.apache.spark.sql.{DataFrame, SparkSession}

object Test02 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().master("local[*]")
      .appName("Test02").getOrCreate()
    val df: DataFrame = spark.read.json("e:/user.json")
    df.createOrReplaceTempView("user")
    spark.udf.register("my_sum",new MySum)
    spark.sql("select my_sum(age) from user").show()
    spark.stop()
  }
}
