package Image.PixelFormat

class Pixel(
             var red: Int,
             var green: Int,
             var blue: Int,
             var alpha: Int
               )
{

  def this(packetPixel: Int) = {
    this(0, 0, 0, 255)
    this.red = packetPixel >> 16 & 0xFF
    this.green = packetPixel >> 8 & 0xFF
    this.blue = packetPixel & 0xFF
  }

  def clamp(): Unit = {
    if (this.red > 255) this.red = 255
    if (this.green > 255) this.green = 255
    if (this.blue > 255) this.blue = 255
    if (this.alpha > 255) this.alpha = 255

    if (this.red < 0) this.red = 0
    if (this.green < 0) this.green = 0
    if (this.blue < 0) this.blue = 0
    if (this.alpha < 0) this.alpha = 0
  }
}
