package Command
import Image.Image
import Main.ArtGenerator
import TextOutput.FileTextOutput

class OutputToFile(filePath: String) extends Command {
  override def Execute(target: Image): Unit = {
    val artGen = new ArtGenerator
    val art = artGen.ConvertImage(target)
    val writer = new FileTextOutput(filePath)
    writer.OutputText(art)
  }
}
