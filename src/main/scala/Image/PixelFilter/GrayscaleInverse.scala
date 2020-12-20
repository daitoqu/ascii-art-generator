package Image.PixelFilter

import Image.PixelFormat.{GrayscalePixel, Pixel}

class GrayscaleInverse() extends GrayscalePixelFilter {
  def apply(pixel: GrayscalePixel): Unit = {
    pixel.luma = 255 - pixel.luma
  }
}
