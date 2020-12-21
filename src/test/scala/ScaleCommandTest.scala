import Command.Scale
import Image.Image
import _root_.Image.ImageLoader.StandartFileLoader
import org.scalatest.FunSuite

class ScaleCommandTest extends FunSuite {
  test("Scale 0.25x command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Scale(0.25)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Scale 1x command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Scale(1)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Scale 4x command") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Scale(4)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "")
  }

  test("Invalid scale amount") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    var img: Image = loader.LoadImage()
    val cmnd = new Scale(3.76)
    var err = ""
    try {
      cmnd.Execute(img)
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "Invalid scaling factor")
  }
}
