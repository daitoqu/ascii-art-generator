package Image.PixelFilter

import Image.PixelFormat.GrayscalePixel

class GrayscaleBrightness(amount: Int) extends GrayscalePixelFilter {
  override def apply(pixel: GrayscalePixel): Unit = {
    pixel.luma = pixel.luma + amount
    pixel.clamp()
  }
}
