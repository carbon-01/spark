package com.atguigu.sql.day03

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaCluster.Err
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object Test09 {
  val groupId = "0830"
  val param: Map[String, String] = Map[String, String](
    ConsumerConfig.GROUP_ID_CONFIG -> groupId, //指定消费者组
    "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092" //指定连接的kafka服务器
  )
  private val topics = Set("topic_stream01", "topic_stream02")
  private val cluster = new KafkaCluster(param)
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test09")
    val ssc = new StreamingContext(conf, Seconds(5))
    val sourceStream: InputDStream[String] =
      KafkaUtils createDirectStream[String, String, StringDecoder, StringDecoder, String](
      ssc,
      param,
      readOfferset(),
      (handler: MessageAndMetadata[String, String]) => handler.message()
    )
    sourceStream.flatMap(_.split("\\W+")).map((_,1)).reduceByKey(_+_).print(1000)
    saveOffsets(sourceStream)
    ssc.start()
    ssc.awaitTermination()
  }

  def readOfferset() = {
    //获取主题对应的分区信息(topic_partition)
    val topicAndPartition: Either[Err, Set[TopicAndPartition]] = cluster.getPartitions(topics)
    //保存各分区对应的offerSet数据到map中
    var partitionAndOfferSets: Map[TopicAndPartition, Long] = Map[TopicAndPartition, Long]()
    //处理分区的信息
    topicAndPartition match {
      //存在该主题
      case Right(topicAndPartitionSet) =>
        //获取分区对应的offerSet
      val topicAndOfferSets: Either[Err, Map[TopicAndPartition, Long]] = cluster.getConsumerOffsets(groupId, topicAndPartitionSet)
        //val value: Set[TopicAndPartition] = topicAndPartition.right.get
        topicAndOfferSets match {
          //表示不是第一次消费，封装数据到Map[TopicAndPartition, Long]
          case Right(map) => partitionAndOfferSets ++= map
          //表示第一次消费，返回偏移量（topic_partition_offerSet,0）
          case Left(_) =>
            //封装(topic_partition,0L)到map中
            partitionAndOfferSets ++= topicAndPartitionSet.map((_,0L)).toMap
        }
      //表示主题不存在
      case Left(_) =>
    }
    partitionAndOfferSets
  }
  //保存partition_offerSet到kafka
  def saveOffsets(sourceStream: InputDStream[String]) = {
    //遍历每个时间间隔内的rdd
    sourceStream.foreachRDD(rdd => {
      //定义map用来存储(topic_partition,offerSet)
      var offerSets = Map[TopicAndPartition,Long]()
      //转换rdd为HasOffSetRanges类型
      val hasOffsetRanges: HasOffsetRanges = rdd.asInstanceOf[HasOffsetRanges]
      //获得各分区对应的offerset的边界数组
      //offerSet.untilOffset结束的位置
      //offerSet.fromOffset结束的位置
      val offerSetRanges: Array[OffsetRange] = hasOffsetRanges.offsetRanges
      //封装各分区(topic_partition,offerSet)数据
      offerSets ++= offerSetRanges.map(offerSet =>
        offerSet.topicAndPartition() -> offerSet.untilOffset)
      //传递offerSet到kafka
      cluster.setConsumerOffsets(groupId,offerSets)
    })
  }
}

