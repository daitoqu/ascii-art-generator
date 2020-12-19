package Image.PixelFormat

class Pixel
{
  var luminance: Int = 0
  def this(packetPixel: Int) = {
    this()
    val red = (packetPixel & 0xFF0000) >> 16
    val green = (packetPixel & 0xFF00) >> 8
    val blue = packetPixel & 0xFF
    luminance = ((0.3 * red) + (0.59 * green) + (0.11 * blue)).toInt
    luminance = 255 - luminance
    this.clamp()
  }

  def clamp(): Unit = {
    if (this.luminance > 255) this.luminance = 255
    if (this.luminance < 0) this.luminance = 0
  }
}
