package com.atguigu.chapter

object Scala_Function {
  def main(args : Array[String]): Unit ={
//    def f1(): Unit ={
//    }
//    println(f1)
//    def f2(a : Int) : Unit = {
//      println(s"a:$a")
//    }
//    f2(20)
//    def f3(a : String): String = {
//      return a
//    }
//    println(f3("zhangsan"))
//
//    def f4() : String = {
//      return "zhangsan"
//    }
//    println(f4)
//
//    def f5(a : Int,b : Int ) : Int = {
//      return a + b
//    }
//    println(f5(20,10))
//
//    def f6(a : Int ) : Any = {
//      if ( a > 10){
//        return a
//      }else{
//        return "a<=10"
//      }
//    }
//    println(f6(1))
//
//    def f7( j : Int ,i : Int*): Unit ={
//      println(s"j:$j",s"i:$i")
//    }
//    f7(1,2,3,4,5)
//
//    def f8(age : Int,name : String = "zhangsan"): Unit = {
//      println("name:" + name +" "+ "age:" + age)
//    }
//    f8(age = 18)

    def f10(): String = {
      "zhangsan"
    }
    println(f10)

    def f11():String = "lisi"
    println(f11())

    def f12(age : Int)={
      if(age > 10) 10 else "age<=10"
    }
    println(f12(12 ))

    def f13() : Unit = return "zhangsan"
    println(f13)


    def f14 = "wangwu"
    println(f14)

    def f15(){"lisi"}
    println("f15:" + f15())

    def f16()={"zhangsan"}
    println(f16)

    var f17 = () => {"zhangsan"}
    f17
    var f18 = ( aa : Int ) => { aa}
    f18(12)

    var f19 = (bb : Int ) =>  {println(bb)}
    f19(20)








  }

}
