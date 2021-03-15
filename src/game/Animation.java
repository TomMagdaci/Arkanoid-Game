package game;

import biuoop.DrawSurface;

/**
 * An animation is an object that has specific time boundaries for his actions (frames).
 *
 * @author Tom Magdaci 316603604
 */
public interface Animation {

 /**
  * doOneFrame method contains set of actions for specific animation.
  * @param d DrawSurface
  */
 void doOneFrame(DrawSurface d);

 /**
  * True if it should cease from its actions, otherwise false.
  * @return boolean
  */
 boolean shouldStop();
}
