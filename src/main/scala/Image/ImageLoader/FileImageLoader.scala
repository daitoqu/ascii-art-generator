package Image.ImageLoader

import Image.Image
import Image.PixelFormat.Pixel

import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import scala.:+
import scala.collection.mutable.ArrayBuffer

class FileImageLoader(filePath: String) extends ImageLoader {
  override def LoadImage(): Image = {
    val img = ImageIO.read(new File(filePath))
    val dimX = img.getWidth
    val dimY = img.getHeight
    var pixel2DArray = ArrayBuffer[Array[Pixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[Pixel]()
      for (x <- 0 until dimX) {
        pixelLine.addOne(new Pixel(packetPixel = img.getRGB(x, y)))
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    new Image(dimX, dimY, pixel2DArray.toArray)
  }
}
