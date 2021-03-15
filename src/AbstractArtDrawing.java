import geometry.Point;
import geometry.Line;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * This class generate surface in which it draw random lines and their middle and intersection points.
 *
 * @author Tom Magdaci 316603604
 */
public class AbstractArtDrawing {

 //Parameters of the animation.
 //LINESUM represents the number of the drawn lines. POINTR represents the radius of the intersection/mid points
 static final int LINESNUM = 30;
 static final int POINTR = 2;
 //GUI Boundaries:
 //WIDTH & HEIGHT are being used by the methods below therefore it is more convenient whereas they are defined here.
 static final int WIDTH = 800;
 static final int HEIGHT = 700;

 /**
  * Generate random new line, and send in back.
  *
  * @param rand random number generator.
  * @return line
  */
 private Line generateRandomLine(Random rand) {
  //using the random-number generator the alg creates four numbers that will be related as two points on the plane.
  int x1 = rand.nextInt(WIDTH) + 1; // get integer in range 1-400
  int y1 = rand.nextInt(HEIGHT) + 1; // get integer in range 1-300
  int x2 = rand.nextInt(WIDTH) + 1; // get integer in range 1-400
  int y2 = rand.nextInt(HEIGHT) + 1; // get integer in range 1-300
  Line l = new Line(x1, y1, x2, y2);
  return l;
 }

 /**
  * Drawing the line on the DrawSurface and drawing its middle point.
  *
  * @param l Line for drawing
  * @param d DrawSurface
  */
 private void drawLine(Line l, DrawSurface d) {
  //Implicit casting is required because DrawSurface funcs don't accept doubles
  int x1 = (int) l.start().getX();
  int y1 = (int) l.start().getY();
  int x2 = (int) l.end().getX();
  int y2 = (int) l.end().getY();
  int midLX = (int) l.middle().getX();
  int midLY = (int) l.middle().getY();
  d.setColor(Color.BLACK);
  d.drawLine(x1, y1, x2, y2);
  d.setColor(Color.BLUE);
  d.fillCircle(midLX, midLY, POINTR);
 }

 /**
  * Searching for intersection points (of array of lines).
  * Drawing of the intersection points.
  *
  * @param l Array of lines.
  * @param d DrawSurface.
  */
 private void drawIntersectionPoints(Line[] l, DrawSurface d) {
  d.setColor(Color.RED); //RED is the color for the intersection point.
  int x, y; //will represent the x,y of the intersection point of every line crossing.
  Point crossP; // will represent the intersection point
  for (int i = 0; i < LINESNUM; i++) {
   for (int j = 0; j < LINESNUM; j++) {
    if (l[i].isIntersecting(l[j])) { //check whether two given lines are intersected.
     crossP = l[i].intersectionWith(l[j]);
     x = (int) crossP.getX();
     y = (int) crossP.getY();
     d.fillCircle(x, y, POINTR);
    }
   }
  }
 }

 /**
  * DrawRandomLines on specific surface.
  * This method uses others for generate DrawSurface, lines, drawing points.
  *
  */
 public void drawRandomLines() {
  Random rand = new Random(); // create a random-number generator
  // Create a window with the title "Random Lines Example"
  // which is 400 pixels wide and 300 pixels high.
  GUI gui = new GUI("Random Lines Example", WIDTH, HEIGHT);
  DrawSurface d = gui.getDrawSurface();
  Line[] l = new Line[LINESNUM]; //create an empty lines array.
  for (int i = 0; i < LINESNUM; ++i) {
   l[i] = generateRandomLine(rand);
   drawLine(l[i], d);
  }
  drawIntersectionPoints(l, d);
  gui.show(d);
 }

 /**
  * Main method Initialize an object example. Then uses other method it creates the animation.
  *
  * @param args array of strings
  */
 public static void main(String[] args) {
  AbstractArtDrawing example = new AbstractArtDrawing();
  example.drawRandomLines();
 }
}
