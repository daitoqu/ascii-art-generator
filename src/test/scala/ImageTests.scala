import Image.PixelFilter.GrayscaleInverse
import Image.PixelFormat.{GrayscalePixel, Pixel}
import Image.Image
import _root_.Image.ImageTransform.{Deg90, HalfRes, TwiceRes, XFlip, YFlip}
import org.scalatest.FunSuite

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Exception

class ImageTests extends FunSuite {
  test("Create normal image"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)

    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    pixelArray.addOne(pixelLine.toArray)
    pixelArray.addOne(pixelLine.toArray)

    var img = new Image(4, 2, pixelArray.toArray)

    for (y <- 0 until img.dimY) {
      for (x <- 0 until img.dimX) {
        assert(img.GetPixel(y)(x) == pixelLine(x))
      }
    }
  }

  test("Create with wrong pixel array (x axis)"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pixelArray.addOne(pixelLine.toArray)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)
    pixelArray.addOne(pixelLine.toArray)

    var msg: String = ""
    try {
      var img = new Image(4, 2, pixelArray.toArray)
    } catch {
      case a : Throwable => msg = a.getMessage
    }
    assert(msg == "Pixel array has wrong X dimension.")
  }

  test("Create with wrong pixel array (y axis)"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)
    pixelArray.addOne(pixelLine.toArray)

    var msg: String = ""
    try {
      var img = new Image(4, 2, pixelArray.toArray)
    } catch {
      case a : Throwable => msg = a.getMessage
    }
    assert(msg == "Pixel array has wrong Y dimension.")
  }

  test("Create with X = 0"){
    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    var msg: String = ""
    try {
      var img = new Image(0, 2, pixelArray.toArray)
    } catch {
      case a : Throwable => msg = a.getMessage
    }
    assert(msg == "Unsupported resolution.")
  }

  test("Create with Y = 0"){
    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    var msg: String = ""
    try {
      var img = new Image(4, 0, pixelArray.toArray)
    } catch {
      case a : Throwable => msg = a.getMessage
    }
    assert(msg == "Unsupported resolution.")
  }

  test("Create with X and Y = 0"){
    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    var msg: String = ""
    try {
      var img = new Image(0, 0, pixelArray.toArray)
    } catch {
      case a : Throwable => msg = a.getMessage
    }
    assert(msg == "Unsupported resolution.")
  }

  test("Scale 0.25x"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)

    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    pixelArray.addOne(pixelLine.toArray)
    pixelArray.addOne(pixelLine.toArray)

    var img = new Image(4, 2, pixelArray.toArray)
    img.Scale(HalfRes)

    assert(img.GetPixel(0)(0).luma == 120)
    assert(img.GetPixel(0)(1).luma == 135)
  }

  test("Scale 4x"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)

    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    pixelArray.addOne(pixelLine.toArray)
    pixelArray.addOne(pixelLine.toArray)

    var img = new Image(4, 2, pixelArray.toArray)
    img.Scale(TwiceRes)

    for (y <- 0 until img.dimY) {
      for (x <- 0 until img.dimX) {
        assert(img.GetPixel(y)(x) == pixelLine(x/2))
      }
    }
  }

  test("Rotate 90"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)

    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    pixelArray.addOne(pixelLine.toArray)
    pixelArray.addOne(pixelLine.toArray)

    var img = new Image(4, 2, pixelArray.toArray)
    img.Rotate(Deg90)

    for (y <- 0 until img.dimY) {
      for (x <- 0 until img.dimX) {
        assert(img.GetPixel(y)(x) == pixelLine(y))
      }
    }
  }

  test("Flip X"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)

    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    pixelArray.addOne(pixelLine.toArray)
    pixelArray.addOne(pixelLine.toArray)

    var img = new Image(4, 2, pixelArray.toArray)
    img.Flip(XFlip)

    for (y <- 0 until img.dimY) {
      for (x <- 0 until img.dimX) {
        assert(img.GetPixel(y)(x) == pixelLine(x))
      }
    }
  }

  test("Flip Y"){
    var pixelLine = new ArrayBuffer[GrayscalePixel](0)
    var pix = new GrayscalePixel(0x00000000)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xF0F0F0F0)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0x0F0F0F0F)
    pixelLine.addOne(pix)
    pix = new GrayscalePixel(0xFFFFFFFF)
    pixelLine.addOne(pix)

    var pixelArray = new ArrayBuffer[Array[GrayscalePixel]](0)
    pixelArray.addOne(pixelLine.toArray)
    pixelArray.addOne(pixelLine.toArray)

    var img = new Image(4, 2, pixelArray.toArray)
    img.Flip(YFlip)

    for (y <- 0 until img.dimY) {
      for (x <- 0 until img.dimX) {
        assert(img.GetPixel(y)(x) == pixelLine(img.dimX - 1 - x))
      }
    }
  }
}
