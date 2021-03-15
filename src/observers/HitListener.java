package observers;

import collidables.Block;
import sprites.Ball;

/**
 * HitListener is an object which is used to implement the Observer design pattern.
 * It is used as an observer and act in case it is informed that hit happened.
 *
 * @author Tom Magdaci
 */
public interface HitListener {

 /**
  * This method is called whenever the beingHit object is hit.
  * The hitter parameter is the Ball that's doing the hitting.
  *
  * @param beingHit hitted Block
  * @param hitter Ball
  */
 void hitEvent(Block beingHit, Ball hitter);
}
