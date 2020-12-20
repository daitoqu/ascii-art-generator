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
      case "--max-size" :: value :: tail =>
        nextOption(map ++ Map('maxsize -> value.toInt), tail)
      case "--min-size" :: value :: tail =>
        nextOption(map ++ Map('minsize -> value.toInt), tail)
      case string :: opt2 :: tail if isSwitch(opt2) =>
        nextOption(map ++ Map('infile -> string), list.tail)
      case string :: Nil =>  nextOption(map ++ Map('infile -> string), list.tail)
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