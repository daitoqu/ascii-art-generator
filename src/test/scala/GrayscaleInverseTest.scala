package MainTest

import Image.PixelFilter.GrayscaleInverse
import Image.PixelFormat.GrayscalePixel
import org.scalatest.FunSuite

class GrayscaleInverseTest extends FunSuite{
  test("Apply to black"){
    var pix = new GrayscalePixel(0)
    var filter = new GrayscaleInverse()
    filter.apply(pix)
    assert(pix.luma == 255)
  }

  test("Apply to white"){
    var pix = new GrayscalePixel(0xFFFFFFFF)
    var filter = new GrayscaleInverse()
    filter.apply(pix)
    assert(pix.luma == 0)
  }

  test("Apply to arbitrary"){
    var pix = new GrayscalePixel(0x89A40DCF)
    var filter = new GrayscaleInverse()
    filter.apply(pix)
    assert(pix.luma == 176)
  }
}
