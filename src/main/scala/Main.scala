package Main

import Image.ImageLoader.FileImageLoader
import Image.TextOutput.FileTextOutput
import Image.Image

object Main extends App {
  val ImageLoader = new FileImageLoader("test.png")
  val img = ImageLoader.LoadImage()
}

object Foos {
  def Foo() : Boolean = true
}