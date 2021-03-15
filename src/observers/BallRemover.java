package observers;

import collidables.Block;
import game.Counter;
import game.GameLevel;
import sprites.Ball;

/**
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 *
 * @author Tom Magdaci 316603604
 */
public class BallRemover implements HitListener {

 //Fields
 private GameLevel gameLevel;
 private Counter remainingBalls;

 /**
  * Constructor with configurable Game and Counter.
  *
  * @param newGameLevel Game
  * @param remainingBalls Counter
  */
 public BallRemover(GameLevel newGameLevel, Counter remainingBalls) {
  this.gameLevel = newGameLevel;
  this.remainingBalls = remainingBalls;
 }

 @Override
 public void hitEvent(Block beingHit, Ball hitter) {
  if (this.remainingBalls.getValue() == 1) {
   beingHit.removeHitListener(this);
  }
  hitter.removeFromGame(this.gameLevel);
  this.remainingBalls.decrease(1);
 }
}
