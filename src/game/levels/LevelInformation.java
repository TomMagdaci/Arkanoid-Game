package game.levels;

import collidables.Block;
import geometry.Velocity;
import sprites.Ball;
import sprites.Sprite;

import java.util.List;

/**
 * LevelInformation is a level in the game that creates specific paddle,
 * balls and blocks according to its planned locations and sizes.
 *
 * @author Tom Magdaci 
 */
public interface LevelInformation {
  /**
   * Return number of balls in the level.
   * @return int
   */
  int numberOfBalls();

  /**
   * Creates List of velocities according to the balls' number.
   * @return List
   */
  List<Velocity> initialBallVelocities();

  /**
   * Returns the paddle speed.
   * @return int
   */
  int paddleSpeed();

  /**
   * Returns the paddle width.
   * @return int
   */
  int paddleWidth();

  /**
   * Returns the level name.
   * @return String
   */
  String levelName();

  /**
   * Returns the background of the level.
   * @return Sprite
   */
  Sprite getBackground();

  /**
   * Creates the Blocks that make up this level, each block contains
   * its size, color and location.
   *
   * @return List of blocks
   */
  List<Block> blocks();

  /**
   * Creates the Balls that make up this level, each ball contains
   * its size, color, location and speed.
   * @return List of balls
   */
  List<Ball> balls();

  /**
   * Return the required number of blocks that should be removed.
   * @return int
   */
  int numberOfBlocksToRemove();
}

