package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppable is an Animation which start/stop it's action according to specific key press.
 *
 * @author Tom Magdaci 
 */
public class KeyPressStoppableAnimation implements Animation {

 //Fields
 private KeyboardSensor keyboardSensor;
 private String key;
 private Animation animation;
 private boolean isAlreadyPressed;
 private boolean stop;

 /**
  * Default constructor.
  *
  * @param sensor keyboard
  * @param k key
  * @param ar animation
  */
 public KeyPressStoppableAnimation(KeyboardSensor sensor, String k, Animation ar) {
  this.keyboardSensor = sensor;
  this.key = k;
  this.animation = ar;
  this.isAlreadyPressed = true;
  this.stop = false;
 }

 @Override
 public void doOneFrame(DrawSurface d) {
  if (this.keyboardSensor.isPressed(this.key)) {
   if (!this.isAlreadyPressed) {
    this.stop = true;
   }
  } else {
   this.isAlreadyPressed = false;
  }
  this.animation.doOneFrame(d);
 }

 @Override
 public boolean shouldStop() {
  return this.stop;
 }
}
