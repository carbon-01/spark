package com.atguigu.spark.core.project.acc

import org.apache.spark.Partitioner

class SessionPartitioner(partitions: List[String]) extends Partitioner{
  private val partitionNum: Int = partitions.length
  private val map: Map[String, Int] = partitions.zipWithIndex.toMap
  override def numPartitions: Int = partitionNum
  override def getPartition(key: Any): Int = key match {
    case (category_id : Long , _ ) => map(category_id.toString)
  }
}
