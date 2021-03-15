package collidables;

import biuoop.DrawSurface;
import geometry.Point;

/**
 * DeathRegoinBlock is a block which its' use and implementation are different from regular Block.
 * DrawOn method is implemented differently, and this block is used in BallsRemoval process.
 *
 * @author Tom Magdaci 
 */
public class DeathRegionBlock extends Block {

 /**
  * Constructor with configurable point, width, height and color.
  * It uses the super's constructor.
  *
  * @param upperLeft Point
  * @param width double
  * @param height double
  * @param color Block's color
  */
 public DeathRegionBlock(Point upperLeft, double width, double height, java.awt.Color color) {
  super(upperLeft, width, height, color);
 }

 /**
  * Constructor with configurable point, width and height.
  * It uses the super's constructor.
  *
  * @param upperLeft Point
  * @param width double
  * @param height double
  */
 public DeathRegionBlock(Point upperLeft, double width, double height) {
  super(upperLeft, width, height);
 }

 /**
  * This special block is drawn without framework,
  * only it's rectangle will be filled with color.
  *
  * @param surface The surface on which the ball will be drawn
  */
 public void drawOn(DrawSurface surface) {
  surface.setColor(this.getColor());
  surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
          (int) this.getCollisionRectangle().getUpperLeft().getY(),
          (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
 }
}
