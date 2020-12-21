import Image.ImageLoader.StandartFileLoader
import org.scalatest.FunSuite

class StandartFileLoaderTests extends FunSuite {
  test("Load image"){
    val loader = new StandartFileLoader("files/2077.jpg")
    val img = loader.LoadImage()
  }

  test("Load non-supported format"){
    val loader = new StandartFileLoader("files/sample.tgz")
    var err = ""
    try {
      val img = loader.LoadImage()
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "Non-supported file format.")
  }

  test("Load non-existing image"){
    val loader = new StandartFileLoader("files/i_dont_exist.jpg")
    var err = ""
    try {
      val img = loader.LoadImage()
    } catch {
      case c : Throwable => err = c.getMessage
    }
    assert(err == "Can't read input file!")
  }
}
