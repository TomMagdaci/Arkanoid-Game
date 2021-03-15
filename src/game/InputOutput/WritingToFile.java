package game.InputOutput;

import game.Counter;

import java.io.*;

public class WritingToFile {

 public static void writingScore(Counter lastScore) {

  File file = new File("HighestScore.txt");
  PrintWriter writer = null;
  try {

   // create output stream with writer wrappers
   writer = new PrintWriter(
           new OutputStreamWriter(
                   new FileOutputStream(file)));
   writer.println("The highest score so far is: " + lastScore.getValue());
  } catch (IOException e) {
   System.err.println("Failed writing file: " + file.getName()
           + ", message:" + e.getMessage());
   e.printStackTrace(System.err);
  } finally {
   // close stream in case it was created
   if (writer != null) {
    writer.close();
   }
  }
 }
}
