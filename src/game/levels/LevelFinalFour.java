package game.levels;

import geometry.Velocity;
import sprites.BackGround;
import sprites.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * LevelFinalFour is a level in our game.
 * It is level number four, it has three balls,  rows of blocks and each row has 15 blocks in it.
 *
 * @author Tom Magdaci 316603604
 */
public class LevelFinalFour extends AbstractLevel implements LevelInformation {

 //Fields
 //Blocks parameters
 public static final int BLOCKSTOREM = 105;
 public static final int FIRSTROWLENGTH = 15;
 public static final int ROWSNUM = 7;
 //Balls parameters
 public static final int BALLSNUM = 3;
 public static final int BALLS_SPEED_FF = 6;
 //Paddle parameters
 public static final int PADDLESPEED = 10;
 public static final int PADDLEWIDTH = 150;

 /**
  * Constructor.
  */
 public LevelFinalFour() {
  super(BALLSNUM, BALLS_SPEED_FF, PADDLESPEED, PADDLEWIDTH, "Final Four", FIRSTROWLENGTH, ROWSNUM);
  setBlocksParamteres(BLOCKSWIDTH, BLOCKSHEIGHT, BLOCKSTOREM, XOFFIRSTBLOCKROW, YOFFIRSTBLOCKROW);
 }

 @Override
 public Sprite getBackground() {
  Sprite s = new BackGround(Color.cyan.darker());
  return s;
 }

 @Override
 public List<Velocity> initialBallVelocities() {
  int leftiestBallAngle = 40;
  int midBallAngle = 0;
  int rightestBallAngle = 320;
  Random rand = new Random();
  List<Velocity> l = new LinkedList<>();
  l.add(Velocity.fromAngleAndSpeed(((double) leftiestBallAngle), getBallSpeed()));
  l.add(Velocity.fromAngleAndSpeed(((double) midBallAngle), getBallSpeed()));
  l.add(Velocity.fromAngleAndSpeed(((double) rightestBallAngle), getBallSpeed()));
  return l;
 }

}
