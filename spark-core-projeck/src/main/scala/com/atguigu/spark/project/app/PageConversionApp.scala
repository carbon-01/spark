package com.atguigu.spark.project.app

import com.atguigu.spark.project.bean.UserVisitAction
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

object PageConversionApp {
    def pageConversionApp(sc : SparkContext,userActionRDD: RDD[UserVisitAction],pages : String): Unit ={
      val splits: Array[String] = pages.split(",")
      val prePages: Array[String] = splits.slice(0, splits.length - 1)//[0,6)
      val postPages: Array[String] = splits.slice(1, splits.length)
      val flowPages = prePages.zip(postPages).map{
        case (perPage,postPage) => s"$perPage->$postPage"
      }

    }
}
