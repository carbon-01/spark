package com.atuguigu.spark.sql

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

object Sql0830 {
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
    spark.udf.register("city_remark",ReMark)
    //spark.sql("create database sparkpractice")
    spark.sql("use sparkpractice")
    spark.sql(
      """
        |select ci.city_id,ci.city_name,ci.area,ui.click_product_id,pi.product_name
        |from  user_visit_action ui
        |join product_info pi
        |on ui.click_product_id = pi.product_id
        |join city_info ci
        |on ci.city_id = ui.city_id
        |""".stripMargin).createOrReplaceTempView("t1")

    spark.sql(
      """
        |select area,product_name,count(*) count,city_remark(city_name) rate
        |from t1
        |group by area,product_name
        |""".stripMargin).createOrReplaceTempView("t2")
    spark.sql(
      """
        |select area,product_name,count , rate,rank()  over(partition by area order by count desc) rk
        |from t2
        |""".stripMargin).createOrReplaceTempView(viewName = "t3")
    val df: DataFrame = spark.sql(
      """
        |select area,product_name,count , rate
        |from t3 where rk <= 3
        |""".stripMargin)
    df.coalesce(numPartitions = 1)
      .write.mode(SaveMode.Overwrite)
      .saveAsTable("sql_project")

      //.show(numRows = 1000,truncate = false)
    //def show(numRows: Int): Unit = show(numRows = 20, truncate = true)
    spark.stop()
  }
}