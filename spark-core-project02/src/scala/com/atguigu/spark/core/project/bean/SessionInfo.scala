package com.atguigu.spark.core.project.bean

case class SessionInfo(
                      category_id : String,
                      session_id : String,
                      count : Long
                      ) extends Ordered[SessionInfo]{
  override def compare(that: SessionInfo): Int = if (this.count <= that.count) 1 else -1
}
