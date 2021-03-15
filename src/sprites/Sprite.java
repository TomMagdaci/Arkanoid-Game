package sprites;

import biuoop.DrawSurface;

/**
 *  a Sprite is a game object that can be drawn to the screen
 *  (and which is not just a background image).
 *
 *  @author Tom Magdaci 316603604
 */
public interface Sprite {

 /**
  * draw the sprite to the screen.
  *
  * @param d DrawSureface
  */
 void drawOn(DrawSurface d);
 /**
  * notify the sprite that time has passed.
  */
 void timePassed();
}