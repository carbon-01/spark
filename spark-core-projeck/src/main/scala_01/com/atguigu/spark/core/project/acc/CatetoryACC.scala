package com.atguigu.spark.core.project.acc

import com.atguigu.spark.project.bean.UserVisitAction
import org.apache.spark.util.AccumulatorV2

class CatetoryACC() extends AccumulatorV2[UserVisitAction,Map[(String,String),Long]]{
  private var map: Map[(String, String), Long] = Map[(String, String), Long]()
  override def isZero: Boolean = map.isEmpty

  override def copy(): AccumulatorV2[UserVisitAction, Map[(String, String), Long]] = {
    val acc = new CatetoryACC
    acc.map = map
    acc
  }

  override def reset(): Unit = map = Map[(String, String), Long]()

  override def add(v: UserVisitAction): Unit = {
    if (v.click_category_id != -1){
      map +=
      (v.click_category_id.toString,"click") -> (map.getOrElse((v.click_category_id.toString,"click"),0L) + 1)
    }else if(v.order_category_ids != "null"){
      val oids: Array[String] = v.order_category_ids.split(",")
      oids.foreach{ oid =>
        map +=
          ((oid,"order") -> (map.getOrElse((oid,"order"),0L) + 1))
      }
    }else if(v.pay_category_ids != "null"){
      val pids: Array[String] = v.pay_category_ids.split(",")
      pids.foreach{ pid =>
        map +=
          (pid,"pay") -> (map.getOrElse((pid,"pay"),0L) + 1)
      }
    }
  }

  override def merge(other: AccumulatorV2[UserVisitAction, Map[(String, String), Long]]): Unit = other match {
    case o : CatetoryACC =>
      map = o.map.foldLeft(map){
        case (map,(cidAction,count)) =>
          map + (cidAction -> (map.getOrElse(cidAction,0L) + count))
    }
  }

  override def value: Map[(String, String), Long] = map
}
