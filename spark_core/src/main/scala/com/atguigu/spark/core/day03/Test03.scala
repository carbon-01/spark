package com.atguigu.spark.core.day03

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

object Test03 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("SQL")
    val sc = new SparkContext(conf)
    val driver = "com.mysql.jdbc.Driver"  //Driver驱动
    val url = "jdbc:mysql://192.168.24.100:3306/mydb"  //连接的url
    val userName = "root"  //账户
    val passWd = "rootroot" //密码
//    val rdd = new JdbcRDD(
//      sc,
//      () => {
//        Class.forName(driver)
//        DriverManager.getConnection(url,userName,passWd)
//      },
//      "select id , name from t2 where id >= ? and id <= ?",
//      1,
//      10,
//      2,
//      result => {
//        (result.getInt(1),result.getString(2))
//      }
//    )
//    rdd.collect.foreach(println)
    val rdd1 = sc.parallelize(Array((3,"carbon"),(4,"json")))
    rdd1.foreachPartition(it => {
      Class.forName(driver)
      val conn: Connection = DriverManager.getConnection(url, userName, passWd)
      val statement: PreparedStatement = conn.prepareStatement("insert into t2 values(?,?)")
      it.foreach({
        case (id, name) =>
          statement.setInt(1, id)
          statement.setString(2, name)
          statement.addBatch()
      })
      statement.execute()
      conn.close()
    })


    sc.stop()
  }

}
