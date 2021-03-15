package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * BackGroundBallAnimation is a sprite that is used as a background in game.
 *
 * @author Tom Magdaci 316603604
 */
 public class BackGroundBallAnimation implements Sprite {

  //Fields
  private int x;
  private int y;
  private int r;
  private boolean fill;
  private Color color;

 /**
  * Constructor with configurable color and locations and flag (fill the ball or not).
  * @param c color
  * @param nX int
  * @param nY int
  * @param nR int
  * @param f boolean
  */
  public BackGroundBallAnimation(Color c, int nX, int nY, int nR, boolean f) {
   this.color = c;
   this.x = nX;
   this.y = nY;
   this.r = nR;
   this.fill = f;
  }

  /**
   * Return the background color.
   * @return color
   */
  public Color getColor() {
   return this.color;
  }

  @Override
  public void drawOn(DrawSurface d) {
   d.setColor(this.color);
   if (this.fill) {
    d.fillCircle(this.x, this.y, this.r);
   }
   d.drawCircle(this.x, this.y, this.r);
  }

  @Override
  public void timePassed() {
  }
 }
