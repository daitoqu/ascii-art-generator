package TextOutput

import java.io.{BufferedWriter, File, FileWriter}

class FileTextOutput(var filePath: String) extends TextOutput {
  override def OutputText(ASCIIArt: String): Unit = {
    val writer = new BufferedWriter(new FileWriter(filePath))
    writer.write(ASCIIArt)
    writer.close()
  }
}
