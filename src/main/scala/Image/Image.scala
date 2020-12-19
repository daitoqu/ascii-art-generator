package Image

import Image.ImageTransform.{Flipable, Rotatable, Scalable}
import Image.PixelFilter.PixelFilter
import Image.PixelFormat.Pixel

class Image(
                  val dimX: Int,
                  val dimY: Int,
                  var pixelArray: Array[Array[Pixel]]
                )
{
  def ApplyFilter(pixelFilter: PixelFilter): Unit = {
    for (y <- 0 until dimY) {
      for (x <- 0 until dimX) {
        pixelFilter.apply(pixelArray(y)(x))
      }
    }
  }
}
