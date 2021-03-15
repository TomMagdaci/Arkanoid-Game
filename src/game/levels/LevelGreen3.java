package game.levels;

import geometry.Velocity;
import sprites.BackGround;
import sprites.Sprite;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * LevelGreen3 is a level in our game.
 * It is level number three, it has two balls, 5 rows of blocks the first row has 10 blocks
 * then it the number of block decreases in 1 from one row to the other.
 *
 * @author Tom Magdaci 316603604
 */
public class LevelGreen3 extends AbstractLevel implements LevelInformation {

 //Fields
 //Blocks parameters
 public static final int BLOCKSTOREM = 40;
 public static final int FIRSTROWLENGTH = 10;
 public static final int ROWSNUM = 5;
 //Balls parameters
 public static final int BALLSNUM = 2;
 //Paddle parameters
 public static final int PADDLESPEED = 9;
 public static final int PADDLEWIDTH = 150;

 /**
  * Constructor.
  */
 public LevelGreen3() {
  super(BALLSNUM, BALLSPEED, PADDLESPEED, PADDLEWIDTH, "Green 3", FIRSTROWLENGTH, ROWSNUM);
  setBlocksParamteres(BLOCKSWIDTH, BLOCKSHEIGHT, BLOCKSTOREM, XOFFIRSTBLOCKROW, YOFFIRSTBLOCKROW);
 }

 @Override
 public Sprite getBackground() {
  Sprite s = new BackGround(Color.green.darker());
  return s;
 }

 @Override
 protected boolean decOrder() {
  return true;
 }

 @Override
 public List<Velocity> initialBallVelocities() {
  int leftiestBallAngle = 40;
  int rightestBallAngle = 320;
  Random rand = new Random();
  List<Velocity> l = new LinkedList<>();
  l.add(Velocity.fromAngleAndSpeed(((double) leftiestBallAngle), getBallSpeed()));
  l.add(Velocity.fromAngleAndSpeed(((double) rightestBallAngle), getBallSpeed()));
  return l;
 }
}
