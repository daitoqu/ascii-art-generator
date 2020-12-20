package Main

import Command.{Brightness, Command, Flip, Invert, OutputToConsole, OutputToFile, Rotate, Scale}
import Image.ImageLoader.FileImageLoader
import TextOutput.FileTextOutput
import Image.Image
import _root_.Image.ImageTransform.{Deg180, Deg270, Deg90, HalfRes, TwiceRes, XFlip, YFlip}
import _root_.Image.PixelFilter.{GrayscaleBrightness, GrayscaleInverse, GrayscalePixelFilter}

import scala.collection.mutable.ArrayBuffer
import scala.sys.exit

object Main extends App {
  val (imageLoader, commands) = CommandParser.parse(args.toList)
  var image = imageLoader.LoadImage()
  for (cmnd <- commands) {
    cmnd.Execute(image)
  }
}

object Foos {
  def Foo() : Boolean = true
}