package game.levels;

import collidables.Block;
import game.AnimationRunner;
import game.GameLevel;
import geometry.Point;
import geometry.Velocity;
import sprites.Ball;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level is a game run with specific environment such as: specific balls' number or paddle speed and so on..
 *
 * @author Tom Magdaci 
 */
public abstract class AbstractLevel {

 //Fields
 private int ballsNum;
 private int ballSpeed;
 private int paddleSpeed;
 private int paddleWidth;
 private String levelName;
 private int numBlocksToRemove;
 private int xFirstBlockRow;
 private int yFirstBlockRow;
 private int blockWidth;
 private int blockHeight;
 private int firstRowLength;
 private int rowsNum;

 //Standard block parameters
 public static final int BLOCKSWIDTH = 50;
 public static final int BLOCKSHEIGHT = 20;
 public static final int YOFFIRSTBLOCKROW = 5 * GameLevel.WALLTHICKNESS;
 public static final int XOFFIRSTBLOCKROW = AnimationRunner.WIDTH - GameLevel.WALLTHICKNESS - BLOCKSWIDTH;
 //Standard ball parameters
 public static final int BALLSPEED = 7;
 public static final int RADIUS = 5;
 public static final Color BALLCOLOR = Color.white;
 public static final int XOFFIRSTBALL = (AnimationRunner.WIDTH / 2);
 public static final double YOFFIRSTBALL = GameLevel.PADDLE_Y_START_POINT - GameLevel.PADDLEHEIGHT * 2;
 //Standard paddle parameters
 public static final Color PADDLECOLOR = Color.YELLOW;

 /**
  * Constructor with the next configurable parameters:
  * Ball's data: number, speed
  * Paddle's data: speed, width
  * Level's data: name, numBlocksToRem
  * Block's data: x_FirstBlockRow, y_FirstBlockRow, block width, block height, rows number, length of the first row.
  * @param nBallsNum int
  * @param nBallSpeed int
  * @param nPaddleSpeed int
  * @param nPaddleWidth int
  * @param nLevelName String
  * @param nFirstRowLength int
  * @param nRowsNum int
  */
 public AbstractLevel(int nBallsNum, int nBallSpeed, int nPaddleSpeed,
                      int nPaddleWidth, String nLevelName, int nFirstRowLength, int nRowsNum) {
  this.ballsNum = nBallsNum;
  this.ballSpeed = nBallSpeed;
  this.paddleSpeed = nPaddleSpeed;
  this.paddleWidth = nPaddleWidth;
  this.levelName = nLevelName;
  this.firstRowLength = nFirstRowLength;
  this.rowsNum = nRowsNum;
 }

 /**
  * Setter for block's parameters.
  * @param bW block width
  * @param bH block height
  * @param nBTR num blocks to rem
  * @param xFirstB x of first block
  * @param yFirstB y of first block
  */
 protected void setBlocksParamteres(int bW, int bH, int nBTR, int xFirstB, int yFirstB) {
  this.blockWidth = bW;
  this.blockHeight = bH;
  this.numBlocksToRemove = nBTR;
  this.xFirstBlockRow = xFirstB;
  this.yFirstBlockRow = yFirstB;
 }

 /**
  * Return the level's balls' number.
  * @return int
  */
 public int numberOfBalls() {
  return this.ballsNum;
 }

 /**
  * Returns the level's paddle speed.
  * @return int
  */
 public int paddleSpeed() {
  return this.paddleSpeed;
 }

 /**
  * Returns the level's paddle width.
  * @return int
  */
 public int paddleWidth() {
  return this.paddleWidth;
 }

 /**
  * Returns level name.
  * @return int
  */
 public String levelName() {
  return this.levelName;
 }

 /**
  * Returns level's number of block that should be removed.
  * @return int
  */
 public int numberOfBlocksToRemove() {
  return this.numBlocksToRemove;
 }

 /**
  * Return the level's balls' speed.
  * @return int
  */
 public int getBallSpeed() {
  return this.ballSpeed;
 }

 /**
  * This method is used in block's creation method, that some specific levels
  * required the blocks' rows' to be built in decrease order.
  * @return boolean
  */
 protected boolean decOrder() {
  return false;
 }

 /**
  * Create list of fixed collidables (meantime only blocks) and add them to game environment.
  * @return List of blocks
  */
 public List<Block> blocks() {
  int firstRowFirstBlockYValue = this.yFirstBlockRow;
  int firstRowFirstBlockXValue = this.xFirstBlockRow;
  int rowLength = this.firstRowLength;
  int rN = this.rowsNum;
  Color[] blockColors = {Color.RED, Color.orange,  Color.YELLOW.brighter(),
          Color.BLUE.brighter(), Color.PINK, Color.GREEN.brighter(), Color.cyan};
  List<Block> blockList = new ArrayList<>();
  int k = 0;
  for (int i = 0; i < rN; i++) {
   for (int j = 0; j < rowLength; j++) {
    blockList.add(new Block(new Point((double) firstRowFirstBlockXValue - j * this.blockWidth,
            (double) firstRowFirstBlockYValue + i * this.blockHeight),
            this.blockWidth, this.blockHeight, blockColors[k]));
    if ((this.rowsNum == 1) && ((j + 1) % 2 == 0)) {
     k++;
     if (k == blockColors.length) {
      k = 0;
     }
    }
   }
   if (decOrder()) {
    rowLength--;
   }
   k++;
   if (k == blockColors.length) {
    k = 0;
   }
  }
  return blockList;
 }

 /**
  * Create list of velocities.
  *
  * @return List velocity
  */
 public abstract List<Velocity> initialBallVelocities();

 /**
  * Create list of balls.
  *
  * @return List balls
  */
 public List<Ball> balls() {
  List<Ball> ball = new ArrayList<>();
  List<Velocity> velocities = initialBallVelocities();
  for (int i = 0; i < this.ballsNum; i++) {
   ball.add(generateBall(i));
   ball.get(i).setVelocity(velocities.get(i));
  }
  return ball;
 }

 /**
  * Generate new Ball with specific parameters.
  * @param i int
  * @return Ball
  */
 public Ball generateBall(int i) {
  double x, y;
  int size = RADIUS;
  x = XOFFIRSTBALL;
  y = YOFFIRSTBALL;
  Ball newB = new Ball(x, y, size, BALLCOLOR);
  return newB;
 }
}
