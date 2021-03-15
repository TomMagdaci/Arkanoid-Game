package sprites;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * a SpriteCollection will hold a collection of sprites.
 *
 * @author Tom Magdaci
 */
public class SpriteCollection {

 //Fields
 private List<Sprite> spriteCollection;

 /**
  * Constructor, creates a new List of spriteCollection.
  */
 public SpriteCollection() {
  this.spriteCollection = new ArrayList<>();
 }

 /**
  * Add the given sprite to the collecion.
  * @param s sprite.
  */
 public void addSprite(Sprite s) {
  this.spriteCollection.add(s);
 }

 /**
  * get sprite from the collecion.
  * @param i int.
  * @return sprite
  */
 public Sprite getSprite(int i) {
  return this.spriteCollection.get(i);
 }

 /**
  * Remove the given sprite to the collecion.
  * @param s sprite.
  */
 public void removeSprite(Sprite s) {
  this.spriteCollection.remove(s);
 }

 /**
  * call timePassed() on all sprites.
  */
 public void notifyAllTimePassed() {
  List<Sprite> spriteCollectionCopy = new ArrayList<>(this.spriteCollection);
  for (Sprite s : spriteCollectionCopy) {
   s.timePassed();
  }
 }

 /**
  * call drawOn(d) on all sprites.
  *
  * @param d DrawSurface
  */
 public void drawAllOn(DrawSurface d) {
  List<Sprite> spriteCollectionCopy = new ArrayList<>(this.spriteCollection);
  for (Sprite s : spriteCollectionCopy) {
   s.drawOn(d);
  }
 }
}
