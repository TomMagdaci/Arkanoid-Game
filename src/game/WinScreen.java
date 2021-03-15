package game;

import biuoop.DrawSurface;

/**
 * WinScreen is an Animation which acts in case the user win the game.
 *
 * @author Tom Magdaci 
 */
public class WinScreen implements Animation {

 private Counter score;

 /**
  * Constructor with configurable counter.
  * @param sc Counter score
  */
 public WinScreen(Counter sc) {
  this.score = sc;
 }

 @Override
 public void doOneFrame(DrawSurface d) {
  int devLeftWard = 100;
  int devDownWard = 64;
  d.drawText(d.getWidth() / 2 - devLeftWard, d.getHeight() / 2, "You Won!", 32);
  d.drawText(d.getWidth() / 2 - devLeftWard, d.getHeight() / 2 + devDownWard,
          "Your score is " + this.score.getValue(), 32);
 }

 @Override
 public boolean shouldStop() {
  return false;
 }
}
