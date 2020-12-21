import Command.Flip
import Image.ImageLoader.StandartFileLoader
import Image.Image
import Main.ArtGenerator
import org.scalatest.FunSuite

class FlipCommandTests extends FunSuite {
  test("Flip x command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Flip("x")
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Flip y command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Flip("y")
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Flip asdf command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Flip("asdf")
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "Invalid axis format")
  }

  test("Flip a command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Flip("a")
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "Invalid axis")
  }
}
