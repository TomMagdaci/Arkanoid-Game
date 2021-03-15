package game;

import biuoop.DrawSurface;
import game.InputOutput.ReadingFromFile;

public class HighScoresAnimation implements Animation {
 private int hScore;

 public HighScoresAnimation() {
  if (ReadingFromFile.readingScore() != null) {
   this.hScore = Integer.parseInt(ReadingFromFile.readingScore());
  } else {
   this.hScore = 0;
  }
 }
 @Override
 public void doOneFrame(DrawSurface d) {
  int devLeftWard = 100;
  int devDownWard = 64;
  d.drawText(d.getWidth() / 2 - devLeftWard, d.getHeight() / 2, "The high score is " + this.hScore, 32);
 }

 @Override
 public boolean shouldStop() {
  return false;
 }
}
