package com.atguigu.spark.core.project.app

import java.text.DecimalFormat

import com.atguigu.spark.core.project.bean.UserVisitAction
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object PageConversionRate {
    def pageConversionRate(sc:SparkContext,actionRDD:RDD[UserVisitAction],pages : String)={
      val splits: Array[String] = pages.split(",")
      val perPages: Array[String] = splits.slice(0, splits.length - 1)
      val postPages: Array[String] = splits.slice(1, splits.length)
      val flows= perPages.zip(postPages).map {
        case (perPage, postPage) => s"$perPage->$postPage"
      }
      val actionPageRDD: RDD[UserVisitAction] = actionRDD.filter(action => perPages.contains(action.page_id.toString))
      val pageAndSessionRDD: RDD[(Long, Int)] = actionPageRDD.map(action => (action.page_id,1))
      val pageCount: collection.Map[Long, Long] = pageAndSessionRDD.countByKey()

      val sessionGroup: RDD[(String, Iterable[UserVisitAction])] = actionRDD.groupBy(_.session_id)
      val pageToNext: RDD[(String, Int)] = sessionGroup.flatMap {
        case (sid, action) =>
          val actions: List[UserVisitAction] = action.toList.sortBy(_.action_time)
          val preActions: List[UserVisitAction] = actions.slice(0, actions.length - 1)
          val postActions: List[UserVisitAction] = actions.slice(1, actions.length)
          val actionFlows: List[(UserVisitAction, UserVisitAction)] = preActions.zip(postActions)
          val pageFlows: List[String] = actionFlows.map {
            case (preAction, postAction) => s"${preAction.page_id}->${postAction.page_id}"
          }
          val pagetopage: List[String] = pageFlows.filter(list => flows.contains(list))
          pagetopage.map((_,1))
      }
      val pageToNextCount: collection.Map[String, Long] = pageToNext.countByKey()
      val decimalFormat = new DecimalFormat(".00%")
      val resultRate: List[(String, String)] = pageToNextCount.map {

        case (pageTonext, count) =>
          val rate: String = decimalFormat.format(count.toDouble / pageCount(pageTonext.split("->")(0).toLong))
          (pageTonext,rate)
      }.toList
      resultRate

    }
}
