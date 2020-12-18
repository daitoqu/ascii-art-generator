package Image.PixelFilter

import Image.PixelFormat.Pixel

trait PixelFilter
{
  def apply(pixel: Pixel)
}
