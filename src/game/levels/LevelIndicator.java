package game.levels;

import biuoop.DrawSurface;
import game.AnimationRunner;
import game.GameLevel;
import sprites.Sprite;

/**
 * LevelIndicator is a sprite that is drawn on the screen for showing the level's name.
 *
 * @author Tom Magdaci 
 */
public class LevelIndicator implements Sprite {

 private LevelInformation level;

 /**
  * Constructor with configurable LevelInformation.
  * @param lvl LevelInformation
  */
 public LevelIndicator(LevelInformation lvl) {
  this.level = lvl;
 }

 @Override
 public void drawOn(DrawSurface d) {
  int fontSize = 20;
  int devRightWard = 200;
  int devDownWard = 7;
  d.drawText((int) AnimationRunner.WIDTH / 2 + devRightWard, (int) GameLevel.WALLTHICKNESS / 2 + devDownWard,
          this.level.levelName(), fontSize);
 }

 @Override
 public void timePassed() {
 }

 /**
  * Add this sprite to specific GameLevel.
  * @param g GameLevel
  */
 public void addToGame(GameLevel g) {
  g.addSprite(this);
 }
}
