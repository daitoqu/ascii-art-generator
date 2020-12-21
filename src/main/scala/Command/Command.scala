package Command

import Image.Image

trait Command {
  def Execute(target: Image)
}
