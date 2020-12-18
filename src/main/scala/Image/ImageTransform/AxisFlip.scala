package Image.ImageTransform

sealed trait AxisFlip
case object XFlip extends AxisFlip
case object YFlip extends AxisFlip