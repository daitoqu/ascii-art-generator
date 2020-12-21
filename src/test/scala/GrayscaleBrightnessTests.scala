package MainTest

import Image.PixelFilter.GrayscaleBrightness
import Image.PixelFormat.GrayscalePixel
import org.scalatest.FunSuite

class GrayscaleBrightnessTests extends FunSuite {
  test("Apply neutral"){
    var pix = new GrayscalePixel(0)
    var filter = new GrayscaleBrightness(0)
    filter.apply(pix)
    assert(pix.luma == 0)
  }

  test("Apply normal"){
    var pix = new GrayscalePixel(0)
    var filter = new GrayscaleBrightness(100)
    filter.apply(pix)
    assert(pix.luma == 100)
  }

  test("Apply positive overflow"){
    var pix = new GrayscalePixel(0)
    pix.luma = 100
    var filter = new GrayscaleBrightness(2147483647)
    filter.apply(pix)
    assert(pix.luma == 255)
  }

  test("Apply negative overflow"){
    var pix = new GrayscalePixel(0)
    pix.luma = 100
    var filter = new GrayscaleBrightness(-2147483647)
    filter.apply(pix)
    assert(pix.luma == 0)
  }
}
