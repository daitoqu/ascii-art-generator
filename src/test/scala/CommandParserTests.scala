import Command.{Command, Flip, Invert, Rotate, Scale}
import Image.ImageLoader.StandartFileLoader
import Main.{ArtGenerator, CommandParser}
import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer

class CommandParserTests extends FunSuite {
  test("Parse correct commands") {
    var loader = new StandartFileLoader("files/test_gradient.png")
    val artGen = new ArtGenerator
    var imgExample = loader.LoadImage()
    var commandsExample = new ArrayBuffer[Command]()
    commandsExample.addOne(new Scale(4))
    commandsExample.addOne(new Rotate(180))
    commandsExample.addOne(new Invert)
    commandsExample.addOne(new Flip("x"))
    commandsExample.addOne(new Scale(0.25))
    for (cmnd <- commandsExample) {
      cmnd.Execute(imgExample)
    }

    val textArgs: List[String] = List(
      "--image", "files/test_gradient.png",
      "--scale", "4",
      "--rotate", "180",
      "--invert",
      "--flip", "x",
      "--scale", "0.25"
    )

    val (loader2, commands) = CommandParser.parse(textArgs)
    var img2 = loader2.LoadImage()
    for (cmnd <- commands) {
      cmnd.Execute(img2)
    }

    assert(artGen.ConvertImage(imgExample) == artGen.ConvertImage(img2))
  }

  test("Parse incorrect file format") {
    val textArgs: List[String] = List(
      "--image", "files/test_gradient.psd",
      "--scale", "4",
      "--rotate", "180",
      "--invert",
      "--flip", "x",
      "--scale", "0.25"
    )
    var err = ""
    try {
      val (loader2, commands) = CommandParser.parse(textArgs)
    } catch {
      case e : Throwable => err = e.getMessage
    }

    assert(err == "Unsupported file format.")
  }
}
