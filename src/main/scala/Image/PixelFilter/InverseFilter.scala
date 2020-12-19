package Image.PixelFilter

import Image.PixelFormat.Pixel

class InverseFilter() extends PixelFilter {
  override def apply(pixel: Pixel): Unit = {
    pixel.luminance = 255 - pixel.luminance
  }
}
