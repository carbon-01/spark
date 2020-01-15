package com.atguigu.spark.core.project.app

import com.atguigu.spark.core.project.acc.CatetoryACC
import com.atguigu.spark.project.bean.{CategoryCountInfo, UserVisitAction}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.immutable

object CaterogyTop10 {
  def categoryTop10(sc : SparkContext,actionRDD : RDD[UserVisitAction]) = {
    val acc = new CatetoryACC
    sc.register(acc)
    actionRDD.foreach(acc.add(_))
    val actionRDDMap: Map[(String, String), Long] = acc.value
    val categoryContInfo: immutable.Iterable[CategoryCountInfo] = actionRDDMap.groupBy(_._1._1).map {
      case (cid, map) => {
        CategoryCountInfo(
          cid,
          map.getOrElse((cid, "click"), 0L),
          map.getOrElse((cid, "order"), 0L),
          map.getOrElse((cid, "pay"), 0L)
        )
      }
    }
    val categoryCountTop10: Array[CategoryCountInfo] = categoryContInfo.toArray.sortBy(action => (-action.clickCount, -action.orderCount, -action.payCount)).take(10)
    categoryCountTop10
  }

}
