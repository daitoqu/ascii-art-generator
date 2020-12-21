package MainTest

import Image.PixelFormat.GrayscalePixel
import org.scalatest.FunSuite


class GrayscalePixelTests extends FunSuite {
  test("Create from RGB White"){
    var pix = new GrayscalePixel(0xFFFFFFFF)
    assert(pix.luma == 255)
  }

  test("Create from RGB Black"){
    var pix = new GrayscalePixel(0)
    assert(pix.luma == 0)
  }

  test("Create from RGB Arbitrary"){
    var pix = new GrayscalePixel(0x89A40DCF)
    assert(pix.luma == 79)
  }

  test("Set luma normal"){
    var pix = new GrayscalePixel(0)
    pix.luma = 140
    assert(pix.luma == 140)
  }

  test("Set luma too high"){
    var pix = new GrayscalePixel(0)
    pix.luma = 1000000
    assert(pix.luma == 255)
  }

  test("Set luma too low"){
    var pix = new GrayscalePixel(0)
    pix.luma = -1000000
    assert(pix.luma == 0)
  }
}
