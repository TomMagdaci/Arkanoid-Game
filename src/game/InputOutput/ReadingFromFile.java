package game.InputOutput;

import game.Counter;

import java.io.*;

public class ReadingFromFile {
 public static String readingScore() {
  BufferedReader reader = null;
  String fileName = "HighestScore.txt";
  try {
   reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

   String line;
   String[] splitLine;
   String score;
   if ((line = reader.readLine()) != null) {
    splitLine = line.split(" ");
    score = splitLine[splitLine.length - 1];
    return score;
   }
  } catch (FileNotFoundException e) { // Can't find file to open
  return null;
 }  catch (IOException e) { // Some other problem
  System.err.println("Failed reading object");
  e.printStackTrace(System.err);
  return null;
 } finally {
  try {
   if (reader != null) {
    reader.close();
   }
  } catch (IOException e) {
   System.err.println("Failed closing file: " + fileName);
  }
 }
  return null;
 }
}
