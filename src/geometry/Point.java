package geometry;
/**
 * This class generates new Point on the plane.
 *
 * @author Tom Magdaci 
 */
public class Point {

 private double x;
 private double y;

 /**
  * constructor with configurable x and y.
  * @param x value in the x axis.
  * @param y value ine the y axis
  */
 public Point(double x, double y) {
  this.x = x;
  this.y = y;
 }

 /**
  * Measure the distance between this point to other point.
  * @param other point, a distance is measured between "this" and "other".
  * @return distance.
  */
 public double distance(Point other) {
  return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
 }

 /**
  * Determine if two points are equal.
  * @param other point, to be compared with "this"
  * @return true if the points are equal, otherwise not.
  */
 public boolean equals(Point other) {
  //Create a reference value (called epsilon) it will be used as an threshold while comparing between two doubles.
  double epsilon = Math.pow(10, -15);
  return ((Math.abs(this.x - other.x) <= epsilon) && (Math.abs(this.y - other.y) <= epsilon));
 }

 /**
  *
  * @return value of the point in the x axis.
  */
 public double getX() {
  return this.x;
 }

 /**
  *
  * @return value of the point in the y axis.
  */
 public double getY() {
  return this.y;
 }
}
