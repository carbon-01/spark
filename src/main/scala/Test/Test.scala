package Test

object Test{
  def main(args: Array[String]): Unit = {
    def test(a : Int,b : Int) : Unit ={
      println(a + b)
    }
    def test1(f : (Int,Int) => Unit): Unit ={
      f(1,2)
    }
    def test2(a : Int) = {
      def test3 (b : Int ){
        println(a + b)
      }
      test3 _
    }
    //test2(2)(3)

    def test4 = (a : Int) =>  {
      println( "test" )
      1
    }
    def test5(b : => Int) = {
      println(b)
    }
    test5(test4(2))


  }
}
