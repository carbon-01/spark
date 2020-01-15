package com.atguigu.spark.core.project.app

import com.atguigu.spark.core.project.acc.SessionPartitioner
import com.atguigu.spark.core.project.bean.{CategoryCountInfo, SessionInfo, UserVisitAction}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.collection.mutable

object SessionTop {
    def sessionTop(sc:SparkContext,actionRDD:RDD[UserVisitAction],categoryTop10: List[CategoryCountInfo]) = {
      val category_ids: List[String] = categoryTop10.map(categoryInfo => categoryInfo.category_id)
      val sessionRDD: RDD[UserVisitAction] = actionRDD.filter(action => {
        category_ids.contains(action.click_category_id.toString)
      })
      val sessionSourceRDD: RDD[((Long, String), Int)] = sessionRDD.map(action => ((action.click_category_id, action.session_id), 1))
      val sessionPartitionCount: RDD[((Long, String), Int)] = sessionSourceRDD.reduceByKey(new SessionPartitioner(category_ids), _ + _)
      val sessionTop10 = sessionPartitionCount.mapPartitions(it => {
        var set = new mutable.TreeSet[SessionInfo]()
        it.foreach {
          case ((cid, sid), count) =>
            set += SessionInfo(cid.toString, sid, count)
            if (set.size > 10) {
              set = set.take(10)
            }
        }
        set.map(ac => (ac.category_id,ac.session_id,ac.count))
        set.toIterator
      })
      sessionTop10.collect().groupBy(_.category_id).mapValues(_.toList)
    }
}
