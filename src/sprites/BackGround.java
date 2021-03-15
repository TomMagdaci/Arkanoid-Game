package sprites;

import biuoop.DrawSurface;
import game.AnimationRunner;

import java.awt.Color;

/**
 * BackGround is a sprite that is used as a background in game.
 *
 * @author Tom Magdaci
 */
public class BackGround implements Sprite {

 //Fields
 private Color color;

 /**
  * Constructor with configurable color.
  * @param c color.
  */
 public BackGround(Color c) {
  this.color = c;
 }

 /**
  * Returns the background color.
  * @return color.
  */
 public Color getColor() {
  return this.color;
 }
 @Override
 public void drawOn(DrawSurface d) {
  d.setColor(this.color);
  d.fillRectangle(0, 0, AnimationRunner.WIDTH, AnimationRunner.HEIGHT);
 }

 @Override
 public void timePassed() {
 }
}
