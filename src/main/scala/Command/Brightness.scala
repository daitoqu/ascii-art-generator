package Command
import Image.Image
import _root_.Image.PixelFilter.GrayscaleBrightness

class Brightness(amount: Int) extends Command {
  override def Execute(target: Image): Unit = {
    val brightnessFilter = new GrayscaleBrightness(amount)
    target.ApplyFilter(brightnessFilter)
  }
}
