package Main

import Image.ImageLoader.FileImageLoader
import Image.TextOutput.FileTextOutput
import Image.Image
import _root_.Image.PixelFilter.{InverseFilter, PixelFilter}

object Main extends App {
  val imageLoader = new FileImageLoader("test.jpg")
  val textOut = new FileTextOutput("test.txt")
  val img = imageLoader.LoadImage()
  img.ApplyFilter(new InverseFilter())
  val artGen = new ArtGenerator()
  val art = artGen.ConvertImage(img)
  textOut.OutputText(art)
}

object Foos {
  def Foo() : Boolean = true
}