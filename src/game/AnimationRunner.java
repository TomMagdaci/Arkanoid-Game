package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * an AnimationRunner is an object that used for running different animations in specific frames.
 *
 * @author Tom Magdaci 316603604
 */
public class AnimationRunner {
 //Fields
 private GUI gui;
 private double framesPerSecond;

 //Different frames for different purposes
 public static final double FRAMESFORCOUNTING = 1.5;
 public static final double FRAMESFORPLAYING = 60;

 //Gui parameters
 public static final int WIDTH = 800;
 public static final int HEIGHT = 600;

 /**
  * Constructor.
  */
 public AnimationRunner() {
  this.framesPerSecond = FRAMESFORPLAYING;
  this.gui = new GUI("title", WIDTH, HEIGHT);
 }

 /**
  * Constructor with configurable framesPerSecond.
  * @param newFramesPerSecond int
  */
 public AnimationRunner(int newFramesPerSecond) {
  this.framesPerSecond = newFramesPerSecond;
  this.gui = new GUI("title", WIDTH, HEIGHT);
 }

 /**
  * Setter for framesPerSecond fields.
  * @param newFramesPerSecond int
  */
 public void setFramesPerSecond(double newFramesPerSecond) {
  this.framesPerSecond = newFramesPerSecond;
 }

 /**
  * Returns the gui fields.
  * @return GUI
  */
 public GUI getGui() {
  return this.gui;
 }

 /**
  * Returns the keyBoardSensor of the gui.
  * @return biuoop.KeyboardSensor
  */
 public biuoop.KeyboardSensor getGuiKeyboard() {
  return this.gui.getKeyboardSensor();
 }

 /**
  * Run method is used for running specific animation in the required time limits (framesPerSecond).
  * @param animation Animation
  */
 public void run(Animation animation) {
  Sleeper sleeper = new Sleeper();
  int millisecondsPerFrame = (int) (1000 / this.framesPerSecond);
  while (!animation.shouldStop()) {
   long startTime = System.currentTimeMillis(); // timing
   DrawSurface d = gui.getDrawSurface();

   animation.doOneFrame(d);

   gui.show(d);
   long usedTime = System.currentTimeMillis() - startTime;
   long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
   if (milliSecondLeftToSleep > 0) {
    sleeper.sleepFor(milliSecondLeftToSleep);
   }
  }
 }
}
