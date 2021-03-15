import geometry.Point;
import geometry.Velocity;
import sprites.Ball;


import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 *
 */
public class BouncingBallAnimation {

 //Animation parameters.
 //GUI paramteres:
 static final int TIMESLEEP = 50;
 static final int WIDTH = 400;
 static final int HEIGHT = 400;
 //Ball parameters:
 static final int RADIUS = 30;
 static final int ANGLE = 20;
 static final int SPEED = 10;

 /**
  * Creates and run the ball's animation. First the gui is created.
  * Then a ball (with point and velocity) is created according to given parameters.
  * After that an animation run.
  * @param start start point of the ball
  * @param dx delta in the x axis (used for velocity creating)
  * @param dy delta in the x axis (used for velocity creating)
  */
 public static void drawAnimation(Point start, double dx, double dy) {
  GUI gui = new GUI("title", WIDTH, HEIGHT);
  Sleeper sleeper = new Sleeper();
  Ball ball = new Ball(start.getX(), start.getY(), RADIUS, java.awt.Color.BLACK);
  Velocity v = Velocity.fromAngleAndSpeed(ANGLE, SPEED);
  ball.setVelocity(v);
  //ball.setVelocity(dx, dy);
  while (true) {
   ball.moveOneStep(WIDTH, HEIGHT);
   DrawSurface d = gui.getDrawSurface();
   ball.drawOn(d);
   gui.show(d);
   sleeper.sleepFor(TIMESLEEP);  // wait for "TIMESLEEP" milliseconds.
  }
 }

 /**
  * Main method: interpret the string[] args into ball's parameters.
  * Then define new animation object and send the ball's parameters to it.
  *
  * @param args array of ball's parameters (point, dx, dy).
  */
 public static void main(String[] args) {
  double xStart = Double.parseDouble(args[0]);
  double yStart = Double.parseDouble(args[1]);
  double dx = Double.parseDouble(args[2]);
  double dy = Double.parseDouble(args[3]);
  drawAnimation(new Point(xStart, yStart), dx, dy);
  //drawAnimation(new Point(200,200),3,4);
 }
}
