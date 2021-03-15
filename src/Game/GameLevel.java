package game;

import biuoop.KeyboardSensor;
import collidables.DeathRegionBlock;
import game.levels.LevelDirectHit;
import game.levels.LevelWideEasy;
import game.levels.LevelInformation;
import game.levels.AbstractLevel;
import game.levels.LevelIndicator;
import observers.BallRemover;
import observers.BlockRemover;
import observers.ScoreTrackingListener;
import sprites.BackGround;
import sprites.Ball;
import sprites.SpriteCollection;
import sprites.ScoreIndicator;
import sprites.Paddle;
import sprites.Sprite;
import collidables.Collidable;
import collidables.Block;
import geometry.Point;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;

/**
 * Initialize and run a game level.
 *
 * @author Tom Magdaci 316603604
 */
public class GameLevel implements Animation {

 //Fields
 private SpriteCollection sprites;
 private GameEnvironment environment;
 private AnimationRunner runner;
 private boolean running;
 private Counter blocksCounter;
 private Counter ballsCounter;
 private Counter levelScore;
 private biuoop.KeyboardSensor keyboard;
 private LevelInformation level;

 //Wall Blocks parameters
 public static final int WALLTHICKNESS = 25;
 public static final int PADDLEHEIGHT = 15;
 public static final double PADDLE_Y_START_POINT = AnimationRunner.HEIGHT - WALLTHICKNESS - PADDLEHEIGHT;

 /**
  * Default constructor.
  * @param newLevel LevelInformation
  */
 public GameLevel(LevelInformation newLevel) {
  this.environment = new GameEnvironment();
  this.sprites = new SpriteCollection();
  this.blocksCounter = new Counter();
  this.ballsCounter = new Counter();
  this.levelScore = new Counter();
  this.runner = new AnimationRunner();
  this.keyboard = this.runner.getGuiKeyboard();
  this.level = newLevel;
 }

 /**
  * Configurable constructor.
  * @param newLevel LevelInformation
  * @param ks KayboardSensor
  * @param ar AnimationRunner
  * @param score Counter
  */
 public GameLevel(LevelInformation newLevel, KeyboardSensor ks, AnimationRunner ar, Counter score) {
  this.environment = new GameEnvironment();
  this.sprites = new SpriteCollection();
  this.blocksCounter = new Counter();
  this.ballsCounter = new Counter();
  this.levelScore = score;
  this.runner = ar;
  this.keyboard = ks;
  this.level = newLevel;
 }

 /**
  * Add collidable to the GameEnvironment.
  * @param c Collidable
  */
 public void addCollidable(Collidable c) {
  this.environment.addCollidable(c);
 }

 /**
  * Add sprite to the SpriteCollection.
  * @param s Sprite
  */
 public void addSprite(Sprite s) {
  this.sprites.addSprite(s);
 }

 /**
  * @return GameEnvironment
  */
 public GameEnvironment getGameEnvironment() {
  return this.environment;
 }

 /**
  * Initialize a new game: create the Blocks and two Balls and Paddle.
  * and add them to the game.
  */
 public void initialize() {
  Sprite backRound = this.level.getBackground();
  addSprite(backRound);
  backRoundAnimation();
  List<Block> blockList = this.level.blocks();
  addListenersToBlocks(blockList);
  this.blocksCounter.increase(blockList.size());
  createWalls(blockList);
  addBlocksToGame(blockList);
  List<Ball> ballList = this.level.balls();
  addBallsToGame(ballList);
  this.ballsCounter.increase(ballList.size());
  createPaddle();
  ScoreIndicator scoreInd = new ScoreIndicator(new Point(0, 0), AnimationRunner.WIDTH, WALLTHICKNESS, this.levelScore);
  scoreInd.addToGame(this);
  LevelIndicator levelind = new LevelIndicator(this.level);
  levelind.addToGame(this);
 }

 /**
  * Add BackRoundAnimation's (according to specific levels) to the game sprite collection.
  */
 private void backRoundAnimation() {
  switch (this.level.levelName()) {
   case "Wide Easy":
    Sprite backRoundAnimation = LevelWideEasy.getBackgroundBallAnimaton();
    if (backRoundAnimation != null) {
     addSprite(backRoundAnimation);
     break;
    }
   case "Direct Hit":
    List<Sprite> backRoundAnimations = LevelDirectHit.getBackgroundBallAnimaton();
    if (backRoundAnimations != null) {
     for (Sprite s : backRoundAnimations) {
      addSprite(s);
     }
    }
    break;
   default:
    break;
  }
 }

 /**
  * Create new paddle with specific level's parameters.
  */
 private void createPaddle() {
  Paddle d = new Paddle(new Block(new Point((double) (AnimationRunner.WIDTH / 2) - this.level.paddleWidth() / 2,
          PADDLE_Y_START_POINT), this.level.paddleWidth(),
          PADDLEHEIGHT, AbstractLevel.PADDLECOLOR), this.keyboard);
  d.setSpeed(this.level.paddleSpeed());
  d.addToGame(this);
 }

 /**
  * Add listeners to blocks.
  *
  * @param blockList List
  */
 private void addListenersToBlocks(List<Block> blockList) {
  BlockRemover blockRem = new BlockRemover(this, this.blocksCounter);
  ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.levelScore);
  for (Block b : blockList) {
   b.addHitListener(blockRem);
   b.addHitListener(scoreTrack);
  }
 }

 /**
  * Add blocks to game.
  *
  * @param blockList List
  */
 private void addBlocksToGame(List<Block> blockList) {
  for (Block b : blockList) {
   b.addToGame(this);
  }
 }

 /**
  * Add balls to game.
  *
  * @param ballList List
  */
 private void addBallsToGame(List<Ball> ballList) {
  for (Ball b : ballList) {
   b.addToGame(this);
  }
 }

 /**
  * Generate special blocks - the boundaries of the gui.
  * Their thickness depends on the WALLTHICKNESS static variable
  * and on the HEIGHT and WIDTH that belong to the Gui.
  *
  * @param blockList List of block
  */
 private void createWalls(List<Block> blockList) {
  //WIDTH and HEIGHT stand for GUI's size
  //WALLTHICKNESS stands for the thickness of the wall.
  int verWallWidth = WALLTHICKNESS, verWallHeight = AnimationRunner.HEIGHT;
  int horWallWidth = AnimationRunner.WIDTH, horWallHeight = WALLTHICKNESS;
  Color wallColor = Color.GRAY;
  Color deathRcolor = Color.blue.darker();
  BallRemover ballRem = new BallRemover(this, this.ballsCounter);
  DeathRegionBlock deathRegion = new DeathRegionBlock(new Point(verWallWidth, (AnimationRunner.HEIGHT - horWallHeight)),
          horWallWidth - 2 * verWallWidth, horWallHeight, ((BackGround) this.level.getBackground()).getColor());
  blockList.add(deathRegion);
  //Left wall
  blockList.add(new Block(new Point(0, 0), verWallWidth, verWallHeight, wallColor));
  //Rigth wall
  blockList.add(new Block(new Point((AnimationRunner.WIDTH - verWallWidth), 0),
          verWallWidth, verWallHeight, wallColor));
  //Upper wall
  blockList.add(new Block(new Point(0, horWallHeight), horWallWidth, horWallHeight, wallColor));
  //Lower wall
  deathRegion.addHitListener(ballRem);
 }

 /**
  * Run the game -- start the animation loop.
  */
 public void run() {
  int numOfSeconds = 3, countDown = 3;
  this.runner.setFramesPerSecond(AnimationRunner.FRAMESFORCOUNTING);
  this.runner.run(new CountDownAnimation(numOfSeconds, countDown, this.sprites,
          ((BackGround) this.level.getBackground()).getColor()));
  this.runner.setFramesPerSecond(AnimationRunner.FRAMESFORPLAYING);
  this.running = true;
  this.runner.run(this);
 }

 /**
  * Remove the given collidable from the list.
  * Uses removeCollidable method from GameEnvironment.
  * @param c collidable
  */
 public void removeCollidable(Collidable c) {
  this.environment.removeCollidable(c);
 }

 /**
  * Remove the given collidable from the list.
  * Uses removeSprite method from SpriteCollection.
  * @param s sprite
  */
 public void removeSprite(Sprite s) {
  this.sprites.removeSprite(s);
 }

 @Override
 public void doOneFrame(DrawSurface d) {
  this.sprites.drawAllOn(d);
  this.sprites.notifyAllTimePassed();
  if (this.keyboard.isPressed("p")) {
   this.runner.run(new KeyPressStoppableAnimation(this.runner.getGuiKeyboard(),
           "space", new PauseScreen()));
  }
 }

 @Override
 public boolean shouldStop() {
  if ((this.ballsCounter.getValue() == 0) || (this.blocksCounter.getValue() == 0)) {
   this.running = true;
   return this.running;
  }
  this.running = false;
  return this.running;
 }

 /**
  * Returns the balls' counter.
  * @return Counter
  */
 public Counter getBallsCounter() {
  return this.ballsCounter;
 }
}