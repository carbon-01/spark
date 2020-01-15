package com.atguigu.sql.day01

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}

object Test06 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("Test05")
      .getOrCreate()

    //    val df1: DataFrame = spark.read.format("json").load("e:/user.json")
    //    df1.write.format("json").mode("override").save("e:/")
    //    val df = spark.read.format("jdbc")
    //      .option("url", "jdbc:mysql://192.168.24.100:3306/mydb")
    //      .option("user", "root")
    //      .option("password", "rootroot")
    //      .option("dbtable", "t2")
    //      .load()
    //    df.show()
//    val url = "jdbc:mysql://192.168.24.100:3306/mydb"
//    val properties = new Properties()
//    properties.put("user", "root")
//    properties.setProperty("password", "rootroot")
//    val df: DataFrame = spark.read.jdbc(url, "t2", properties)
//    df.show()
    import spark.implicits._
    val df: DataFrame = Seq((20,"ls"), (15,"zs")).toDF("id","name")
//    df.write.format("jdbc").mode("append")
//        .option("url","jdbc:mysql://hadoop103:3306/mydb")
//        .option("user","root")
//        .option("password","rootroot")
//        .option("dbtable","t2")
//        .mode("append")
//        .save()
        val url = "jdbc:mysql://192.168.24.100:3306/mydb"
        val properties = new Properties()
        properties.put("user", "root")
        properties.setProperty("password", "rootroot")
    df.write.mode("append").jdbc(url,"t2",properties)
    spark.stop()
  }


}
