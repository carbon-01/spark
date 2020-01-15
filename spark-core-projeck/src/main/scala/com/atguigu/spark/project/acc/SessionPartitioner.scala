package com.atguigu.spark.project.acc

import org.apache.spark.{HashPartitioner, Partitioner}

class SessionPartitioner(cids: Array[Long]) extends Partitioner {
  private val map: Map[Long, Int] = cids.zipWithIndex.toMap
  override def numPartitions: Int = cids.size

  override def getPartition(key: Any): Int = key match{
    case (cid : Long,_) => map(cid)
  }
  override def equals(other: Any): Boolean = other match {
    case h: SessionPartitioner =>
      h.numPartitions == numPartitions
    case _ =>
      false
  }

  override def hashCode: Int = numPartitions
}
