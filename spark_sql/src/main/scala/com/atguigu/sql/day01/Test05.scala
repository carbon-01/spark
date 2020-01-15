package com.atguigu.sql.day01


import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, SparkSession, TypedColumn}

object Test05 {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[2]")
      .appName("Test05")
      .getOrCreate()
    val df: DataFrame = spark.read.json("e:/user.json")
      .filter(row => !(row.isNullAt(0) || row.isNullAt(1)))
    import spark.implicits._
    val ds: Dataset[User] = df.as[User]
    val c: TypedColumn[User, Double] = UserAgv.toColumn.name("age_sum")
    ds.select(c).show()
  }
}
//样例类
case class User(name : String,age : Long)
//缓存区样例类
case class AgeAgv(sum : Long , count : Long) {
  def avg: Double = sum.toDouble / count
}
object UserAgv extends Aggregator[User, AgeAgv, Double] {
    //对缓存区进行初始化
    override def zero: AgeAgv = AgeAgv(0L, 0L)

    //分区内的聚合
    override def reduce(b: AgeAgv, a: User): AgeAgv = {
      a match {
        case u: User if (u.name == null || u.age == 0) => b
        case u: User => AgeAgv(b.sum + u.age, b.count + 1)
        case _ => b
      }
    }

    //分区间的聚合
    override def merge(b1: AgeAgv, b2: AgeAgv): AgeAgv = {
      AgeAgv(b1.sum + b2.sum, b1.count + b2.count)
    }

    //返回缓冲区的值
    override def finish(reduction: AgeAgv): Double = reduction.avg

    //缓冲区的编码器
    override def bufferEncoder: Encoder[AgeAgv] = Encoders.product

    //输出的编码器
    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}
