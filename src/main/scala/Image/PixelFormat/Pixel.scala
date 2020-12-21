package Image.PixelFormat

trait Pixel {
  def luma: Int
  def luma_=(newLum: Int)
}
