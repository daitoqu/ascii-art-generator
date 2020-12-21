package Command
import Image.Image
import _root_.Image.ImageTransform.{Deg180, Deg270, Deg90, HalfRes, TwiceRes}

class Rotate(amount: Int) extends Command {
  override def Execute(target: Image): Unit = {
    if (amount % 90 != 0)
      throw new Exception("Invalid angle")
    var angle = amount % 360
    if (angle < 0)
      angle = 360 + angle
    angle match {
      case 90 => target.Rotate(Deg90)
      case 180 => target.Rotate(Deg180)
      case 270 => target.Rotate(Deg270)
      case 0 => {}
      case _ => {
        throw new Exception("Invalid angle")
      }
    }
  }
}
