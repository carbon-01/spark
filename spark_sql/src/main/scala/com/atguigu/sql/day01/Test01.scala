package com.atguigu.sql.day01

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}

object Test01 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[2]").appName("Test01")
      .getOrCreate()
    val sc: SparkContext = spark.sparkContext
    import spark.implicits._
//    val sourceRDD = sc.parallelize(User11("jack", 18) :: User11("tom", 20) :: Nil, 2)
//    val ds: Dataset[User11] = sourceRDD.toDS()
//    ds.show()
//    val rdd: RDD[User11] = ds.rdd
//    rdd.collect().foreach(println)



    val sourceRDD: RDD[Row] = sc.parallelize(User11("jack", 18) :: User11("tom", 20) :: Nil, 2).map {
      case user: User11 => Row(user.name, user.age)
    }
    val schema: StructType = StructType(List(StructField("name",StringType) ,StructField("age", LongType)))
    val df: DataFrame = spark.createDataFrame(sourceRDD, schema)
    val ds: Dataset[User11] = df.as[User11]
    val df1: DataFrame = ds.toDF()
    df1.show()

//    val rdd: RDD[Row] = df.rdd
//    val userRDD: RDD[User11] = rdd.map {
//      row => User11(row.getString(0),row.getLong(1))
//    }
//      userRDD.collect().foreach(println)
    //val df: DataFrame = sourceRDD.toDF()
    //df.show()



    spark.stop()


  }

}
case class User11(name : String,age : Long)
