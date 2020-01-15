package com.atguigu.spark.core.project.acc

import com.atguigu.spark.core.project.bean.UserVisitAction
import org.apache.spark.util.AccumulatorV2

class ActionAcc extends AccumulatorV2[UserVisitAction,Map[(String,String),Long]]{
  var map = Map[(String,String),Long]()
  override def isZero: Boolean = map.isEmpty

  override def copy(): AccumulatorV2[UserVisitAction, Map[(String, String), Long]] = {
    val acc = new ActionAcc
    acc.map = map
    acc
  }

  override def reset(): Unit = map = Map[(String,String),Long]()

  override def add(v: UserVisitAction): Unit = {
    if (v.click_category_id != -1){
      map += (v.click_category_id.toString,"click") -> (map.getOrElse((v.click_category_id.toString,"click"),0L) + 1)
    }else if(v.order_category_ids != "null"){
      val orderIds = v.order_category_ids.split(",")
      for(orderId <- orderIds){
        map += (orderId,"order") -> (map.getOrElse((orderId,"order"),0L) + 1)
      }
    }else if(v.pay_category_ids != "null"){
      val payIds: Array[String] = v.pay_category_ids.split(",")
      for(payId <- payIds){
        map += (payId,"pay") -> (map.getOrElse((payId,"pay"),0L) + 1)
      }
    }
  }

  override def merge(other: AccumulatorV2[UserVisitAction, Map[(String, String), Long]]): Unit = other match {
    case o : ActionAcc =>
      map = o.map.foldLeft(map)((left,right) => {
      left + (right._1 -> (left.getOrElse(right._1,0L) + right._2))
    })
  }

  override def value: Map[(String, String), Long] = map
}
