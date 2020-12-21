package Command
import Image.Image
import _root_.Image.ImageTransform.{XFlip, YFlip}

class Flip(axis: String) extends Command {
  override def Execute(target: Image): Unit = {
    if (axis.length != 1) {
      throw new Exception("Invalid axis format")
    }
    axis match {
      case "x" => target.Flip(XFlip)
      case "y" => target.Flip(YFlip)
      case _ => throw new Exception("Invalid axis")
    }
  }
}
