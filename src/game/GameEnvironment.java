package game;

import java.util.List;
import java.util.ArrayList;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import collidables.Collidable;
import collidables.CollisionInfo;


/**
 * The GameEnvironment will be a collection of collidables.
 *
 * @author Tom Magdaci 
 */
public class GameEnvironment {

 //Fields
 //objects the ball can collide with (Block, Paddle..)
 private List<Collidable> collidables;

 /**
  * Constructor, creates a new List of collidables.
  */
 public GameEnvironment() {
  this.collidables = new ArrayList<Collidable>();
 }

 /**
  * Add the given collidable to the list.
  * @param c collidable
  */
 public void addCollidable(Collidable c) {
  this.collidables.add(c);
 }

 /**
  * Remove the given collidable from the list.
  * @param c collidable
  */
 public void removeCollidable(Collidable c) {
  this.collidables.remove(c);
 }

 /**
  * @return the list of the collidables.
  */
 public List<Collidable> getCollidable() {
  return this.collidables;
 }

 /**
  * This method gets line (stands for the movement of the object)
  * and checks if it is crossing any collidable object,
  * from the collidables list.
  *
  * @param trajectory Line of the movement of the object.
  * @return The closest collision point between the line and the collidables objects,
  *         if there is no collision point then return null.
  */
 public CollisionInfo getClosestCollision(Line trajectory) {
  //Purpose: create a new "list" consists of the closest points of each rec (collidable),
  //whereas each index in the new list is the same as in the Colliadble list
  List<Point> closestColPointForEachRec = new ArrayList<Point>();
  Rectangle r;
  List<Point> collisionPoints;
  List<Collidable> collidablesCopy = new ArrayList<>(this.collidables);
  for (Collidable c : collidablesCopy) {
   //Each collidable is a rectangle
   r = c.getCollisionRectangle();
   //For each rectangle, list of intersection points will be found
   collisionPoints = r.intersectionPoints(trajectory);
   if (collisionPoints != null) {
    //That means there are collision points between the trajectory and r.
    //Find the closest point from the collision point and keep it in a list
    closestColPointForEachRec.add(trajectory.closestIntersectionToStartOfLine(r));
   } else {
    //That means there are no collision points between the collidable and the trajectory.
    //To maintain the proper index according to the collidables list.
    closestColPointForEachRec.add(null);
   }
  }
  //Now we have list the consist the closest collision points for each collidable
  //for collidables without collision points at all (with trajectory),
  //null was filled in the closestColPointForEachRec at the same index as they are in the collidables list.
  //the list can be full of null values
  int i = 0;
  //Check whether the closescolPointForEachRec is full of nulls
   while ((i < closestColPointForEachRec.size()) && (closestColPointForEachRec.get(i) == null)) {
    i++;
   }
   if (i == closestColPointForEachRec.size()) {
    //closestColPointForEachRec is full of nulls. That means no collidable points with the trajectory
    return null;
   }
   //closestColPointForEachRec is not full of nulls, and i points to none-null index.
  for (int j = 1; j < closestColPointForEachRec.size(); j++) {
   if (closestColPointForEachRec.get(j) != null) {
    //The purpose: find the closestPoint from the closestColPointForEachRec.
    if (closestColPointForEachRec.get(j).distance(trajectory.start())
            < closestColPointForEachRec.get(i).distance(trajectory.start())) {
     i = j;
    }
   }
  }
  //index i points to the closestPoint between those in closestColPointForEachRec.
  //Reminder: The indexing order is the same in these two lists: closestColPointForEachRec, collidables.
  //That means i points also to the specific collidable with the closest point (in collidable list).
  Collidable c = this.collidables.get(i);
  Point closestCollision = closestColPointForEachRec.get(i);
  CollisionInfo newC = new CollisionInfo(closestCollision, c);
  return newC;
 }



}
