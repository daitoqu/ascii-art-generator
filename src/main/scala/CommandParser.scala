package Main

import Command.{Brightness, Command, Flip, Invert, OutputToConsole, OutputToFile, Rotate, Scale}
import Image.ImageLoader.{StandartFileLoader, ImageLoader}

import scala.collection.mutable.ArrayBuffer

object CommandParser {
  var imageLoader = new StandartFileLoader("")
  def parse(list: List[String]) : (ImageLoader, ArrayBuffer[Command]) = {
    var commands = new ArrayBuffer[Command]()
    this.parse(commands, list)
  }
  def parse(commands : ArrayBuffer[Command], list: List[String]) : (ImageLoader, ArrayBuffer[Command]) = {
    list match {
      case Nil => (imageLoader, commands)
      case "--image" :: path :: tail => {
        val format = path.takeRight(3)
        format match {
          case "jpg" | "png" | "gif" => imageLoader = new StandartFileLoader(path)
          case _ => throw new Exception("Unsupported file format.")
        }
        parse(commands, tail)
      }
      case "--rotate" :: value :: tail =>
        parse(commands.addOne(new Rotate(value.toInt)), tail)
      case "--scale" :: value :: tail =>
        parse(commands.addOne(new Scale(value.toDouble)), tail)
      case "--invert" :: tail =>

        parse(commands.addOne(new Invert), tail)
      case "--brightness" :: value :: tail =>
        parse(commands.addOne(new Brightness(value.toInt)), tail)
      case "--flip" :: axis :: tail =>
        parse(commands.addOne(new Flip(axis)), tail)
      case "--output-console" :: tail =>
        parse(commands.addOne(new OutputToConsole), tail)
      case "--output-file" :: path :: tail =>
        parse(commands.addOne(new OutputToFile(path)), tail)
      case option :: tail => {
        throw new Exception("Unknown option" + option)
      }
    }
  }
}
