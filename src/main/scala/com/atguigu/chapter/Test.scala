package com.atguigu.chapter

object Test{
  def main(args: Array[String]): Unit = {
    var s = "zhangsan"
    //print(f1(xxx) => z)
    def f1(s : String) : Char = {
      s.charAt(0).toUpper
    }
    println("print01=>" + f1(s))

    var c = (s : String) => s.charAt(0).toUpper
    println("print02=>" + c(s))

    def f2 (f : String => Char) : Char = {
      f(s)
    }
    def f22(s : String) : Char = {
      s.charAt(0).toUpper
    }
    println("print03=>" + f2(f22))

    println("print04=>" + f2(_.charAt(0)).toUpper)

    def f11 {"zhangsan"}
    println( f11)







  }
}
