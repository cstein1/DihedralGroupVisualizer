// import NGon.MyImplicits._
import scala.collection.mutable.ListBuffer

object Main {
   def TestEachIndividually: Unit = 
   {
     var n = 10
     var Example = new NGon(n)
     Example.Display()
     println("Rotate Left")
     for (a <- 0 until n)
     {
       Example.RotateLeft
       Example.Display()
     }
     println("Rotate Right")
     for (a <- 0 until n)
     {
       Example.RotateRight
       Example.Display()
     }
     println("Flips")
     for(a <- 0 until 4)
     {
       Example.Flip
       Example.Print
     }
     println("Generate Group")
     for(a <- 0 until n)
     {
       Example.RotateRight
       Example.Print
       Example.Flip
       Example.Print
       Example.Flip
     }
   }
   
   def TestBinaryOperations:Unit =
   {
     var rot1 = new Rotations(1)
     var rot2 = new Rotations(2)
     var flip1 = new Flips(4)
     var flip2 = new Flips(5)
     var rotsList = ListBuffer[Permutations](rot1, rot2)
     var flipsList = ListBuffer[Permutations](flip1, flip2)
     var mixList1 = ListBuffer[Permutations](rot1,flip1)
     var mixList2 = ListBuffer[Permutations](flip1,rot1)
     
     var rotState = new NGon(3).state     
     var flipState = new NGon(3).state
     var mixState1 = new NGon(3).state
     var mixState2 = new NGon(3).state
     
     rotState.+=(rotsList)
     flipState.+=(flipsList)
     mixState1.+=(mixList1)
     mixState2.+=(mixList2)
     
     println("Rot + Rot")
     rotState.PrintState()
     rotState.ReduceState(0)
     rotState.PrintState()
     
     println("Flip + Flip")
     flipState.PrintState()
     flipState.ReduceState(0)
     flipState.PrintState()
     
     println("Rot + Flip")
     mixState1.PrintState()
     mixState1.ReduceState(0)
     mixState1.PrintState()
     
     println("Flip + Rot")
     mixState2.PrintState()
     mixState2.ReduceState(0)
     mixState2.PrintState()
   }
     
   def TestUpdate = {
     var n = 4
     var Example = new NGon(n)
     Example.Print
     Example.RotateRight
     Example.RotateLeft
     Example.Print
     Example.Flip
     Example.Flip
     Example.RotateLeft
     Example.Print
     Example.RotateRight
     Example.Print
     Example.Flip
     Example.Print
   }
   
  def Trying2BreakIt = {
     var n = 4
     var Example = new NGon(n)
     Example.Print
     Example.Flip
     Example.Print
     Example.RotateLeft
     Example.Print
     Example.Flip
     Example.Print
     Example.RotateLeft
     Example.Print
     
//     Example.Print
//     Example.Flip
//     Example.Print
//     Example.RotateRight
//     Example.Print
//     Example.Flip
//     Example.Print
  }
   
  
  def main(args: Array[String]): Unit = 
  {
    //TestBinaryOperations
    //TestEachIndividually
    //TestUpdate
    //Trying2BreakIt
  }
}