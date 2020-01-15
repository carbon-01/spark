package com.atguigu.spark.core.project.app

import com.atguigu.spark.project.bean.{CategoryCountInfo, UserVisitAction}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object ProjectApp {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("project")
    val sc = new SparkContext(conf)
    val sourceRDD: RDD[String] = sc.textFile("e:/user_visit_action.txt")
    val actionRDD: RDD[UserVisitAction] = sourceRDD.map(action => {
      val splits: Array[String] = action.split("_")
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


    val categoryTop10: Array[CategoryCountInfo] = CaterogyTop10.categoryTop10(sc, actionRDD)
    //categoryTop10.foreach(println)
    //    val sessinTop10: RDD[(Long, List[(String, Int)])] = SessionTop10.sessionTop10(sc, actionRDD, categoryTop10)
    //    sessinTop10.foreach(println)
    SessionTop10.sessionTop10(sc, actionRDD, categoryTop10)
  }
}
