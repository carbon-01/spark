package com.atguigu.spark.core.project.app

import com.atguigu.spark.core.project.acc.ActionAcc
import com.atguigu.spark.core.project.bean.{CategoryCountInfo, UserVisitAction}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object CatetoryTop {
  def catetoryTop(sc:SparkContext,actionRDD:RDD[UserVisitAction]) = {
    val acc = new ActionAcc
    sc.register(acc)
    actionRDD.foreach(acc.add(_))
    val acctionCategoryRDD: Map[(String, String), Long] = acc.value
    val categoryGroupRDD: Map[String, Map[(String, String), Long]] = acctionCategoryRDD.groupBy(_._1._1)
    val categoryCount: List[CategoryCountInfo] = categoryGroupRDD.map {
      case (cid, map) => CategoryCountInfo(
        cid,
        map.getOrElse((cid, "click"), 0L),
        map.getOrElse((cid, "order"), 0L),
        map.getOrElse((cid, "pay"), 0L)
      )
    }.toList
    val categorySortby: List[CategoryCountInfo] = categoryCount.sortBy(action => (-action.clickCount, -action.orderCount, -action.payCount))
    val categoryTop10: List[CategoryCountInfo] = categorySortby.take(10)
    categoryTop10
  }
}
