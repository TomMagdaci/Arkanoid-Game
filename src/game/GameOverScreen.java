package game;

import biuoop.DrawSurface;

/**
 * GameOverScreen is an Animation which acts in case the user lost the game.
 *
 * @author Tom Magdaci 316603604
 */
public class GameOverScreen implements Animation {

 private Counter score;

 /**
  * Constructor with Counter configurable.
  * @param sc Counter
  */
 public GameOverScreen(Counter sc) {
  this.score = sc;
 }


 @Override
 public void doOneFrame(DrawSurface d) {
  int devLeftWard = 100;
  int devDownWard = 64;
  d.drawText(d.getWidth() / 2 - devLeftWard, d.getHeight() / 2, "Game Over!", 32);
  d.drawText(d.getWidth() / 2 - devLeftWard, d.getHeight() / 2 + devDownWard,
          "Your score is " + this.score.getValue(), 32);
 }

 @Override
 public boolean shouldStop() {
  return false;
 }
}