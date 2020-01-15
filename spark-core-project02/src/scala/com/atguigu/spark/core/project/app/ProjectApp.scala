package com.atguigu.spark.core.project.app

import com.atguigu.spark.core.project.bean.{CategoryCountInfo, SessionInfo, UserVisitAction}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object ProjectApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("ProjectApp")
    val sc = new SparkContext(conf)
    val sourceRDD: RDD[String] = sc.textFile("e:/user_visit_action.txt")
    val actionRDD: RDD[UserVisitAction] = sourceRDD.map(line => {
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
        splits(12).toLong
      )
    })
    val categoryTop10: List[CategoryCountInfo] = CatetoryTop.catetoryTop(sc, actionRDD)
    categoryTop10.foreach(println)
    val sessinTop10: Map[String, List[SessionInfo]] = SessionTop.sessionTop(sc, actionRDD, categoryTop10)
    sessinTop10.foreach(println)
    val resultRate: List[(String, String)] = PageConversionRate.pageConversionRate(sc, actionRDD, "1,2,3,4,5,6,7")
    resultRate.sortBy(_._1).foreach(println)
  }
}
