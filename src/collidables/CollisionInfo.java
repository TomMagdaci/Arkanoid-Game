package collidables;

import geometry.Point;
/**
 * Collision info contains the collision point and the collidable object.
 *
 * @author Tom Magdaci 316603604
 */
public class CollisionInfo {

 //Fields
 private Point collisionPoint;
 private Collidable collisionObject;

 /**
  * Constructor.
  *
  * @param collisionPoint point, the collision point
  * @param collisionObject collidable object
  */
 public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
  this.collisionPoint = collisionPoint;
  this.collisionObject = collisionObject;
 }

 /**
  * @return the point at which the collision occurred.
  */
 public Point collisionPoint() {
  return this.collisionPoint;
 }

 /**
  * @return the collidable object involved in the collision.
  */
 public Collidable collisionObject() {
  return this.collisionObject;
 }
}