

class Rotations(var howMany:Int) extends Permutations {
  override def amnt = howMany
  override def copy:Rotations = new Rotations(howMany)
  override def newamnt(n:Int) = howMany = n
  override def stringVer:String = howMany.toString + "R "
  def PrettyPrint():Unit = printf(howMany + "R ")
  
  def ==(compared:Permutations): Boolean =
  {
    if(this.getClass == compared.getClass && howMany == compared.amnt)
      true
    else
      false
  }
}