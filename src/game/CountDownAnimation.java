package game;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * CountDownAnimation is an Animation.
 * This Animation purpose is to animate countdown for the start of the the game level.
 *
 * @author Tom Magdaci 
 */
public class CountDownAnimation implements Animation {

 //Fields
 private double numOfSeconds;
 private int countFrom;
 private SpriteCollection gameScreen;
 private Color backRoundColor;

 /**
  * Constructor with configurable seconds number and the SpriteCollect that needed to be drawn.
  *
  * @param numOfSeconds int
  * @param countFrom int
  * @param gameScreen Sprite Collection
  */
 public CountDownAnimation(double numOfSeconds,
                           int countFrom,
                           SpriteCollection gameScreen) {
  this.numOfSeconds = numOfSeconds;
  this.countFrom = countFrom;
  this.gameScreen = gameScreen;
  }

 /**
  * Constructor with additional Color configurable.
  *
  * @param numOfSeconds int
  * @param countFrom int
  * @param gameScreen Sprite collection
  * @param backRoundC color
  */
 public CountDownAnimation(double numOfSeconds,
                           int countFrom,
                           SpriteCollection gameScreen, Color backRoundC) {
  this.numOfSeconds = numOfSeconds;
  this.countFrom = countFrom;
  this.gameScreen = gameScreen;
  this.backRoundColor = backRoundC;
 }

 @Override
 public void doOneFrame(DrawSurface d) {
  int devDownWard = 220;
  int devLeftWard = 30;
  int fontSize = 80;
  this.gameScreen.drawAllOn(d);
  d.setColor(Color.white);
  if (this.backRoundColor != null && this.backRoundColor == Color.white) {
   d.setColor(Color.BLACK);
  }
  d.drawText((int) AnimationRunner.WIDTH / 2 - devLeftWard, (int) AnimationRunner.HEIGHT / 7 + devDownWard,
          Integer.toString((int) this.countFrom), fontSize);
  this.countFrom--;
 }

 @Override
 public boolean shouldStop() {
  if (this.countFrom <= 0) {
   return true;
  }
  return false;
 }
}
