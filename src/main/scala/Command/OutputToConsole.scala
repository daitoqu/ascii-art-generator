package Command
import Image.Image
import Main.ArtGenerator
import TextOutput.ConsoleTextOutput

class OutputToConsole extends Command {
  override def Execute(target: Image): Unit = {
    val artGen = new ArtGenerator
    val art = artGen.ConvertImage(target)
    val writer = new ConsoleTextOutput
    writer.OutputText(art)
  }
}
