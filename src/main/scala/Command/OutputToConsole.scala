package Command
import Image.Image
import Main.ArtGenerator

class OutputToConsole extends Command {
  override def Execute(target: Image): Unit = {
    val artGen = new ArtGenerator
    val art = artGen.ConvertImage(target)
    print(art)
  }
}
