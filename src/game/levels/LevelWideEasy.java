package game.levels;

import game.AnimationRunner;
import game.GameLevel;
import geometry.Velocity;
import sprites.BackGround;
import sprites.BackGroundBallAnimation;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class LevelWideEasy extends AbstractLevel implements LevelInformation {

 //Blocks parameters
 public static final int BLOCKSTOREM = 15;
 public static final int YOFFIRSTBLOCKROW = AnimationRunner.HEIGHT / 3;
 public static final int XOFFIRSTBLOCKROW = AnimationRunner.WIDTH - BLOCKSWIDTH - GameLevel.WALLTHICKNESS;
 public static final int FIRSTROWLENGTH = 15;
 public static final int ROWSNUM = 1;
 //Balls parameters
 public static final int BALLSNUM = 10;
 public static final double XOFFIRSTBALL_WIDE = GameLevel.WALLTHICKNESS + BLOCKSWIDTH * 3.9;

 //Paddle parameters
 public static final int PADDLESPEED = 2;
 public static final int PADDLEWIDTH = (2 * AnimationRunner.WIDTH) / 3;

 /**
  * LevelWideEasy is a level in our game.
  * It has ten balls and 15 blocks sorting in a row. In addtion, it has wide paddle.
  * @author Tom Magdaci 
  */
 public LevelWideEasy() {
  super(BALLSNUM, BALLSPEED, PADDLESPEED, PADDLEWIDTH, "Wide Easy", FIRSTROWLENGTH, ROWSNUM);
  setBlocksParamteres(BLOCKSWIDTH, BLOCKSHEIGHT, BLOCKSTOREM, XOFFIRSTBLOCKROW, YOFFIRSTBLOCKROW);
 }

 @Override
 public Sprite getBackground() {
  Sprite s = new BackGround(Color.WHITE);
  return s;
 }

 /**
  * Returns the required animations that should be drawn on the screen while this level is running.
  * This is a static method because different levels have different BackgroundAnimations types to return,
  * in addition, BackgroundBallAnimation should not be declared as a LevelInformation.
  * @return List of sprites
  */
 public static Sprite getBackgroundBallAnimaton() {
  int radius = 80;
  Sprite s = new BackGroundBallAnimation(Color.YELLOW, AnimationRunner.WIDTH / 4,
          AnimationRunner.HEIGHT / 4, radius, true);
  return s;
 }

 /**
  * Generate new Ball with specific parameters.
  * @param i int
  * @return Ball
  */
 public Ball generateBall(int i) {
  double proRightUpWard = 0.7 * BLOCKSWIDTH;
  double x, y;
  int indexOfMedianBall = 4;
  int size = RADIUS;
  if (i <= indexOfMedianBall) {
   x = (double) (XOFFIRSTBALL_WIDE + i * proRightUpWard);
   y = (double) (YOFFIRSTBALL + Math.log10(i + 1) / Math.log10(2) * (-1) * proRightUpWard);
  } else {
   x = (double) (XOFFIRSTBALL_WIDE + BLOCKSWIDTH + i * proRightUpWard);
   y = (double) (YOFFIRSTBALL  - (Math.log10(10 - i) / Math.log10(2)) * proRightUpWard);
  }
  Ball newB = new Ball(x, y, size, BALLCOLOR);
  return newB;
 }

 @Override
 public List<Velocity> initialBallVelocities() {
  int firstBallAngle = 315;
  int devAngleBetweenBalls = 10;
  Random rand = new Random();
  List<Velocity> l = new LinkedList<>();
  for (int i = 0; i < BALLSNUM; i++) {
   l.add(Velocity.fromAngleAndSpeed(((double) firstBallAngle + i * devAngleBetweenBalls), getBallSpeed()));
  }
  return l;
 }
}
