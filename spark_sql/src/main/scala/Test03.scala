import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("Test03")
    val scc = new StreamingContext(conf, Seconds(2))
    //创建可变的队列集合
    val queue: mutable.Queue[RDD[Int]] = mutable.Queue[RDD[Int]]()
    //读取队列中的数据到DStream
    val queueDStream: InputDStream[Int] = scc.queueStream(queue, oneAtATime = false)
    //WordCount
    queueDStream.reduce(_+_).print(1000)
    //启动流处理
    scc.start()
    //生产数据到队列中
    while(true){
      val rdd: RDD[Int] = scc.sparkContext.parallelize(0 to 10)
      queue.enqueue(rdd)
      Thread.sleep(100)
    }
    //阻塞主线程
    scc.awaitTermination()

  }
}
