package collidables;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * Collidable objects are things that can be collided with.
 *
 * @author Tom Magdaci 
 */
public interface Collidable {

 /**
  * @return the "collision shape" of the object.
  */
 Rectangle getCollisionRectangle();
 /**
  * The object that we have collided with it at collision Point has a given velocity,
  * therefore its velocity should be changed after the hit.
  *
  * @param collisionPoint the collision point
  * @param currentVelocity the current vel of the moving object
  * @param hitter Ball
  * @return the new velocity of the moving object after the hit occurred.
  */
 Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
