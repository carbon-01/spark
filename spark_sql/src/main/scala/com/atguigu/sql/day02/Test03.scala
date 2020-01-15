package com.atguigu.sql.day02

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.api.java.StorageLevels
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

import scala.util.parsing.input.StreamReader

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("Test02")
    val scc = new StreamingContext(conf, Seconds(2))
    val receiverDStream: ReceiverInputDStream[String] = scc.receiverStream(new MyReceiver("hadoop103", 6666))
    receiverDStream.flatMap(_.split("\\W+")).map((_, 1)).reduceByKey(_ + _).print(1000)
    scc.start()
    scc.awaitTermination()
  }
}

class MyReceiver(host: String, port: Int) extends Receiver[String](StorageLevels.MEMORY_ONLY) {
  var socket: Socket = _
  var read: BufferedReader = _

  //receiver启动时回调这个方法
  override def onStart(): Unit = {
    //使用函数的控制抽象传递代码到线程中
    //因为onStart方法不能进行阻塞，所以需要定义其他的方法启动线程，完成读数据
    runThread {
      try {
        //创建socket对象
        socket = new Socket(host, port)
        //将数据流封装到字符流中，再传递到缓冲流中，可以实现按行读取
        read = new BufferedReader(new InputStreamReader(socket.getInputStream, "utf-8"))
        //读取buffer中的一行数据
        var line: String = read.readLine()
        //在重复读取数据，直到没有数据传入
        //该处为阻塞线程
        while (socket.isConnected && line != null) {
          //传递数据到socketStream中
          store(line)
          //读取下一行数据
          line = read.readLine()
        }
      } catch {
        case e => println(e.getMessage)
      } finally { //发生异常，保证实时读取数据
        restart("重启receiver")
      }
    }

  }

  //receiver关闭时调用，并且释放资源
  override def onStop(): Unit = {
    if (socket != null) { //可以避免空指针异常
      socket.close()
    }
    if (read != null) {
      read.close()
    }
  }

  def runThread(f: => Unit): Unit = {
    new Thread() {
      override def run = f
    }.start()
  }
}
