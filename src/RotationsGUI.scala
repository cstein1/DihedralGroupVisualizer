import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control._

object RotationsGUI {
  val app = new JFXApp {
    stage = new JFXApp.PrimaryStage {
      title = "Rotations"
      scene = new Scene(400, 300) {
        val button = new Button("Rotate Right")
        
        content = button
      }
    }
  }
  
}