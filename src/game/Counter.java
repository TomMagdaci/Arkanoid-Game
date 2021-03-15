package game;

/**
 * Counter is used to track on the amount of specific objects.
 *
 * @author Tom Magdaci 316603604
 */
public class Counter {

 //Fields
 private int count;

 /**
  * Constructor with no arguments.
  */
 public Counter() {
  this.count = 0;
 }

 /**
  * Add number to current count.
  * @param number Int
  */
 public void increase(int number) {
  this.count = this.count + number;
 }

 /**
  * Subtract number from current count.
  * @param number Int
  */
 public void decrease(int number) {
  this.count = this.count - number;
 }

 /**
  * Get current count.
  * @return the counter's value
  */
 public int getValue() {
  return this.count;
 }
}
