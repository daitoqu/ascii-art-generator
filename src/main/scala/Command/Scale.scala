package Command
import Image.Image
import _root_.Image.ImageTransform.{HalfRes, TwiceRes}

import scala.sys.exit

class Scale(amount: Double) extends Command {
  override def Execute(target: Image): Unit = {
    amount match {
      case 4 => target.Scale(TwiceRes)
      case 0.25 => target.Scale(HalfRes)
      case 1 => {}
      case _ => {
        println("Invalid scaling factor")
        exit(1)
      }
    }
  }
}
