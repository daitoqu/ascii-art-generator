package Main

import Image.ImageLoader.FileImageLoader
import TextOutput.FileTextOutput
import Image.Image
import _root_.Image.ImageTransform.{Deg180, Deg270, Deg90, HalfRes, TwiceRes, XFlip, YFlip}
import _root_.Image.PixelFilter.{GrayscaleBrightness, GrayscaleInverse, GrayscalePixelFilter}

import scala.sys.exit

object Main extends App {
  val arglist = args.toList
  type OptionMap = Map[Symbol, Any]

  def nextOption(map : OptionMap, list: List[String]) : OptionMap = {
    def isSwitch(s : String) = (s(0) == '-')
    list match {
      case Nil => map
      case "--image" :: path :: tail =>
        nextOption(map ++ Map('image -> path), tail)
      case "--rotate" :: value :: tail =>
        nextOption(map ++ Map('rotate -> value.toInt), tail)
      case "--scale" :: value :: tail =>
        nextOption(map ++ Map('scale -> value.toDouble), tail)
      case "--invert" :: tail =>
        nextOption(map ++ Map('invert -> 1), tail)
      case "--brightness" :: value :: tail =>
        nextOption(map ++ Map('brightness -> value.toInt), tail)
      case "--flip" :: axis :: tail =>
        nextOption(map ++ Map('flip -> axis), tail)
      case "--output-console" :: tail =>
        nextOption(map ++ Map('outputconsole -> 1), tail)
      case "--output-file" :: path :: tail =>
        nextOption(map ++ Map('outputfile -> path), tail)
      case option :: tail => println("Unknown option "+option)
        exit(1)
    }
  }
  val options = nextOption(Map(),arglist)
  println(options)
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