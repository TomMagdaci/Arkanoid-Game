import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

import sprites.Ball;

/**
 *  This class generate random bouncing balls with random colors
 *  in specific boundaries in form of two rectangles,
 *  and give them velocities according to their sizes.
 */
public class MultipleFramesBouncingBallsAnimation {

 //Animation parameters.
 //GUI paramteres:
 static final int TIMESLEEP = 50; //Sleeper time (in milliseconds)
 static final int WIDTH = 1000; //GUI's width
 static final int HEIGHT = 1000; //GUI's height

 /**
  * Change each one of the balls' colors in the ballArr randomly, by
  * choosing randomly cells from the colorArr.
  *
  * @param ballArr array of balls
  * @param colorArr array of colors
  */
 public void changeToRandomColors(Ball[] ballArr, Color[] colorArr) {
  Random rand = new Random();
  int c;
  for (int i = 0; i < ballArr.length; i++) {
   c = rand.nextInt(colorArr.length - 1);
   ballArr[i].changeColor(colorArr[c]);
  }
 }

 /**
  * This method has two parts:
  * Part1: using helper methods, creating two array of balls according to the boundaries
  * and with randomly colors.
  * Part2: draw an animation of BB from these two array of balls, each ball
  * has its limits according to specific rectangle.
  *
  * @param firstArr array of balls.
  * @param secondArr array of balls.
  * @param example1 an object of MultipleBBAnimation.
  */
 public void drawAnimation(int[] firstArr, int[] secondArr, MultipleBouncingBallsAnimation example1) {
  //First define the boundaries and the sides of the two rectangles.
  int xStartFirstRec = 50, yStartFirstRec = 50, xEndFirstRec = 500,  yEndFirstRec = 500, sideFirstRec = 450;
  int xStartSecRec = 450,  yStartSecRec = 450,  xEndSecRec = 600, yEndSecRec = 600, sideSecRec = 150;
  //Create array of balls according to the sizes, using MultipleBBAnimation method calles createBallsArray()
  Ball[] firstBallsArray
          = example1.createBallsArray(firstArr, xStartFirstRec, yStartFirstRec, xEndFirstRec, yEndFirstRec);
  Color[] colorArr = {Color.RED, Color.BLUE, Color.PINK, Color.orange};
  changeToRandomColors(firstBallsArray, colorArr);
  Ball[] secondBallsArray
          = example1.createBallsArray(secondArr, xStartSecRec, yStartSecRec, xEndSecRec, yEndSecRec);
  colorArr = new Color[]{Color.RED, Color.BLUE, Color.PINK, Color.orange, Color.GRAY};
  changeToRandomColors(secondBallsArray, colorArr);
  //Create new Gui and sleeper.
  GUI gui = new GUI("title", WIDTH, HEIGHT);
  Sleeper sleeper = new Sleeper();
  while (true) {
   DrawSurface d1 = gui.getDrawSurface();
   d1.setColor(Color.GRAY);
   d1.fillRectangle(xStartFirstRec, yStartFirstRec, sideFirstRec, sideFirstRec);
   d1.setColor(Color.YELLOW);
   d1.fillRectangle(xStartSecRec, yStartSecRec, sideSecRec, sideSecRec);
   for (int i = 0; i < firstBallsArray.length; i++) {
    firstBallsArray[i].moveOneStep(xStartFirstRec, yStartFirstRec, xEndFirstRec, yEndFirstRec);
    secondBallsArray[i].moveOneStep(xStartSecRec, yStartSecRec, xEndSecRec, yEndSecRec);
    firstBallsArray[i].drawOn(d1);
    secondBallsArray[i].drawOn(d1);
   }
   gui.show(d1);
   sleeper.sleepFor(TIMESLEEP);  // wait for TIMESLEEP milliseconds.
  }
 }

 /**
  * Main method: Initialize a MultipleframesBBAnimation & Initialize a MultipleBBAnimation.
  * using MultipleBBAnimation methods creates two different arrays of balls' sizes
  * each one is in ascending order.
  * Then make animation of BB (Bouncing balls) from these two arrays.
  *
  * @param args array of strings, each string will be attributed as size of ball
  */
 public static void main(String[] args) {
  MultipleFramesBouncingBallsAnimation example = new MultipleFramesBouncingBallsAnimation();
  MultipleBouncingBallsAnimation example1 = new MultipleBouncingBallsAnimation();
  int[] sizeArray = example1.turnToIntArray(args);
  int half = sizeArray.length / 2;
  int[] firstArr = Arrays.copyOfRange(sizeArray, 0, half);
  int[] secondArr = Arrays.copyOfRange(sizeArray, half, sizeArray.length);
  example1.turnToAscendingOrder(firstArr);
  example1.turnToAscendingOrder(secondArr);
  example.drawAnimation(firstArr, secondArr, example1);
 }
}
