package Image

import Image.ImageTransform.{AxisFlip, Deg180, Deg270, Deg90, Flipable, HalfRes, Rotatable, RotationFactor, Scalable, ScalingFactor, TwiceRes, XFlip, YFlip}
import Image.PixelFilter.PixelFilter
import Image.PixelFormat.Pixel

import scala.collection.mutable.ArrayBuffer

class Image(
                  var dimX: Int,
                  var dimY: Int,
                  var pixelArray: Array[Array[Pixel]]
                ) extends Scalable with Rotatable with Flipable
{
  def ApplyFilter(pixelFilter: PixelFilter): Unit = {
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

  def Scale(factor: ScalingFactor): Unit = {
    factor match {
      case HalfRes => this.ScaleHalfRes()
      case TwiceRes => this.ScaleTwiceRes()
    }
  }

  def Flip(axisFlip: AxisFlip): Unit = {
    axisFlip match {
      case XFlip => this.FlipX()
      case YFlip => this.FlipY()
    }
  }

  private def FlipX(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[Pixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[Pixel]()
      for (x <- 0 until dimX) {
        pixelLine.addOne(pixelArray(dimY - 1 - y)(x))
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    pixelArray = pixel2DArray.toArray
  }

  private def FlipY(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[Pixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[Pixel]()
      for (x <- 0 until dimX) {
        pixelLine.addOne(pixelArray(y)(dimX - 1 - x))
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    pixelArray = pixel2DArray.toArray
  }

  private def Rotate90Deg(): Unit = {
    var pixel2DArray = ArrayBuffer[Array[Pixel]]()
    for (x <- 0 until dimX) {
      var pixelLine = ArrayBuffer[Pixel]()
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
    var pixel2DArray = ArrayBuffer[Array[Pixel]]()
    for (y <- 0 until dimY) {
      var pixelLine = ArrayBuffer[Pixel]()
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
    var pixel2DArray = ArrayBuffer[Array[Pixel]]()
    for (y <- 0 until dimY/2) {
      var pixelLine = ArrayBuffer[Pixel]()
      for (x <- 0 until dimX/2) {
        var accum: Int = pixelArray(2*y    )(2*x    ).luminance
        accum +=         pixelArray(2*y + 1)(2*x    ).luminance
        accum +=         pixelArray(2*y    )(2*x + 1).luminance
        accum +=         pixelArray(2*y + 1)(2*x + 1).luminance
        accum /= 4
        var pixel = new Pixel(0)
        pixel.luminance = accum
        pixelLine.addOne(pixel)
      }
      pixel2DArray.addOne(pixelLine.toArray)
    }
    dimX /= 2
    dimY /= 2
    pixelArray = pixel2DArray.toArray
  }
}
