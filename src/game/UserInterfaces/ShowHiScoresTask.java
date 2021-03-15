package game.UserInterfaces;

import game.Animation;
import game.AnimationRunner;
import game.KeyPressStoppableAnimation;

public class ShowHiScoresTask implements Task<Void> {
 private AnimationRunner runner;
 private Animation highScoresAnimation;
 public ShowHiScoresTask(AnimationRunner r, Animation h) {
  this.runner = r;
  this.highScoresAnimation = h;
 }
 public Void run() {
  //this.runner.run(this.highScoresAnimation);
  this.runner.run(new KeyPressStoppableAnimation(this.runner.getGuiKeyboard(),
          "space", this.highScoresAnimation));
  return null;
 }
}
