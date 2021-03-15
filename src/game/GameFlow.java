package game;

import biuoop.KeyboardSensor;
import game.InputOutput.ReadingFromFile;
import game.InputOutput.WritingToFile;
import game.levels.LevelInformation;

import java.util.List;

/**
 * GameFlow object is the object that runs the game's level on after the other.
 *
 * @author Tom Magdaci 
 */
public class GameFlow {

 private AnimationRunner animationRunner;
 private biuoop.KeyboardSensor keyboardSensor;
 private Counter score;

 //Score parameters
 public static final int BONUS_SCORE = 100;

 /**
  * Constructor with configurable AnimationRunner and KeyboardSensor.
  * @param ar AnimationRunner
  * @param ks KeyboardSensor
  */
 public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
  this.animationRunner = ar;
  this.keyboardSensor = ks;
  this.score = new Counter();
 }

 /**
  * Level running method.
  * This method initialize and run a game level.
  *
  * @param levels GameLevel
  */
 public void runLevels(List<LevelInformation> levels) {
  // ...
  int blocksOverall = 0;
  int maxScore;
  for (LevelInformation levelInfo : levels) {

   GameLevel gameLevel = new GameLevel(levelInfo,
           this.keyboardSensor,
           this.animationRunner,
               this.score);

   gameLevel.initialize();

   while (!gameLevel.shouldStop()) {
    gameLevel.run();
   }

   if (gameLevel.getBallsCounter().getValue() == 0) {
    if (ReadingFromFile.readingScore() != null) {
     if (Integer.parseInt(ReadingFromFile.readingScore()) < this.score.getValue()) {
      WritingToFile.writingScore(this.score);
     }
    } else {
     WritingToFile.writingScore(this.score);
    }
    this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGuiKeyboard(),
            "space", new GameOverScreen(this.score)));
    break;
   }
   this.score.increase(BONUS_SCORE);
   }
  for (LevelInformation level : levels) {
   blocksOverall += level.numberOfBlocksToRemove();
  }
  maxScore = blocksOverall * 5 + levels.size() * BONUS_SCORE;
  if (maxScore == this.score.getValue()) {
   this.animationRunner.run(new KeyPressStoppableAnimation(this.animationRunner.getGuiKeyboard(),
           "space", new WinScreen(this.score)));
  }
 }
}
