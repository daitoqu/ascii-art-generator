import Image.Image

class ArtGenerator(palette: String = " .:-=+*#%@") {
  def ConvertImage(srcImage: Image): String = {
    var ASCIIArt: String = ""
    for (y <- 0 until srcImage.dimY) {
      for (x <- 0 until srcImage.dimX) {
        ASCIIArt += palette(srcImage.pixelArray(y)(x).luminance % palette.length)
      }
      ASCIIArt += "\r\n"
    }
    ASCIIArt
  }
}
