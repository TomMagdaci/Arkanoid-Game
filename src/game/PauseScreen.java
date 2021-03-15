package game;

import biuoop.DrawSurface;

/**
 * WinScreen is an Animation which acts in case specific key was pressed.
 * And ceases his actions according to key press
 *
 * @author Tom Magdaci 316603604
 */
public class PauseScreen implements Animation {

 @Override
 public void doOneFrame(DrawSurface d) {
  int devLeftWard = 100;
  int devDownWard = 64;
  d.drawText(d.getWidth() / 2 - devLeftWard, d.getHeight() / 2, "Paused!", 32);
  d.drawText(d.getWidth() / 2 - devLeftWard * 3, d.getHeight() / 2 + devDownWard, "Press space to continue", 32);
 }

 @Override
 public boolean shouldStop() {
  return false;
 }
}
