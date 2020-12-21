package MainTest

import Image.PixelFormat.GrayscalePixel
import Main.Foos
import org.scalatest.FunSuite


class MainTest extends FunSuite {
  test("Foo"){
    var pix = new GrayscalePixel(0)
    assert(pix.luma ==1)
  }
}
