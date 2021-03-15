package collidables;

import observers.HitListener;

/**
 * HitNotifier is an object which is used to implement the Observer design pattern.
 * It is used as observable and inform the requested address in case of hit.
 *
 * @author Tom Magdaci 316603604
 */
public interface HitNotifier {

 /**
  * Add hl as a listener to hit events.
  *
  * @param hl HitListener
  */
 void addHitListener(HitListener hl);

 /**
  * Remove hl from the list of listeners to hit events.
  *
  * @param hl HitListener
  */
 void removeHitListener(HitListener hl);
}