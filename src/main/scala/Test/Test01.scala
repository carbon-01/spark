package Test

object Test01 {
  def main(args: Array[String]): Unit = {
    def userCompare[T : Ordering](a : T,b : T): T ={
      val ord: Ordering[T] = implicitly[Ordering[T]]
      ord.max(a,b)
    }
    implicit val ord : Ordering[User] = new Ordering[User] {
      override def compare(x: User, y: User): Int = x.age - y.age
    }

    println(userCompare(User("jack", 10), User("tom", 12)))
  }
}
case class User(name : String ,age : Int ){

}
