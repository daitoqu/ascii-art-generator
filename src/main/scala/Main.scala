package Main

import Image.ImageLoader.FileImageLoader
import Image.TextOutput.FileTextOutput
import Image.Image
import _root_.Image.ImageTransform.{Deg180, Deg270, Deg90, HalfRes, TwiceRes, XFlip, YFlip}
import _root_.Image.PixelFilter.{BrightnessFilter, InverseFilter, PixelFilter}

object Main extends App {
  val imageLoader = new FileImageLoader("files/test.jpg")
  val textOut = new FileTextOutput("files/test.txt")
  val img = imageLoader.LoadImage()
  img.Flip(YFlip)
  val artGen = new ArtGenerator()
  val art = artGen.ConvertImage(img)
  textOut.OutputText(art)
}

object Foos {
  def Foo() : Boolean = true
}