package geometry;
import java.util.List;
import java.util.ArrayList;

/**
 * This class generates new Rectangle object on the plane.
 *
 * @author Tom Magdaci 
 */
public class Rectangle {

 //Fields
 private Point upperLeft;
 private Point upperRight;
 private Point lowerLeft;
 private Point lowerRight;
 private double width;
 private double height;

 /**
  * Constructor with configurable upperleft point, width and height.
  *
  * @param upperLeft the upperleft point of the rectangle.
  * @param width the width of the rectangle.
  * @param height the height of the rectangle.
  */
 // Create a new rectangle with location and width/height.
 public Rectangle(Point upperLeft, double width, double height) {
  this.upperLeft = upperLeft;
  this.width = width;
  this.height = height;
  this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
  this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
  this.lowerRight = new Point(upperRight.getX(), lowerLeft.getY());
 }

 /**
  * This method creates four lines, each one is the a side on the rectangle.
  * @return array of four lines.
  */
 public Line[] create4LinesFromRecSides() {
  int recSidesNum = 4;
  Point[] upperPoints = {this.upperLeft, this.lowerRight};
  Point[] lowerPoints = {this.lowerLeft, this.upperRight};
  Line[] recLines = new Line[recSidesNum];
  int k = 0;
  for (int i = 0; i < 2; i++) {
   for (int j = 0; j < 2; j++) {
    //recLines[0]: line(upperleft, lowerleft) = LEFT side of the rectangle
    //reclines[1]: line(upperleft, upperRight) = TOP side of the rectangle
    //recLines[2]: line(lowerRight, lowerLeft)= BOTTOM side of the rectangle
    //recLines[3]: line(lowerRight, upperRight)= RIGHT side of the rectangle
    recLines[k] = new Line(upperPoints[i], lowerPoints[j]);
    k++;
   }
  }
  return recLines;
 }

 /**
  * @param line Line
  * @return Return a List of intersection points (can be empty) with a given Line.
  */
 public java.util.List<Point> intersectionPoints(Line line) {
  Line[] recLines = create4LinesFromRecSides();
  List<Point> intersectionPoints = new ArrayList<Point>();
  //In a loop each rectangle line will be checked whether it intersected with "line"
  //if they are intersecting, intersection points will be calculated (Line class methods)
  //and this point will be added to "intersectionPoints" list.
  for (int i = 0; i < recLines.length; i++) {
   if (recLines[i].isIntersecting(line)) {
    intersectionPoints.add(recLines[i].intersectionWith(line));
   }
  }
  if (intersectionPoints.size() > 0) {
   return intersectionPoints;
  }
  return null;
 }

 /**
  * @return the width of the rectangle
  */
 public double getWidth() {
  return this.width;
 }

 /**
  * @return the height of the rectangle
  */
 public double getHeight() {
  return this.height;
 }

 /**
  * @return the upperLeft point of the rectangle
  */
 public Point getUpperLeft() {
  return this.upperLeft;
 }

 /**
  * @return the upperRight point of the rectangle
  */
 public Point getUpperRight() {
  return this.upperRight;
 }

 /**
  * @return the lowerLeft point of the rectangle
  */
 public Point getLowerLeft() {
  return this.lowerLeft;
 }

 /**
  * @return the lowerRight point of the rectangle
  */
 public Point getLowerRight() {
  return this.lowerRight;
 }
}
