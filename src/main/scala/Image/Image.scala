package Image

import Image.ImageTransform.{AxisFlip, Deg180, Deg270, Deg90, Flipable, HalfRes, Rotatable, RotationFactor, Scalable, ScalingFactor, TwiceRes, XFlip, YFlip}
import Image.PixelFilter.GrayscalePixelFilter
import Image.PixelFormat.GrayscalePixel

import scala.collection.mutable.ArrayBuffer

class Image extends Scalable with Rotatable with Flipable
{
  var dimX: Int = 0
  var dimY: Int = 0
  private var pixelArray: Array[Array[GrayscalePixel]] = new Array[Array[GrayscalePixel]](0)
  def this(
    iDimX: Int,
    iDimY: Int,
    iPixelArray: Array[Array[GrayscalePixel]]
  ) = {
    this()
    if (iDimX < 1 || iDimY < 1) {
      throw new Exception("Unsupported resolution.")
    }
    if (iPixelArray.length != iDimY) {
      throw new Exception("Pixel array has wrong Y dimension.")
    }

    for (line <- iPixelArray) {
      if (line.length != iDimX) {
        throw new Exception("Pixel array has wrong X dimension.")
      }
    }

    this.dimX = iDimX
    this.dimY = iDimY
    this.pixelArray = iPixelArray
  }

  def ApplyFilter(pixelFilter: GrayscalePixelFilter): Unit = {
    for (y <- 0 until dimY) {
      for (x <- 0 until dimX) {
        pixelFilter.apply(pixelArray(y)(x))
      }
    }
  }

  def Rotate(rotationFactor: RotationFactor): Unit = {
    rotationFactor match {
      case Deg90 => this.Rotate90Deg()
      case Deg180 => {
        this.Rotate90Deg()
        this.Rotate90Deg()
      }
      case Deg270 => {
        this.Rotate90Deg()
        this.Rotate90Deg()
        this.Rotate90Deg()
      }
    }
  }

  def GetPixel(y: Int)(x: Int): GrayscalePixel = {
    pixelArray(y)(x)
  }

  def Scale(factor: ScalingFactor): Unit = {
    factor match {
      case HalfRes => this.ScaleHalfRes()
      case TwiceRes => this.ScaleTwiceRes()
      case _ => throw new Exception("Unknown scaling factor")
    }
  }

  def Flip(axisFlip: AxisFlip): Unit = {
    axisFlip match {
      case XFlip => this.FlipX()
      case YFlip => this.FlipY()
    }
  }

  private def FlipX(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[GrayscalePixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[GrayscalePixel]()
      for (x <- 0 until dimX) {
        pixelLine.addOne(pixelArray(dimY - 1 - y)(x))
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    pixelArray = pixel2DArray.toArray
  }

  private def FlipY(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[GrayscalePixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[GrayscalePixel]()
      for (x <- 0 until dimX) {
        pixelLine.addOne(pixelArray(y)(dimX - 1 - x))
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    pixelArray = pixel2DArray.toArray
  }

  private def Rotate90Deg(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[GrayscalePixel]]()
    for (x <- 0 until dimX) {
      var pixelLine = ArrayBuffer[GrayscalePixel]()
      for (y <- 0 until dimY) {
        pixelLine.addOne(pixelArray(dimY - 1 - y)(x))
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    val oldX = dimX
    val oldY = dimY
    dimX = oldY
    dimY = oldX
    pixelArray = pixel2DArray.toArray
  }

  private def ScaleTwiceRes(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[GrayscalePixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[GrayscalePixel]()
      for (x <- 0 until dimX) {
        pixelLine.addOne(pixelArray(y)(x))
        pixelLine.addOne(pixelArray(y)(x))
      }
      pixel2DArray.addOne(pixelLine.toArray)
      pixel2DArray.addOne(pixelLine.toArray)
    }
    dimX *= 2
    dimY *= 2
    pixelArray = pixel2DArray.toArray
  }

  private def ScaleHalfRes(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[GrayscalePixel]]()
    for (y <- 0 until dimY/2) {
      var pixelLine = ArrayBuffer[GrayscalePixel]()
      for (x <- 0 until dimX/2) {
        var accum: Int = pixelArray(2*y    )(2*x    ).luma
        accum +=         pixelArray(2*y + 1)(2*x    ).luma
        accum +=         pixelArray(2*y    )(2*x + 1).luma
        accum +=         pixelArray(2*y + 1)(2*x + 1).luma
        accum /= 4
        var pixel = new GrayscalePixel(0)
        pixel.luma = accum
        pixelLine.addOne(pixel)
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    dimX /= 2
    dimY /= 2
    pixelArray = pixel2DArray.toArray
  }
}
