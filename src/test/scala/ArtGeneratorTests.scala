import Image.ImageLoader.StandartFileLoader
import Main.ArtGenerator
import TextOutput.FileTextOutput
import org.scalatest.FunSuite

import java.util.Calendar

class ArtGeneratorTests extends FunSuite{
  test("Generate test gradient") {
    val loader = new StandartFileLoader("files/test_gradient.png")
    val artGen = new ArtGenerator
    assert(artGen.ConvertImage(loader.LoadImage()) == artGen.palette + "\r\n")
  }
}
