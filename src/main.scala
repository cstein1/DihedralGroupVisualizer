import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.text._
import scalafx.event.ActionEvent
import scalafx.event.EventHandler

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
  }
   
  
    var n = 12
    var ngon = new NGon(n)
    ngon.Print
    var Points = List[Double]()
    

    
  def GUI(args: Array[String]):Unit = {
    val app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "Rotations"
        scene = new Scene(200, 250) {
          val drawing = Polygon()//0,0,100,0,100,100,0,100)
          drawing.layoutX = 100
          drawing.layoutY = 150
          var labels = List()
          var xlab = 0.0
          var ylab = 0.0
          for(i <- 0 until n; theta = i*2*math.Pi/n) {
            println(theta)
            drawing.getPoints.addAll(50*math.cos(theta),50*math.sin(theta))
            
            xlab = 55.0*math.cos(theta)
            ylab = 55.0*math.sin(theta)
            
            labels :: List(new Text(xlab.toInt,ylab.toInt, i.toString))
          }
          
          drawing.rotate = 45
          
          val stateView = new Label("STATE")
          stateView.layoutY = 70
          stateView.layoutX = 82
          
          val buttonRight = new Button("Rotate Right")
          val buttonLeft = new Button("Rotate Left")
          val buttonFlip = new Button("Flip")
          buttonRight.layoutX = 100
          buttonRight.layoutY = 10
          buttonLeft.layoutX = 20
          buttonLeft.layoutY = 10
          buttonFlip.layoutX = 80
          buttonFlip.layoutY = 40
          
          
          var contents = List(buttonRight,buttonLeft, buttonFlip, stateView, drawing)
//          contents += new Label{
//            text = "hi"
//            layoutY = 10
//            layoutX = 10
//          }
//          for(i <- 0 until n-1){
//            contents = contents + List[labels(i)]
//          }
          
          
          content = contents
          
          buttonRight.onAction = (e:ActionEvent) => {
        	  ngon.RotateRight
        	  ngon.Print
        	  stateView.setText(""+ngon.state.StateString)
          }
          buttonLeft.onAction = (e:ActionEvent) => {
        	  ngon.RotateLeft
        	  ngon.Print
        	  stateView.text = ngon.state.StateString
          }
          buttonFlip.onAction = (e:ActionEvent) => {
        	  ngon.Flip
        	  ngon.Print
        	  stateView.text = ngon.state.StateString
          }
          
        }
      }
    }
    app.main(args)
  }
  
  def main(args: Array[String]): Unit = 
  {
    //TestBinaryOperations
    //TestEachIndividually
    //TestUpdate
    //Trying2BreakIt
    
    GUI(args)
  }
}