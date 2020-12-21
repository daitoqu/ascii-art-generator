package Image.PixelFilter

import Image.PixelFormat.GrayscalePixel

class GrayscaleBrightness(private var amount: Int) extends GrayscalePixelFilter {
  override def apply(pixel: GrayscalePixel): Unit = {
    if (amount > 255) amount = 255
    if (amount < -255) amount = -255
    pixel.luma = pixel.luma + amount
    pixel.clamp()
  }
}
