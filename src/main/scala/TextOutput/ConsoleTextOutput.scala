package TextOutput

class ConsoleTextOutput extends TextOutput {
  override def OutputText(ASCIIArt: String): Unit = {
    println(ASCIIArt)
  }
}
