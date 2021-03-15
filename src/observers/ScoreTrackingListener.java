package observers;

import collidables.Block;
import game.Counter;
import sprites.Ball;

/**
 * ScoreTrackingListener is used for tracking the user's score in the game.
 * It uses counter to keep the score updated.
 *
 * @author Tom Magdaci 
 */
public class ScoreTrackingListener implements HitListener {

 private Counter currentScore;

 /**
  * Constructor with configurable Counter.
  *
  * @param scoreCounter Counter
  */
 public ScoreTrackingListener(Counter scoreCounter) {
  this.currentScore = scoreCounter;
 }

 @Override
 public void hitEvent(Block beingHit, Ball hitter) {
  this.currentScore.increase(5);
 }
}
