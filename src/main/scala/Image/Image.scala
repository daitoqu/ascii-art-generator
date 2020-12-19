package Image

import Image.PixelFormat.Pixel

class Image(
                  val dimX: Int,
                  val dimY: Int,
                  var pixelArray: Array[Array[Pixel]]
                )
{
}
