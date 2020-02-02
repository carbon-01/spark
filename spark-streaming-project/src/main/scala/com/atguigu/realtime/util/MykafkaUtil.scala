package com.atguigu.realtime.util
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
 * Author atguigu
 * Date 2020/1/15 16:36
 */
object MyKafkaUtil {
  val kafkaParams = Map[String, Object](
    "bootstrap.servers" -> "hadoop102:9092,hadoop103:9092,hadoop104:9092",
    "key.deserializer" -> classOf[StringDeserializer],
    "value.deserializer" -> classOf[StringDeserializer],
    "group.id" -> "bigdata",
    //消费者从最后一位开始消费
    "auto.offset.reset" -> "latest",
    //自动提交offerSet
    "enable.auto.commit" -> (true: java.lang.Boolean)
  )
  //获取kafka的流数据的value的值，传入ssc和多个topic主题
  def getKafkaStream(ssc: StreamingContext, topic:String, otherTopics: String*): DStream[String] = {
    KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](otherTopics :+ topic, kafkaParams)
    ).map(record => record.value())
  }
}
