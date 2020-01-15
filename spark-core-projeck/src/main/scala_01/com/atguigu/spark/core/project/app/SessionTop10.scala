package com.atguigu.spark.core.project.app
import com.atguigu.spark.project.bean.{CategoryCountInfo, UserVisitAction}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object SessionTop10 {
    def sessionTop10(sc : SparkContext, actionRDD : RDD[UserVisitAction], categoryTop10: Array[CategoryCountInfo]): Any ={
        val cids: Array[Long] = categoryTop10.map(_.categoryId.toLong)
        val sessionRDD: RDD[UserVisitAction] = actionRDD.filter(action =>
                        cids.contains(action.click_category_id)
                    )
        for(cid <- cids){
            val sessionSource: RDD[UserVisitAction] = sessionRDD.filter(action => cid == action.click_category_id)
            val sessionCount: RDD[((Long, String), Int)] = sessionSource.map(action => ((cid, action.session_id), 1))
            val SessionCounts: RDD[((Long, String), Int)] = sessionCount.reduceByKey(_ + _)
            val sessinGroup: Map[Long, Array[(Long, (String, Int))]] = SessionCounts.sortBy(-_._2).map {
                case ((cid, sid), count) => (cid, (sid, count))
            }.take(10).groupBy(_._1)
            val sessionTop10: Map[Long, List[(String, Int)]] = sessinGroup.map {
                case (cid, arr) => (cid, arr.map(_._2).toList)
            }
          sessionTop10
        }


//        val sessionCount: RDD[((Long, String), Int)] = sessionRDD.map(action => ((action.click_category_id, action.session_id), 1))
//        val sessionCounts: RDD[((Long, String), Int)] = sessionCount.reduceByKey(_ + _)
//        val sessionResult: RDD[(Long, List[(String, Int)])] = sessionCounts.map {
//            case ((cid, sid), count) => (cid, (sid, count))
//        }.groupByKey().mapValues(action => {
//            action.toList.sortBy(-_._2).take(10)
//        })
//        sessionResult
    }
}
