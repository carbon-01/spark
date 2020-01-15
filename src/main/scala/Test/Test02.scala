package Test

object Test02 {
  def main(args: Array[String]): Unit = {
    val user1 = new User1("jack", 10)
    val user2 = new User1("tom", 12)
    implicit val ord : Ordering[User1] = new Ordering[User1] {
      override def compare(x: User1, y: User1): Int = x.age - y.age
    }
    println(User1.userCompare(user1, user2).toString)
  }
}
class User1(){
  var name : String = _
  var age : Int = _
  def this(name : String ,age : Int){
    this()
    this.name = name
    this.age = age
  }

  override def toString: String = "(" + name + "," + age + ")"
}
object User1{
  def userCompare[T : Ordering](a : T,b : T)(implicit ord : Ordering[T]): T ={
    //val ord: Ordering[T] = implicitly[Ordering[T]]
    ord.max(a,b)
  }
}
