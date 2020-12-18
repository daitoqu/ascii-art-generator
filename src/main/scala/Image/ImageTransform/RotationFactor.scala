package Image.ImageTransform

sealed trait RotationFactor
case object Deg90 extends RotationFactor
case object Deg180 extends RotationFactor
case object Deg270 extends RotationFactor
