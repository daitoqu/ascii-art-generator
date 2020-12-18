package Main

import Image.ImageLoader.FileImageLoader

object Main extends App {
  var fileLoader = new FileImageLoader("test.jpg")
  val img = fileLoader.LoadImage()
  println(img.dimX)
  println(img.dimY)
}

object Foos {
  def Foo() : Boolean = true
}