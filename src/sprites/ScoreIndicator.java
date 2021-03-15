package sprites;

import biuoop.DrawSurface;
import collidables.Block;
import game.Counter;
import geometry.Point;
import java.awt.Color;

/**
 * A ScoreIndicator is a block which is used for presentation of the user's score in the game.
 *
 * @author Tom MagdacI
 */
public class ScoreIndicator extends Block {

 //Fields
 private Counter count;

 /**
  * Constructor with configurable point, width, height, color and counter.
  * It uses the super's constructor.
  *
  * @param upperLeft Point
  * @param width width of the indicator
  * @param height height of the indicator
  * @param color color
  * @param counter counter for the score presentation.
  */
 public ScoreIndicator(Point upperLeft, double width, double height, java.awt.Color color, Counter counter) {
  super(upperLeft, width, height, Color.white);
  this.count = counter;
 }

 /**
  * Constructor with configurable point, width, height  and counter.
  *  It uses the super's constructor.
  *
  * @param upperLeft Point
  * @param width width of the indicator
  * @param height height of the indicator
  * @param counter counter for the score presentation.
  */
 public ScoreIndicator(Point upperLeft, double width, double height, Counter counter) {
  super(upperLeft, width, height, Color.white);
  this.count = counter;
 }

 /**
  * ScoreIndicator is drawn first as a regular block.
  * Then this score text is written above the block in its center.
  *
  * @param surface The surface on which the ball will be drawn
  */
 public void drawOn(DrawSurface surface) {
 super.drawOn(surface);
 int value = this.count.getValue();
 int fontSize = 20;
 int devLeftWard = -300;
 int devDownWard = 7;
 double upperSide = this.getCollisionRectangle().getUpperLeft().distance(this.getCollisionRectangle().getUpperRight());
 double side = this.getCollisionRectangle().getUpperLeft().distance(this.getCollisionRectangle().getLowerLeft());
 surface.drawText((int) upperSide / 2 + devLeftWard, (int) side / 2 + devDownWard,
         "Score: " + Integer.toString(value), fontSize);
 }
}
