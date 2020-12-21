import Command.Rotate
import Image.Image
import _root_.Image.ImageLoader.StandartFileLoader
import org.scalatest.FunSuite

class RotateCommandTest extends FunSuite {
  test("Rotate 90") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Rotate(90)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Rotate 180") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Rotate(180)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Rotate 270") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Rotate(270)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Rotate 360") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Rotate(360)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Rotate 1") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Rotate(1)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "Invalid angle")
  }
}
