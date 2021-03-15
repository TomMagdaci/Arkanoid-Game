//ID: 316603604

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

import geometry.Velocity;
import sprites.Ball;


/**
 * This class generate random bouncing balls.
 * and give them velocities according to their sizes.
 */
public class MultipleBouncingBallsAnimation {

 //Animation parameters.
 //GUI paramteres:
 static final int TIMESLEEP = 50;
 static final int WIDTH = 400;
 static final int HEIGHT = 400;
 //Ball parameters:
 static final int MAXSPEED = 20;
 static final int MAXDEGREE = 360;
 static final int MINSPEED = 2;
 static final int SLOWBALL = 50;

 /**
  * Generate a new ball with a required size and random center point.
  *
  * @param size size of a ball
  * @param rand random numbers generator
  * @param xStart x's value of the Gui's start point
  * @param yStart y's value of the Gui's start point
  * @param xEnd x's value of the Gui's end point
  * @param yEnd y's value of the Gui's end point
  * @return new ball with random center point and the required size
  */
 public Ball generateRandomBall(int size, Random rand, int xStart, int yStart, int xEnd, int yEnd) {
  double x = (double) (rand.nextInt(xEnd - xStart) + xStart + size);
  double y = (double) (rand.nextInt(yEnd - yStart) + yStart + size);
  Ball newB = new Ball(x, y, size, Color.black);
  return newB;
 }

 /**
  * This method creates a new velocity according to the ball's size.
  * The purpose is: big balls will get slow velocity and little ones get faster velocity.
  *
  * @param ballsArray array of balls
  * @param i int (index in the array)
  * @param rand random numbers generator
  * @return Velocity
  */
 private Velocity defineVelToBall(Ball[] ballsArray, int i, Random rand) {
  //Creates new velocity with null value and fill it accordingly
  Velocity v;
  double vProportion, speed;
  //In case the ball's size is 50 and above
  if ((ballsArray[i].getSize() >= SLOWBALL)) {
   v = Velocity.fromAngleAndSpeed(((double) rand.nextInt(MAXDEGREE)), MINSPEED);
   return v;
  }
  //For balls with sizes lower than 50, proportion will be calculated (between its size and the SLOWBALL's size)
  //then speed will be calculated (based on the proportion) and with the limit of MAXSPEED.
  vProportion = (SLOWBALL / ballsArray[i].getSize());
  speed = Math.min(vProportion * MINSPEED, MAXSPEED);
  v = Velocity.fromAngleAndSpeed(((double) rand.nextInt(MAXDEGREE)), speed);
  return v;
 }

 /**
  * This method creates new array of balls and fills its cells with new balls.
  *
  *
  * @param sizeArray array of integers
  * @param xStart x's value of the Gui's start point
  * @param yStart y's value of the Gui's start point
  * @param xEnd x's value of the Gui's end point
  * @param yEnd y's value of the Gui's end point
  * @return array of balls (sorted according to the balls' sizes).
  */
 public Ball[] createBallsArray(int[] sizeArray, int xStart, int yStart, int xEnd, int yEnd) {
  Random rand = new Random(); // create a random-number generator
  //Creates a new array of balls with the same size as the array of sizes.
  Ball[] ballsArray = new Ball[sizeArray.length];
  //Create new velocity (null) and fill that in a loop according to the balls sizes'
  Velocity v;
  for (int i = 0; i < ballsArray.length; i++) {
   //For each cell helper method is used in which a new ball is created.
   ballsArray[i] = generateRandomBall(sizeArray[i], rand, xStart, yStart, xEnd, yEnd);
   //For each ball helper method is used in which a new velocity is created.
   v = defineVelToBall(ballsArray, i, rand);
   ballsArray[i].setVelocity(v);
  }
  return ballsArray;
 }

 /**
  * This method purpose is to create array of balls according to
  * given array of sizes (sorted in ascending order) and draw it into an animation.
  * It uses some helpers methods.
  *
  * @param sizeArray array of integers
  */
 public void drawAnimation(int[] sizeArray) {
  //Define gui's start and end points.
  int xStart = 0, yStart = 0, xEnd = WIDTH, yEnd = HEIGHT;
  //Create new Gui and sleeper.
  GUI gui = new GUI("title", WIDTH, HEIGHT);
  Sleeper sleeper = new Sleeper();
  //Create array of balls according to the array of sizes (Each ball will get velocity)
  //and according to the gui's boundaries
  Ball[] ballsArray = createBallsArray(sizeArray, xStart, yStart, xEnd, yEnd);
  while (true) {
   DrawSurface d = gui.getDrawSurface();
   //In a loop each ball in the array will moveOneStep accroding to its velocity
   for (int i = 0; i < ballsArray.length; i++) {
    ballsArray[i].moveOneStep(WIDTH, HEIGHT);
    ballsArray[i].drawOn(d);
   }
   gui.show(d);
   sleeper.sleepFor(TIMESLEEP);  // wait for 50 milliseconds.
  }
 }

 /**
  * Replace values of two cells in the array according to given two indexes.
  *
  * @param sizeArray array of integers
  * @param a index in the array
  * @param b index in the array
  */
 public void swapInArray(int[] sizeArray, int a, int b) {
  int temp = sizeArray[a];
  sizeArray[a] = sizeArray[b];
  sizeArray[b] = temp;
 }

 /**
  * This method (base on bubble sort alg) sorts the array into ascending order.
  *
  * @param sizeArray array of integers.
  */
 public void turnToAscendingOrder(int[] sizeArray) {
  //Implementation of BubbleSort alg in order to sort the array into an ascending order
  for (int i = 0; i < sizeArray.length - 1; i++) {
   for (int j = 0; j < sizeArray.length - 1 - i; j++) {
    if (sizeArray[j + 1] < sizeArray[j]) {
     swapInArray(sizeArray, j, j + 1);
    }
   }
  }
 }

 /**
  * This method creates a new array of integers with the same size as the string array.
  * Then it fills each one of the integer array cells' with the
  * integer value of the same cell (according to the cell's index) in the string array.
  *
  * @param sizeS array of strings
  * @return array of integers
  */
 public int[] turnToIntArray(String[] sizeS) {
   int[] sizeArray = new int[sizeS.length];
   for (int i = 0; i < sizeS.length; i++) {
    sizeArray[i] = Integer.parseInt(sizeS[i]);
   }
   return sizeArray;
  }



 /**
  * Main method: Initialize a animation. Turn its string[] args into int[] array.
  * Then arrange it to ascending order.
  * Then send the array (related as array of balls' sizes) to be used in a
  * method that draw balls in an animation.
  *
  * @param args array of random balls' sizes
  */
 public static void main(String[] args) {
  MultipleBouncingBallsAnimation example = new MultipleBouncingBallsAnimation();
  int[] sizeArray = example.turnToIntArray(args);
  example.turnToAscendingOrder(sizeArray);
  example.drawAnimation(sizeArray);
 }

}
