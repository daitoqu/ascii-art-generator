package Image.PixelFormat

class GrayscalePixel extends Pixel
{
  private var _luminance: Int = 0
  def this(packetRGB: Int) = {
    this()
    val red = (packetRGB & 0xFF0000) >> 16
    val green = (packetRGB & 0xFF00) >> 8
    val blue = packetRGB & 0xFF
    _luminance = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
    _luminance = 255 - _luminance
    this.clamp()
  }

  def luma: Int = {
    _luminance
  }

  def luma_=(newLum: Int) = {
    _luminance = newLum
  }

  def clamp(): Unit = {
    if (this._luminance > 255) this._luminance = 255
    if (this._luminance < 0) this._luminance = 0
  }
}
