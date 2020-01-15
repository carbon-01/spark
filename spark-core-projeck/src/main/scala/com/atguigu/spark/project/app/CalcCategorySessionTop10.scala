package com.atguigu.spark.project.app

import com.atguigu.spark.project.acc.SessionPartitioner
import com.atguigu.spark.project.bean.{CategoryCountInfo, SessionInfo, UserVisitAction}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.mutable

object CalcCategorySessionTop10 {
  def calcCategorySessionTop10(sc: SparkContext, userActionRDD: RDD[UserVisitAction], categoryTop10: Array[CategoryCountInfo])= {
    val cids: Array[Long] = categoryTop10.map(_.categoryId.toLong)
    val filterCategorys: RDD[UserVisitAction] = userActionRDD.filter(action => cids.contains(action.click_category_id))
    val categroySessionCount: RDD[((Long, String), Int)] = filterCategorys.map(action => {
      ((action.click_category_id, action.session_id), 1)
    }).reduceByKey(_ + _)
    val resultSessionTop10: RDD[(Long, List[(String, Int)])] = categroySessionCount.map {
      case ((cid, sid), count) => (cid, (sid, count))
    }.groupByKey().mapValues(it => it.toList.sortBy(-_._2).take(10))

    resultSessionTop10.foreach(println)
  }
  def calcCategorySessionTop10_1(sc: SparkContext, userActionRDD: RDD[UserVisitAction], categoryTop10: Array[CategoryCountInfo])= {
    val cids: Array[Long] = categoryTop10.map(_.categoryId.toLong)
    val filterCategorys: RDD[UserVisitAction] = userActionRDD.filter(action => cids.contains(action.click_category_id))
    val categroySessionCount: RDD[((Long, String), Int)] = filterCategorys.map(action => {
      ((action.click_category_id, action.session_id), 1)
    }).reduceByKey(new SessionPartitioner(cids: Array[Long]),_ + _)
    val sessionCount: RDD[(Long, (String, Int))] = categroySessionCount.map {
      case ((cid, sid), count) => (cid, (sid, count))
    }
    val sessionResult: RDD[(Long, SessionInfo)] = sessionCount.mapPartitions(it => {
      var set: mutable.TreeSet[SessionInfo] = mutable.TreeSet[SessionInfo]()
      var categoryID = 0L
      it.foreach {
        case (cid, (sid, count)) =>
          categoryID = cid
          set += SessionInfo(sid, count)
          if (set.size > 10) set = set.take(10)
      }
      set.map((categoryID, _)).toIterator
    })
    sessionResult.collect().groupBy(_._1).mapValues(_.toList)foreach(println)
  }
}
