import TextOutput.FileTextOutput
import org.scalatest.FunSuite

import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter}
import java.util.Calendar

class FileOutputTests extends FunSuite {
  test("Output to a text file"){
    val now = Calendar.getInstance().getTime()
    val filePath = "files/test_text.txt"
    val out = new FileTextOutput(filePath)
    val text = "This is a test text " + now.toString
    out.OutputText(text)

    val lines = scala.io.Source.fromFile(filePath).mkString
    assert(lines == text)
  }
}
