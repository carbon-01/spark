package com.atguigu.spark.core.day03

import org.apache.hadoop.hdfs.DFSClient.Conf
import org.apache.spark.rdd.RDD
import org.apache.spark.{Partition, Partitioner, RangePartitioner, SparkConf, SparkContext}

import scala.reflect.ClassTag

object Test02 {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("Test02"))
    sc.textFile("e:/")
    var tuple = Tuple2(("a",1),("b",2))
    val map = Map(("a", 1), ("b", 2))
    val rdd1: RDD[Int] = sc.parallelize(1 :: 2 :: 111 :: 22 :: Nil)
    val rdd2: RDD[(Int, Int)] = rdd1.map((_, 1))
    rdd2.sortByKey()
    //val rdd3: RDD[(Int, Int)] = rdd2.partitionBy(new MyPartition(3))
    val rdd3: RDD[(Int, Int)] = rdd2.partitionBy(new RangePartitioner(4, rdd2))
    val rdd4: RDD[Array[(Int, Int)]] = rdd3.glom()
    rdd4.foreachPartition(it => println(it))

  }
}
class MyPartition(var partitionNum : Int) extends Partitioner{
  //传入分区数
  override def numPartitions: Int = partitionNum
  //根据key来计算这个k-v应该进入到那个分区中
  override def getPartition(key: Any): Int = key match{
    case null => 0
    case _ => key.hashCode() % partitionNum
  }
  //传入hashcode值
  override def hashCode(): Int = partitionNum
  //和当前的分区器进行比较，如果分区器一样而且分区数一致，那么就不会触发shuffle过程
  override def equals(obj: Any): Boolean = obj match {
    case obj : MyPartition => obj.partitionNum == partitionNum
    case _  => false
  }
}
