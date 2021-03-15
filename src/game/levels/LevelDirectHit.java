package game.levels;

import game.AnimationRunner;
import geometry.Point;
import geometry.Velocity;
import sprites.BackGround;
import sprites.BackGroundBallAnimation;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * LevelDirectHit is a level in our game.
 * It has one ball and one block.
 *
 * @author Tom Magdaci 
 */
public class LevelDirectHit extends AbstractLevel implements LevelInformation {

 //Blocks parameters
 public static final int BLOCKSIDE = 20;
 public static final int BLOCKSTOREM = 1;
 public static final int X_BLOCK = (AnimationRunner.WIDTH / 2) - 3 * BLOCKSIDE / 4;
 public static final int FIRSTROWLENGTH = 1;
 public static final int ROWSNUM = 1;
 //Balls parameters
 public static final int BALLSNUM = 1;
  //Paddle parameters
 public static final int PADDLESPEED = 4;
 public static final int PADDLEWIDTH = 100;

 /**
  * Constructor with configurable ball, paddle and block's parameters.
  */
 public LevelDirectHit() {
  super(BALLSNUM, BALLSPEED, PADDLESPEED, PADDLEWIDTH, "Direct Hit", FIRSTROWLENGTH, ROWSNUM);
  setBlocksParamteres(BLOCKSIDE, BLOCKSIDE, BLOCKSTOREM, X_BLOCK, YOFFIRSTBLOCKROW);
 }

 @Override
 public List<Velocity> initialBallVelocities() {
  List<Velocity> l = new LinkedList<>();
  l.add(new Velocity(0, (-1) * getBallSpeed()));
  return l;
 }


 @Override
 public Sprite getBackground() {
  Sprite s = new BackGround(Color.black);
  return s;
 }

 /**
  * Returns the required animations that should be drawn on the screen while this level is running.
  * This is a static method because different levels have different BackgroundAnimations types to return,
  * in addition, BackgroundBallAnimation should not be declared as a LevelInformation.
  * @return List of sprites
  */
 public static List<Sprite> getBackgroundBallAnimaton() {
  List<Sprite> l = new ArrayList<>();
  Sprite s;
  int radius = 80;
  for (int i = 0; i < 3; i++) {
    s = new BackGroundBallAnimation(Color.cyan, X_BLOCK + BLOCKSIDE / 2,
           YOFFIRSTBLOCKROW + BLOCKSIDE / 2, radius - (20 * i), false);
    l.add(s);
  }
  return l;
 }

 @Override
 public List<Ball> balls() {
  List<Ball> ball = new ArrayList<>();
  ball.add(new Ball(new Point(XOFFIRSTBALL, YOFFIRSTBALL),
          RADIUS, Color.white));
  ball.get(0).setVelocity(initialBallVelocities().get(0));
  return ball;
 }

}
