package Main
import Image.Image

class ArtGenerator(palette: String = (" .:-=+*#%@").reverse) {
  def ConvertImage(srcImage: Image): String = {
    var ASCIIArt: String = ""
    for (y <- 0 until srcImage.dimY) {
      for (x <- 0 until srcImage.dimX) {
        val discErr: Double = 255.0 / (palette.length - 1)
        val charId: Int = (srcImage.GetPixel(y)(x).luma / discErr).toInt
        ASCIIArt += palette(charId)
      }
      ASCIIArt += "\r\n"
    }
    ASCIIArt
  }
}
