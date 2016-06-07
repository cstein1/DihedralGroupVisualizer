

class Flips(var howMany:Int) extends Permutations {
  override def amnt = howMany
  override def copy:Flips = new Flips(howMany)
  override def newamnt(n:Int) = howMany = n
  override def stringVer:String = howMany.toString + "S "
  def PrettyPrint():Unit = printf(howMany + "S ")
  
  def ==(compared:Permutations): Boolean =
  {
   this.getClass == compared.getClass && howMany%2 == compared.amnt%2
  }
}