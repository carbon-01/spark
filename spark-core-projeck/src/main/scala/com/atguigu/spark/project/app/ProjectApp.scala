package com.atguigu.spark.project.app

import com.atguigu.spark.project.acc.CategoryAcc
import com.atguigu.spark.project.bean.{CategoryCountInfo, UserVisitAction}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object ProjectApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ProjectApp")
    val sc = new SparkContext(conf)
    val sourceRDD: RDD[String] = sc.textFile("e:/user_visit_action.txt")
    val userActionRDD: RDD[UserVisitAction] = sourceRDD.map(line => {
      val splits: Array[String] = line.split("_")
      UserVisitAction(
        splits(0),
        splits(1).toLong,
        splits(2),
        splits(3).toLong,
        splits(4),
        splits(5),
        splits(6).toLong,
        splits(7).toLong,
        splits(8),
        splits(9),
        splits(10),
        splits(11),
        splits(12).toLong)
    })
    val categoryTop10: Array[CategoryCountInfo] = CategoryTopApp.statCategoryTop10(sc, userActionRDD)
//    categoryTop10.foreach(
    ////      println
    ////    )
    //CalcCategorySessionTop10.calcCategorySessionTop10(sc, userActionRDD: RDD[UserVisitAction], categoryTop10)
    //val SessionTop10 = CalcCategorySessionTop10.calcCategorySessionTop10_1(sc, userActionRDD: RDD[UserVisitAction], categoryTop10)
    //SessionTop10.foreach(println)
    PageConversionApp.pageConversionApp(sc,userActionRDD,"1,2,3,4,5,6,7")
  }
}
