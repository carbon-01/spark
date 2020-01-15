package com.atguigu.spark.project.app

import com.atguigu.spark.project.acc.CategoryAcc
import com.atguigu.spark.project.bean.{CategoryCountInfo, UserVisitAction}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.sys.process.ProcessBuilder.Source

object CategoryTopApp {
    def statCategoryTop10(sc : SparkContext,userActionRDD:RDD[UserVisitAction]) = {
      val acc = new CategoryAcc
      sc.register(acc,"CategoryAcc" )
      userActionRDD.foreach(action => {
        acc.add(action)
      })
      val actionMap: Map[(String, String), Long] = acc.value
      val categropyCountInfos: Array[CategoryCountInfo] = actionMap.groupBy(_._1._1).map {
        case (cid, map) =>
          CategoryCountInfo(
            cid,
            map.getOrElse((cid, "click"), 0L),
            map.getOrElse((cid, "order"), 0L),
            map.getOrElse((cid, "pay"), 0L)
          )
      }.toArray
      val categoryCountTop10: Array[CategoryCountInfo] = categropyCountInfos
        .sortBy(x =>
          (x.clickCount, x.orderCount, x.payCount))(Ordering.Tuple3(Ordering.Long.reverse, Ordering.Long.reverse, Ordering.Long.reverse)).take(10)


      categoryCountTop10

    }

}
