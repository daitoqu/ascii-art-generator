package Command
import Image.Image
import _root_.Image.PixelFilter.GrayscaleInverse

class Invert extends Command {
  override def Execute(target: Image): Unit = {
    val invertFilter = new GrayscaleInverse
    target.ApplyFilter(invertFilter)
  }
}
