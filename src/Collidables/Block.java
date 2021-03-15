package collidables;

import observers.HitListener;
import sprites.Ball;
import sprites.Sprite;

import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Velocity;
import game.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Block is a collidable object in shape of a rectangle.
 *
 * @author Tom Magdaci 316603604
 */
public class Block implements Collidable, Sprite, HitNotifier {

 private List<HitListener> hitListeners;
 private Rectangle rec;
 private java.awt.Color color;

 /**
  * Constructor with configurable upper Left point and
  * the width and the height of the rectangle, and with color.
  *
  * @param upperLeft point of the rectangle
  * @param width width of the rectangle
  * @param height height of the rectangle
  * @param color color of the block
  */
 public Block(Point upperLeft, double width, double height, java.awt.Color color) {
  this.rec = new Rectangle(upperLeft, width, height);
  this.color = color;
  this.hitListeners = new LinkedList<>();
 }

 /**
  * Constructor with configurable upper Left point and
  * the width and the height of the rectangle.
  * this constructor will creates a block with default color (black).
  *
  * @param upperLeft point of the rectangle
  * @param width width of the rectangle
  * @param height height of the rectangle
  */
 public Block(Point upperLeft, double width, double height) {
  this.rec = new Rectangle(upperLeft, width, height);
  this.color = Color.BLACK;
  this.hitListeners = new LinkedList<>();
 }

 /**
  * @return the block's rectangle.
  */
 @Override
 public Rectangle getCollisionRectangle() {
  return this.rec;
 }

 /**
  * Changes the block's rectangle.
  *
  * @param r new Rectangle
  */
 public void setRectange(Rectangle r) {
  this.rec = r;
 }

 /**
  * Changes the moving object's velocity,
  * according to the collision point and ts current velocity.
  *
  * @param collisionPoint the collision point
  * @param currentVelocity the current vel of the moving object
  * @param hitter Ball
  * @return new Velocity.
  */
 @Override
 public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
  this.notifyHit(hitter);
  int changeD = -1;
  Velocity newV = currentVelocity;
  //First we create 4 lines from the Block (rectangle) sides
  //"create4LinesFromRecSides()" returns array with 4 lines,
  //whereas its indexes are arranged that way: 0 - LEFT, 1 - TOP, 2 - BOTTOM, 3 - RIGHT.
  Line[] recLines = this.rec.create4LinesFromRecSides();
  //Check whether the collision happened on the vertical sides.
  if (recLines[0].isOnLine(collisionPoint) || recLines[3].isOnLine(collisionPoint)) {
   newV = new Velocity(changeD * newV.getDX(), newV.getDY());
  }
  //Check whether the collision point is on the horizontal lines
  if (recLines[1].isOnLine(collisionPoint) || recLines[2].isOnLine(collisionPoint)) {
   newV = new Velocity(newV.getDX(), changeD * newV.getDY());
  }
  return newV;
 }

 /**
  * Draw the block on the given DrawSurface.
  *
  * @param surface The surface on which the ball will be drawn
  */
 @Override
 public void drawOn(DrawSurface surface) {
  surface.setColor(color);
  surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
          (int) this.rec.getWidth(), (int) this.rec.getHeight());
  surface.setColor(Color.BLACK);
  surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
          (int) this.rec.getWidth(), (int) this.rec.getHeight());
 }

 /**
  * void.
  */
 @Override
 public void timePassed() {
 }

 /**
  * @return the block's color.
  */
 public java.awt.Color getColor() {
  return this.color;
 }

 /**
  * change the block's color.
  *
  * @param c a new color
  */
 public void changeColor(java.awt.Color c) {
  this.color = c;
 }

 /**
  * This method adds this.block as Sprite and as collidable to specific game.
  * @param g Game
  */
 public void addToGame(GameLevel g) {
  g.addCollidable(this);
  g.addSprite(this);
 }

 /**
  * Add HitListener (an observer).
  * @param hl HitListener
  */
 public void addHitListener(HitListener hl) {
  this.hitListeners.add(hl);
 }

 /**
  * Remove HitListener (an observer).
  * @param hl HitListener
  */
 public void removeHitListener(HitListener hl) {
  this.hitListeners.remove(hl);
 }

 /**
  * Notify the observers that hit happened.
  * @param hitter Ball
  */
 private void notifyHit(Ball hitter) {
  // Make a copy of the hitListeners before iterating over them.
  List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
  // Notify all listeners about a hit event:
  for (HitListener hl : listeners) {
   hl.hitEvent(this, hitter);
  }
 }

 /**
  * Remove this Block from the requested game.
  * @param gameLevel Game
  */
 public void removeFromGame(GameLevel gameLevel) {
  gameLevel.removeCollidable(this);
  gameLevel.removeSprite(this);
 }
}
