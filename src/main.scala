import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.shape._
import scalafx.scene.text._
import scalafx.event.ActionEvent
import scalafx.event.EventHandler
import scalafx.scene.layout.HBox
 
import scala.collection.mutable.ListBuffer

object Main {  
    var n = 10
    var ngon = new NGon(n)
    ngon.Print
    var Points = List[Double]()
    
  def GUI(args: Array[String]):Unit = {
    val app = new JFXApp {
      stage = new JFXApp.PrimaryStage {
        title = "Rotations"
        scene = new Scene(200, 250) {
          val menuBar = new MenuBar
          val fileMenu = new Menu("File")
          val exitItem = new MenuItem("Exit")
          exitItem.onAction = (e:ActionEvent) => sys.exit(0)
          val editMenu = new Menu("Interact")
          val numSides = new MenuItem("Change Number of Sides")
//          numSides.onAction = (e:ActionEvent) => restart(args)
          
          fileMenu.items = List(exitItem)
          editMenu.items = List(numSides)
          
          menuBar.menus = List(fileMenu, editMenu)
          menuBar.prefWidth_=(200)
          
          val drawing = Polygon()
          drawing.layoutX = 100
          drawing.layoutY = 180
          
          
          var labels = List[Label]()
          var xlab = 0.0
          var ylab = 0.0
          for(i <- 0 until n; theta = i*2*math.Pi/n) {
            println(theta)
            drawing.getPoints.addAll(50*math.cos(theta),50*math.sin(theta))
            
            xlab = 55.0*math.cos(theta)+95
            ylab = 55.0*math.sin(theta)+172
              
            labels = labels.::(new Label{
              text = (ngon.loc(i).toString)
         		  layoutX = xlab
         		  layoutY = ylab         
            })
          }
          
          // Cleans up the visual. Trust me.
          for(i <- labels.indices)
            labels(i).text = ngon.loc(i).toString
          
          val stateView = new Label("STATE")
          stateView.layoutY = 100
          stateView.layoutX = 82
          
          val buttonRight = new Button("Rotate Left")
          val buttonLeft = new Button("Rotate Right")
          val buttonFlip = new Button("Flip")
          buttonLeft.layoutX = 100
          buttonLeft.layoutY = 40
          buttonRight.layoutX = 20
          buttonRight.layoutY = 40
          buttonFlip.layoutX = 80
          buttonFlip.layoutY = 70
          
          buttonRight.onAction = (e:ActionEvent) => {
        	  ngon.RotateRight
        	  ngon.Print        	 
        	  stateView.setText(""+ngon.state.StateString)
        	  for(i <- labels.indices){
        	    labels(i).text =ngon.reversedLoc(i).toString
        	  }
          }
          buttonLeft.onAction = (e:ActionEvent) => {
        	  ngon.RotateLeft
        	  ngon.Print
        	  stateView.text = ngon.state.StateString
        	  for(i <- labels.indices){
        	    labels(i).text =ngon.reversedLoc(i).toString
        	  }
          }
          buttonFlip.onAction = (e:ActionEvent) => {
        	  ngon.Flip
        	  ngon.Print
        	  stateView.text = ngon.state.StateString
         	  for(i <- labels.indices){
        	    labels(i).text =ngon.reversedLoc(i).toString
        	  }
          }
          
          var contents = List(buttonRight,buttonLeft, buttonFlip, stateView, drawing, menuBar)
          for(i <- 0 until n)
            contents = contents.::(labels(i))
          content = contents
          

          
        }
      }
    }
    app.main(args)
  }
  
  def main(args: Array[String]): Unit = 
  {
    GUI(args)
  }
}