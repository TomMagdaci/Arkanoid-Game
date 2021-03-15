package sprites;

import geometry.Point;
import geometry.Line;
import geometry.Velocity;
import game.GameEnvironment;
import game.GameLevel;
import collidables.CollisionInfo;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * This class creates a new Ball.
 * Ball has centerPoint, radius, velocity, color.
 *
 * @author Tom Magdaci 316603604
 */
public class Ball implements Sprite {
 //Fields
 private Point center;
 private int r;
 private java.awt.Color color;
 private Velocity v; //Ball's velocity The regular constructor does not fill this field.
 private GameEnvironment game; //Ball object should be guided by the obstacles in the game
 private Line trajectory;


 /**
  * Constructor with configurable point, size, color.
  *
  * @param center center's ball point
  * @param r      radius of the ball
  * @param color  color of the ball
  */
 public Ball(Point center, int r, java.awt.Color color) {
  this.center = center;
  this.r = r;
  this.color = color;
 }

 /**
  * Constructor with configurable x y axises, size, color.
  *
  * @param xCenter center's ball value on the x axis
  * @param yCenter center's ball value on the y axis
  * @param r       radius of the ball
  * @param color   color of the ball
  */
 public Ball(double xCenter, double yCenter, int r, java.awt.Color color) {
  this.center = new Point(xCenter, yCenter);
  this.r = r;
  this.color = color;
 }

 /**
  * @return value of the center of the ball in the X axis.
  */
 public int getX() {
  return ((int) this.center.getX());
 }

 /**
  * @return value of the center of the ball in the Y axis.
  */
 public int getY() {
  return ((int) this.center.getY());
 }

 /**
  * @return Radius of the ball.
  */
 public int getSize() {
  return this.r;
 }

 /**
  * @return the ball's color.
  */
 public java.awt.Color getColor() {
  return this.color;
 }

 /**
  * change the ball's color.
  *
  * @param c a new color
  */
 public void changeColor(java.awt.Color c) {
  this.color = c;
 }

 /**
  * Implementing Sprite interface.
  * Draw the ball on the given DrawSurface.
  *
  * @param surface The surface on which the ball will be drawn
  */
 public void drawOn(DrawSurface surface) {
  //Explicit casting is needed because fillCircle accept (int) and Point.(x/y) are in (double)
  int xCenter = (int) this.center.getX();
  int yCenter = (int) this.center.getY();
  surface.setColor(color);
  surface.fillCircle(xCenter, yCenter, r);
  surface.setColor(Color.black);
  surface.drawCircle(xCenter, yCenter, r);
 }

 /**
  * Implementing of the Sprite interface
  * commits moveOneStep() method.
  */
 public void timePassed() {
  this.moveOneStep();
 }

 //Setters:
 /**
  * This method fill the velocity of this object.
  *
  * @param v1 velocity object
  */
 public void setVelocity(Velocity v1) {
  this.v = v1;
  computeLineTrajectory();
 }

 /**
  * This method fill the velocity of this object.
  *
  * @param dx delta x for the velocity object.
  * @param dy delta y for the velocity object.
  */
 public void setVelocity(double dx, double dy) {
  this.v = new Velocity(dx, dy);
  computeLineTrajectory();
 }

 /**
  * This method computes the line trajectory of the ball, after a velocity was set.
  */
 public void computeLineTrajectory() {
  this.trajectory = new Line(this.center.getX(), this.center.getY(),
          this.center.getX() + this.v.getDX(), this.center.getY() + this.v.getDY());
 }

 /**
  * This method fills the GameEnvironment of this object.
  *
  * @param newGameEnv GameEnvironment object
  */
 public void setGameEnvironment(GameEnvironment newGameEnv) {
  this.game = newGameEnv;
 }

 /**
  * This method adds this.ball as Sprite to specific game.
  * @param g Game
  */
 public void addToGame(GameLevel g) {
  g.addSprite(this);
  this.setGameEnvironment(g.getGameEnvironment());
 }

 //New Getters:
 /**
  * Return the object's velocity.
  *
  * @return the velocity of the ball
  */
 public Velocity getVelocity() {
  return this.v;
 }

 /**
  * Return the object's line trajectory.
  *
  * @return the velocity of the ball
  */
 public Line getLineTrajectory() {
  return this.trajectory;
 }

 /**
  * Applying the object's velocity on the center point.
  */
 public void moveOneStep() {
  //Collision check will be commited by game.getClosestCollision() method in GameEnv class
  CollisionInfo colInf = this.game.getClosestCollision(this.trajectory);
  if (colInf == null) {
   this.center = this.getVelocity().applyToPoint(this.center);
   computeLineTrajectory();
   return;
  }
  //Closest Collision point has been found
  //This point lays on the trajectory line.
  //We will move the ball on the trajectory line to be in distance of one radius from the collision point.
  //We assume the ball will be small enough for this to look good
  Velocity newV;
  double distance, dProportion;
  //This point in which the ball will be moved called newC.
  Point newC;
  //We will compute the adjusted dxToNewC and dyToNewC (to newC).
  double dxToNewC, dyToNewC;
  Point colPoint = colInf.collisionPoint();
  distance = this.center.distance(colPoint);
  //If the distance < radius that means the collision point is on the ball's area
  //because of that, we have to change the velocity.
  if (distance >= this.r) {
   //The collision point has x,y. Therefore dx and dy to this point can be calculated,
   //with these dx and dy we have right triangle and his two sides dx , dy to col point
   //and the hypotenuse is the distance from the center point to the col point.
   //this hypotenuse is one radius long then the distance from the center point to the newC point
   //This difference is used to create a dProportion, and to compute the dxToNewC and dyToNewC.
   dProportion = (distance - this.r) / distance;
   dxToNewC = dProportion * (colPoint.getX() - this.center.getX());
   dyToNewC = dProportion * (colPoint.getY() - this.center.getY());
   newC = new Point(this.center.getX() + dxToNewC, this.center.getY() + dyToNewC);
   this.center = newC;
  }
  //new Velocity will be calculated using the hit() method of the colliable object
  newV = colInf.collisionObject().hit(this, colInf.collisionPoint(), this.v);
  this.setVelocity(newV);
 }

 /**
  * Applying the object's velocity within specific boundries.
  * Used in case the start point is (x,y) (where x,y != 0)
  *
  * @param xEnd boundary of the Gui
  * @param yEnd boundary of the Gui
  * @param xStart boundary of the Gui
  * @param yStart boundary of the Gui
  */
 public void moveOneStep(int xStart, int yStart, int xEnd, int yEnd) {
  //method moveOneStep in case the Gui not starts in 0,0.
  //x,yStart is the upperLeft Point of the Gui
  //x,yEnd is the lowerRight of the Gui
  Point newC;
  newC = changeDirecion(xStart, yStart, xEnd, yEnd);
  if (newC == this.center) {
   this.center = this.getVelocity().applyToPoint(this.center);
  } else {
   this.center = newC;
  }
 }

 /**
  * Applying the object's velocity within specific boundries.
  * Used in case the start point is (0,0)
  * and therefore the given data will be width and height only.
  *
  * @param width width of the Gui that used as boundary to bouncing ball
  * @param height height of the Gui that used as boundary to bouncing ball
  */
 public void moveOneStep(int width, int height) {
  //method moveOneStep in case the Gui starts in 0,0.
  int xEndGui = width;
  int yEndGui = height;
  int xStartGui = 0;
  int yStartGui = 0;
  Point newC;
  newC = changeDirecion(xStartGui, yStartGui, width, height);
  if (newC == this.center) {
   this.center = this.getVelocity().applyToPoint(this.center);
  } else {
   this.center = newC;
  }
 }

 /**
  * Check whether the ball reached to the boundaries in the 'x'/'y' axis.
  *
  * @param xEnd boundary of the Gui
  * @param yEnd boundary of the Gui
  * @param xStart boundary of the Gui
  * @param yStart boundary of the Gui
  * @return true if the ball reached the boundries, otherwise false.
  */
 public Point changeDirecion(int xStart, int yStart, int xEnd, int yEnd) {
  int cD = -1; //a constant, which when one multiply it causes to change direction
  Point newC = this.center;
  Velocity newV = this.getVelocity();
  double dProportion;
  double xNew, yNew, tempNewDX, tempNewDY;
  //check from the right and left sides
  if ((newC.getX() + this.r + newV.getDX()) > xEnd) {
   xNew = xEnd - this.r; //Define Max x in this fixed boundaries
   tempNewDX = xNew - newC.getX(); //Define the current fixed DX.
   //Delta proportion = RealDX / DX. (RealDX = xNew - x)
   dProportion = tempNewDX / newV.getDX(); //Define the relation in the change of the DX/
   tempNewDY = dProportion * newV.getDY(); //Implement this change on the DY.
   newC = new Point(xNew, newC.getY() + tempNewDY); //Define new center point with X,Y with the new DX,DY accordingly
   newV = new Velocity(0, tempNewDY); //checking on DY should be continued.
   this.setVelocity(cD * (this.v.getDX()), this.v.getDY());
  }
  if ((newC.getX() - this.r + newV.getDX()) < xStart) {
   //Similar to the preivious "if" but
   xNew = xStart + this.r;
   tempNewDX = xNew - newC.getX();
   dProportion = tempNewDX / newV.getDX();
   tempNewDY = dProportion * newV.getDY();
   newC = new Point(xNew, newC.getY() + tempNewDY);
   newV = new Velocity(0, tempNewDY);
   this.setVelocity(cD * (this.v.getDX()), this.v.getDY());
  }
  if ((newC.getY() + this.r + newV.getDY()) > yEnd) {
   yNew = yEnd - this.r;
   tempNewDY = yNew - newC.getY();
   dProportion = tempNewDY / newV.getDY();
   tempNewDX = dProportion * newV.getDX();
   newC = new Point(newC.getX() + tempNewDX, yNew);
   newV = new Velocity(tempNewDX, 0);
   this.setVelocity(this.v.getDX(), cD * (this.v.getDY()));
  }
  if ((newC.getY() - this.r + newV.getDY()) < yStart) {
   yNew = yStart + this.r;
   tempNewDY = yNew - newC.getY();
   dProportion = tempNewDY / newV.getDY();
   tempNewDX = dProportion * newV.getDX();
   newC = new Point(newC.getX() + tempNewDX, yNew);
   newV = new Velocity(tempNewDX, 0);
   this.setVelocity(this.v.getDX(), cD * (this.v.getDY()));
  }
  return newC;
 }

 /**
  * Remove this Ball from the requested game.
  * @param newGameLevel Game
  */
 public void removeFromGame(GameLevel newGameLevel) {
  newGameLevel.removeSprite(this);
 }
}
