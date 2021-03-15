package sprites;

import biuoop.DrawSurface;
import java.util.List;

import game.AnimationRunner;
import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Velocity;
import collidables.Collidable;
import collidables.Block;
import game.GameEnvironment;
import game.GameLevel;

/**
 * The Paddle is a moving block (which is in shape of rectangle).
 * It is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 *
 * @author Tom Magdaci 316603604
 */
public class Paddle implements Sprite, Collidable {

 //Fields
 private biuoop.KeyboardSensor keyboard;
 private Block delegate;
 private GameEnvironment gameEnvironment;
 private Line[] regions;
 private int speed;

 //The paddle upper side is composed of regions with different traits.
 static final int REGIONSNUM = 5;
 //The paddle is created with default speed.
 //It is possible to use setSpeed() method to change it
 static final int DEFAULTSPEED = 7;

 /**
  * Constructor with configurable upperLeft point and the width and the heigh of the rectangle,
  * and with color.
  * Also a keyboard should be provided.
  *
  * @param upperLeft upperLeft point of the rectangle
  * @param width width of the rectangle
  * @param height height of the rectangle
  * @param color color of the paddle.
  * @param keyboard specific keyboard
  */
 public Paddle(Point upperLeft, double width, double height, java.awt.Color color, biuoop.KeyboardSensor keyboard) {
  this.delegate = new Block(upperLeft, width, height, color);
  this.keyboard = keyboard;
  this.regions = new Line[REGIONSNUM];
  regionsComputing();
  this.speed = DEFAULTSPEED;
 }

 /**
  * Constructor with configurable upperLeft point and the width and the heigh of the rectangle,
  * Also a keyboard should be provided.
  * This constructor does not get color therefore
  * it uses the block's constructor that provided default color.
  *
  * @param upperLeft upperLeft point of the rectangle
  * @param width width of the rectangle
  * @param height height of the rectangle
  * @param keyboard specific keyboard
  */
 public Paddle(Point upperLeft, double width, double height, biuoop.KeyboardSensor keyboard) {
  this.delegate = new Block(upperLeft, width, height);
  this.keyboard = keyboard;
  this.regions = new Line[REGIONSNUM];
  regionsComputing();
  this.speed = DEFAULTSPEED;
 }

 /**
  * Constructor with configurable block and specific keyboard.
  *
  * @param b block
  * @param keyboard specific keyboard
  */
 public Paddle(Block b, biuoop.KeyboardSensor keyboard) {
  this.delegate = b;
  this.keyboard = keyboard;
  this.regions = new Line[REGIONSNUM];
  regionsComputing();
  this.speed = DEFAULTSPEED;
 }

 /**
  * The upper side of the paddle should be divided to 5 regions according to the assignment requirements
  * Each one of them will be in equal length.
  * They will be used in the velocity calculation of the hit() method.
  */
 private void regionsComputing() {
  //The upperLine of the paddle should be divided into regions.
  int regionsNum = REGIONSNUM;
  //length of the upperLine
  double paddleLength = this.delegate.getCollisionRectangle().getUpperLeft().
          distance(this.delegate.getCollisionRectangle().getUpperRight());
  //Segment's length for each region will be calculated
  double segLentgh = paddleLength / REGIONSNUM;
  Line upperPaddleLine = new Line(this.delegate.getCollisionRectangle().getUpperLeft(),
          this.delegate.getCollisionRectangle().getUpperRight());
  for (int i = 0; i < this.regions.length; i++) {
   this.regions[i] = new Line(upperPaddleLine.start().getX() + i * segLentgh, upperPaddleLine.start().getY(),
           this.delegate.getCollisionRectangle().getUpperLeft().getX()
                   + (i + 1) * segLentgh, upperPaddleLine.start().getY());
  }
 }

 /**
  * According to the user command if the left key is pushed then,
  * this method copies the paddle left according to its speed and the gui boundaries.
  *
  */
 public void moveLeft() {
  if (keyboard.isPressed(this.keyboard.LEFT_KEY)) {
   Point currentUpperLeft = this.delegate.getCollisionRectangle().getUpperLeft();
   Point currentUpperRight = this.delegate.getCollisionRectangle().getUpperRight();
   //Line trajectory is created according to the paddle speed and upper left point
   Line trajectory = new Line(currentUpperLeft,
           new Point(currentUpperLeft.getX() - this.speed, currentUpperLeft.getY()));
   //It will be checked whether the trajectory is intersect with collidables objects.
   //These collidables object can be the gui's boundaries (walls)
   if (!(checkCollision(trajectory))) {
    //The trajectory length is less than the distance between the paddle's UpperLeftPoint and the boundaries.
    //That means the paddle can move according to its trajectory.
    computeNewRectangle(new Point(currentUpperLeft.getX() - this.speed, currentUpperLeft.getY()));
   } else {
    //The trajectory length is at least the distance between the paddle's UpperLeftPoint and the boundaries.
    //Therefore the paddle will move to its limit of movement from the left
    computeNewRectangle(new Point(GameLevel.WALLTHICKNESS, currentUpperLeft.getY()));
   }
  }
 }

 /**
  * According to the user command if the right key is pushed then,
  * this method copies the paddle right according to its speed and the gui boundaries.
  *
  */
 public void moveRight() {
  if (keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
   Point currentUpperLeft = this.delegate.getCollisionRectangle().getUpperLeft();
   Point currentUpperRight = this.delegate.getCollisionRectangle().getUpperRight();
   //Line trajectory is created according to the paddle speed and upperRight point
   Line trajectory = new Line(currentUpperRight,
           new Point(currentUpperRight.getX() + this.speed, currentUpperRight.getY()));
   //It will be checked whether this trajectory is intersect with collidables objects.
   //These collidables object can be the gui's boundaries (walls).
   if (!(checkCollision(trajectory))) {
    //The trajectory length is less than the distance between the paddle's UpperRight point and the boundaries.
    //That means the paddle can move according to its trajectory.
    computeNewRectangle(new Point(currentUpperLeft.getX() + this.speed, currentUpperLeft.getY()));
   } else {
    //The trajectory length is at least the distance between the paddle's UpperRight point and the boundaries.
    //Therefore the paddle will move to its limit of movement from the right
    computeNewRectangle(new Point(((double) (AnimationRunner.WIDTH - GameLevel.WALLTHICKNESS)
            - this.delegate.getCollisionRectangle().getWidth()), currentUpperLeft.getY()));
   }
  }
 }

 /**
  * Checks if a given paddle's trajectory line is crossing with collidable objects.
  *
  * @param trajectory Line
  * @return true if its trajectory is crossing with collidable object, otherwise false.
  */
 private boolean checkCollision(Line trajectory) {
  //Create the paddle rectangle and upperline
  Rectangle paddleRec = this.delegate.getCollisionRectangle();
  //get collidables list
  List<Collidable> collidables = this.gameEnvironment.getCollidable();
  List<Point> intersectionPoints = null;
  //check for every collidable item if its the paddle
  //if not check whether the paddle's upperline is intersected with it
  for (Collidable c : collidables) {
   Rectangle r = c.getCollisionRectangle();
   if (paddleRec != r) {
    intersectionPoints = r.intersectionPoints(trajectory);
    if (intersectionPoints != null) {
     return true;
    }
   }
  }
  return false;
 }

 /**
  * Creates a new rectangle for the paddle according to a given upperLeft point,
  * the same width and height from the current rectangle are used.
  * Then, changes the paddle's rectangle to be the new rectangle.
  *
  * @param upperLeft point of the paddle's rectangle.
  */
 private void computeNewRectangle(Point upperLeft) {
  Point currentUpperLeft = this.delegate.getCollisionRectangle().getUpperLeft();
  Rectangle r = new Rectangle(upperLeft, this.delegate.getCollisionRectangle().getWidth(),
          this.delegate.getCollisionRectangle().getHeight());
  this.delegate.setRectange(r);
  regionsComputing();
 }

 /**
  * Checks if the paddle should change its position before it will be drawn again.
  */
 @Override
 public void timePassed() {
  this.moveLeft();
  this.moveRight();
 }

 /**
  * Draw the paddle on the surface.
  *
  * @param d DrawSureface
  */
 public void drawOn(DrawSurface d) {
  this.delegate.drawOn(d);
 }

 /**
  * @return the paddle's rectangle.
  */
 @Override
 public Rectangle getCollisionRectangle() {
  return this.delegate.getCollisionRectangle();
 }

 /**
  *  This method computes the velocity of the new velocity of the ball after the collision.
  *  Collision point and Current velocity are used to this computation.
  * @param collisionPoint Point
  * @param currentVelocity Velocity
  * @param hitter Ball
  * @return Velocity, after hitting.
  */
 public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
  double startAngle = -60;
  double deltaAngle = 30;
  int changeD = -1;
  int flag = 0;
  Velocity newV = currentVelocity;
  //The upperLine of the paddle is divided into 5 regions
  //It will be checked on which region the collision point lays
  //Then the velocity will be calculated accordingly.
  for (int i = 0; i < this.regions.length; i++) {
   if (regions[i].isOnLine(collisionPoint)) {
    if (startAngle + deltaAngle * i != 0) {
     //That means the hit point lays o region (1, 2, 4 or 5)
     //According to the assignment requirements.
     //There is a fixed angle that the ball's velocity angle should be changed to.
     //The speed should be the same as it was before the hit occured.
     newV = Velocity.fromAngleAndSpeed(startAngle + deltaAngle * i, currentVelocity.getSpeed());
     flag = 1;
     break;
    } else { //startAngle + deltaAngle * i == 0
     //The hit point lays on region 3, therefore only the verital direction should be changed
     newV = new Velocity(currentVelocity.getDX(), changeD * currentVelocity.getDY());
     flag = 1;
     break;
    }
   }
  }
  if (flag == 0) {
   //In case the collision point lays on the other lines of the paddle and not the upper line.
   return this.delegate.hit(hitter, collisionPoint, currentVelocity);
  }
  return newV;
 }

 /**
  * Add this paddle to the game.
  *
  * @param g Game.
  */
 public void addToGame(GameLevel g) {
  g.addCollidable(this);
  g.addSprite(this);
  this.gameEnvironment = g.getGameEnvironment();
 }

 /**
  * Set a new speed to the paddle.
  *
  * @param s new speed for the paddle.
  */
 public void setSpeed(int s) {
  this.speed = s;
 }
}