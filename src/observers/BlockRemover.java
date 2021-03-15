package observers;

import collidables.Block;
import game.Counter;
import game.GameLevel;
import sprites.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Tom Magdaci 316603604
 */
public class BlockRemover implements HitListener {

 //Fields
 private GameLevel gameLevel;
 private Counter remainingBlocks;

 /**
  * Constructor with configurable Game and Counter.
  *
  * @param newGameLevel Game
  * @param remainingBlocks Counter
  */
 public BlockRemover(GameLevel newGameLevel, Counter remainingBlocks) {
  this.gameLevel = newGameLevel;
  this.remainingBlocks = remainingBlocks;
 }

 @Override
 public void hitEvent(Block beingHit, Ball hitter) {
  beingHit.removeHitListener(this);
  beingHit.removeFromGame(this.gameLevel);
  this.remainingBlocks.decrease(1);
 }
}
