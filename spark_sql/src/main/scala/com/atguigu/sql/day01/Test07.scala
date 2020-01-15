package com.atguigu.sql.day01

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Test07 {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("Hive")
      .enableHiveSupport()  // 支持外置hive
      //指定仓库的存放目录(否则就会存放在spark的内置hivespark-warehouse)
      .config("spark.sql.warehouse.dir", "hdfs://hadoop102:9000/spark_hive")
      //使用save()写数据时，默认使用的时spark内置hive的snappy-parquet格式
      .config("spark.sql.hive.convertMetastoreParquet", false)
      .getOrCreate()
    //spark.sql("select * from gmall.ads_back_count").show(100)
    spark.udf.register("my_sum",(s : String) => s.toUpperCase())
    val df: DataFrame = spark.sql("select * from gmall.ads_back_count")
    //自动创建表，不能重复执行
    //df.write.saveAsTable("spark_0830")
    //向表中追加数据
    //df.write.mode(SaveMode.Append).saveAsTable("spark_0830")
    //向表中插入数据
//

    df.createOrReplaceTempView("tmp")
//    spark.sql(
//      """
//        |insert overwrite table spark_0830
//        |select * from tmp
//        |""".stripMargin)
//    spark.sql("use db1")
//    spark.sql(
//      """
//        |create table test1(name string ,age int)
//        |""".stripMargin)
    spark.sql("create database db2")
    //spark.sql("select * from spark_0830").show()



  }
}
