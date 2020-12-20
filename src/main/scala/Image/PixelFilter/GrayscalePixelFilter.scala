package Image.PixelFilter

import Image.PixelFormat.{GrayscalePixel, Pixel}

trait GrayscalePixelFilter
{
  def apply(pixel: GrayscalePixel)
}
