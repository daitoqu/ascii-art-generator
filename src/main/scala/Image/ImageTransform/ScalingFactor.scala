package Image.ImageTransform

sealed trait ScalingFactor
case object HalfRes extends ScalingFactor
case object FullRes extends ScalingFactor
case object TwiceRes extends ScalingFactor
