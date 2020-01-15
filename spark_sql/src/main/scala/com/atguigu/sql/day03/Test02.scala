package com.atguigu.sql.day03

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Test02{
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test02")
    //创建入口
    val scc: StreamingContext = new StreamingContext(conf,Seconds(3))
    //设置检查点
    scc.checkpoint("./ck2")
    //获取netcat中的数据
    val sourceStream: ReceiverInputDStream[String] = scc.socketTextStream("hadoop103", 9999)
    val result: DStream[(String, Int)] = sourceStream
      .flatMap(_.split("\\W+")
        .map((_, 1)))
      //开窗，步长为9s,滑动步长为6s
      //.reduceByKeyAndWindow(_ + _, Seconds(9), slideDuration = Seconds(6))
    //开窗，步长为9s,滑动步长为6s,计算时需要优化，通过上一次的计算结果+新增的数据-移动的数据
    //注意：使用优化需要设置checkpoint directory，保存上一次的数据
      //使用filter过滤value等于0的数据
       .reduceByKeyAndWindow(_+_,_-_,Seconds(9),slideDuration = Seconds(6),filterFunc = _._2 > 0)
    //每间隔滑动步长的时间打印一次
    result.print(100)
    scc.start()
    scc.awaitTermination()
  }
}
