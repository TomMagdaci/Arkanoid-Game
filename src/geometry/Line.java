package geometry;
import java.util.List;

/**
 * This class generates new Line on the plane.
 *
 * @author Tom Magdaci 
 */
public class Line {

 private Point start;
 private Point end;
 private double slope;

 /**
  * Constructor with configurable start & end points.
  *
  * @param start Start point of the line
  * @param end Snd point of the line
  */
 public Line(Point start, Point end) {
  this.start = start;
  this.end = end;
  this.slope = slopeCalculus(this.start(), this.end());
 }

 /**
  * Constructor with configurable start's x & y values and end's x & y values.
  *
  * @param x1 value in the x axis, of the start point.
  * @param y1 value in the y axis, of the start point.
  * @param x2 value in the x axis, of the end point.
  * @param y2 value in the y axis, of the end point.
  */
 public Line(double x1, double y1, double x2, double y2) {
  this.start = new Point(x1, y1);
  this.end = new Point(x2, y2);
  this.slope = slopeCalculus(this.start(), this.end());
 }

 /**
  *
  * @return length of the line
  */
 public double length() {
  return Math.sqrt(((this.start.getX() - this.end.getX()) * (this.start.getX() - this.end.getX()))
          + ((this.start.getY() - this.end.getY()) * (this.start.getY() - this.end.getY())));
 }

 /**
  *
  * @return middle point of the line
  */
 public Point middle() {
  if (isPoint(this)) { //In case line is a point
   return this.start;
  }
  double midX =  (this.end.getX() + this.start.getX()) / 2;
  double midY = (this.end.getY() + this.start.getY()) / 2;
  Point midP =  new Point(midX, midY);
  return midP;
 }

 /**
  *
  * @return the start point of the line
  */
 public Point start() {
  return this.start;
 }

 /**
  *
  * @return the end point of the line
  */
 public Point end() {
  return this.end;
 }

 /**
  * Check whether two lines are intersecting.
  *
  * @param other a line, to be checked whether it intersects with "this".
  * @return true if the lines intersect, false otherwise
  */
 public boolean isIntersecting(Line other) {
  //First the method will determine if one (or two) of the lines are actually a point.
  if (isPoint(this) && isPoint(other)) { //In case the two lines are points
   if (this.equals(other)) {
    return true;
   }
  }
  if (isPoint(this) && !(isPoint(other))) {
   //In case "this" line is point and "other" is not. check if "this" is on "other"
   if (isOnLine(other, this.start)) {
    return true;
   }
  }
  if (isPoint(other) && !(isPoint(this))) {
   //In case "other" line is point and "this" not. check if "other" is on "this"
   if (isOnLine(this, other.start)) {
    return true;
   }
  }
  if (this.equals(other)) { //if the lines are equals and not points. c
   return false;
  }
  //From now on the method relates to the lines as more than one point.
  //In case the slopes are equal, it can be that one of the lines is a continuation of the other.
  //Also, it may happen when they both have +/- infinite slopes
  //It will be checked whether they have any mutual points.
  if ((this.slope == other.slope) || (Double.isInfinite(this.slope) && Double.isInfinite(other.slope))) {
   if (isIntersectingSameSlopes(other)) {
    return true;
   }
    return false;
  }
  //In case the slopes are not equal
  //For the intersection test the line-segments will be attributed as actual (infinite) lines.
  //Cross points will be calculated.
  //Then the alg determines if the cross point is on each of the line-segments.
  if (this.slope != other.slope) {
   Point cross = findCrossPointBetweenLines(this, other);
   if ((isOnLine(this, cross)) && (isOnLine(other, cross))) {
    return true;
   }
  }
  return false;
 }

 /**
  * Check whether two lines are a continuation of each other (actually one line-segment).
  *
  * @param a optional mutual start/end of the two lines
  * @param c optional mutual start/end of the two lines
  * @param l1 Line
  * @param l2 Line
  * @param b start/end point of one line, optional to be mutual point of the two line
  * @param d start/end point of one line, optional to be mutual point of the two line
  * @return true if only! one point is the mutual point between the lines, otherwise false.
  */
 private boolean arePointsEqualAndOthersNotOnLine(Point a, Point c, Line l1, Line l2, Point b, Point d) {
  //This method checks whether two points (start/end) of two lines are equal.
  //and whether the other (start/end) points respectively not on the lines of each other.
  //In case: Only one start/end point from each line is the only! mutual point of the two lines 'true' will be returned
  if (a.equals(c)) {
   if (!(isOnLine(l1, d)) && !(isOnLine(l2, b))) {
    return true;
   }
  }
  return false;
 }

 /**
  *  Check intersection in case of identical slopes.
  *  Only the case that one line is a continuation of the other,
  *  with only one mutual point only, is considered as intersection.
  *
  * @param other a line, to be checked whether it has mutual start/end point with "this".
  * @return true if the lines intersect (according to the definition above), otherwise false.
  */
 private boolean isIntersectingSameSlopes(Line other) {
  //Only lines with the same slopes were sent to this method (or both +/- infinite)
  //it will be checked whether they have only! one mutual point (start/end).
  //First the intercept value (of the line equation) will be checked.
  //Intercept value is the "c" in this line's equation: "y = mx + c".
  //Two lines with the same slope and different "c" are parallels and have no mutual point.
  //"c" can't be calculated to a line with Infinite slope.
  if (!(Double.isInfinite(this.slope)) && !(Double.isInfinite(other.slope))) {
   if (interceptCalculating(this) != interceptCalculating(other)) {
    return false; //Lines are parallel without any mutual points.
   }
  }
  Point[] pFCmp1 = {this.start, this.end};
  Point[] pFCmp2 = {other.start, other.end};
  Line[] lFCmp = {this, other};
  //These two loops checks whether one line continuation of the other.
  for (int i = 0; i < 2; i++) {
   for (int j = 0; j < 2; j++) {
    // In each iteration (start/end) points of each line are sent for equation checking..
    //..and the other (start/end) points respectively (of each line) are sent for isOnline testing.
    //pFCmp stands for "point for comparing"
    //lFCmp stands for "line for comparing"
    if (arePointsEqualAndOthersNotOnLine(pFCmp1[j], pFCmp2[i], lFCmp[0],
            lFCmp[1], pFCmp1[Math.abs(j - 1)],  pFCmp2[Math.abs(i - 1)])) {
     //return true in case arePointsEqualAndOthersNotOnLine() returned true and that means the lines..
     //.. are continuation of each other with only! one mutual point.
     return true;
    }
   }
  }
  return false;
 }

 /**
  *  Check if a given line is a point.
  *
  * @param l line
  * @return true if given line is a point, otherwise false.
  */
 private boolean isPoint(Line l) {
  return (l.start.equals(l.end));
 }

 /**
  *  Checking if a point is on line.
  *
  * @param l line
  * @param p point
  * @return true if the given point is on the line-segment, otherwise not.
  */
 public boolean isOnLine(Line l, Point p) {
  if ((p.getX() <= Math.max(l.start.getX(), l.end.getX()))
          && (p.getX() >= Math.min(l.start.getX(), l.end.getX()))
          && (p.getY() <= Math.max(l.start.getY(), l.end.getY()))
          && (p.getY() >= Math.min(l.start.getY(), l.end.getY()))) {
   return true;
  }
  return false;
 }

 /**
  * method overloading in case we want to check if a point is on this line.
  *
  * @param p point
  * @return true if the point is on this line, otherwise false
  */
 public boolean isOnLine(Point p) {
  Line l = this;
  return isOnLine(l, p);
 }

 /**
  *  Calculate Line's slope (with a given two points).
  *
  * @param startPoint start point of the line
  * @param endPoint end point of the line
  * @return the slope of the line that goes through these two points.
  */
 private double slopeCalculus(Point startPoint, Point endPoint) {
  //In case the line is actually a point, its fixed slope will be "0"
  int pointSlope = 0;
  if (start.equals(end)) {
   return pointSlope;
  }
  return ((end.getY() - start.getY()) / (end.getX() - start.getX()));
 }

 /**
  *  Calculating the line's intercept ('c').
  *  This data will be a part of the line's equation (y = mx + c).
  *
  * @param l line
  * @return line's intercept ("c")
  */
 private double interceptCalculating(Line l) {
  double c = (l.start.getY() - (l.slope * l.start.getX()));
  return c;
 }

 /**
  *  only lines with different slopes will be sent here.
  *  this function handles the situation in which one of the lines has infinite slope.
  *  for a given two (infinite) lines, the function calculates the cross point.
  *  the calculus here is based on the lines equations.
  *
  * @param l1 line
  * @param l2 line
  * @return (cross) Point.
  */
 private Point findCrossPointBetweenLinesINFCase(Line l1, Line l2) {
  double x, y;
  if (Double.isInfinite(l1.slope)) {
   x = l1.start.getX();
   y = l2.slope * x + interceptCalculating(l2);
  } else { //l2.slope == INF
   x = l2.start.getX();
   y = l1.slope * x + interceptCalculating(l1);
  }
  Point crossP = new Point(x, y);
  return crossP;
 }

 /**
  *  for a given two (infinite) lines, the function calculates the cross point.
  *  the calculus here is based on the lines equations.
  *
  * @param l1 line
  * @param l2 line
  * @return (cross) Point.
  */
 private Point findCrossPointBetweenLines(Line l1, Line l2) {
  if (Double.isInfinite(l1.slope) || Double.isInfinite(l2.slope)) {
   //We assume that only one of them is INF because they are reaching this method...
   //..only in case they have different slopes.
   return findCrossPointBetweenLinesINFCase(l1, l2);
  }
  //Line equation is y = mx + c, in this case we need to equate between the line equations,
  // therefore the formula will be m1*x + c1 = m2*x + c2 (comparing two line's equations),
  //after developing this formula we will get x = (c1 - c2)/(m2 - m1)
  double c1 = interceptCalculating(l1);
  double c2 = interceptCalculating(l2);
  double x = ((c1 - c2) / (l2.slope - l1.slope));
  double y = l1.slope * x + c1;
  Point crossP = new Point(x, y);
  return crossP;
 }

 /**
  * this function uses the isIntersecting() func to check whether if these lines are intersected.
  * In case they are intersected:
  * (isIntersecting() returned *true* explicitly: the lines crossing/ only one line is a point on other line..
  * ..or the lines are continuation of each other (has only one mutual start/end point)
  * First isPoint() func is used to check whether one of them is a point, in case it is
  * and based on isIntersecting() returned value (true) that means this point(line) is on the other line..
  * .. that is why the fun return it's value.
  * Second their slopes are checked, if they are equals or opposite they can be continuation of each other
  * in a double loop the func will search the equal mutual start/end points.
  * Third (for lines with different slopes only) (opposite slopes included)
  * the function uses the findCrossPointBetweenLine() to calculate the cross point.
  *
  * @param other a line, to be checked whether it intersects with "this"
  * @return the intersection point if the lines intersect, otherwise null.
  */
 public Point intersectionWith(Line other) {
  if (!isIntersecting(other)) {
   return null;
  }
  if (isPoint(this) || isPoint(other)) {
   // In case at least one of the lines is a point and they are defined as intersected
   if (isPoint(this)) {
    return this.start;
   }
   return other.start;
  }
  if ((this.slope == other.slope) || (Double.isInfinite(this.slope) && Double.isInfinite(other.slope))) {
   Point[] pFCmp1 = {this.start, this.end};
   Point[] pFCmp2 = {other.start, other.end};
   //if the program reached this stage this means the two lines are not points and..
   //..the lines are intersected with the same slope therefore they are continuation of each other..
   //.. therefore they have only excatly one pair of start/end points that equal.
   for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 2; j++) {
     if (pFCmp1[i].equals(pFCmp2[j])) {
      return pFCmp1[i];
     }
    }
   }
  }
  Point intercP = findCrossPointBetweenLines(this, other);
  return intercP;
 }

 /**
  *  the function checks whether two lines are equal.
  *
  * @param other Line, the line to equate with this.
  * @return true is the lines are equal, false otherwise
  */
 public boolean equals(Line other) {
  if (this.start.equals(other.start) && this.end.equals(other.end)) {
   return true;
  }
  if ((this.end.equals(other.start) && this.start.equals(other.end))) {
   return true;
  }
  return false;
 }

 /**
  * this method's purpose is to find the closest intersection point, of a line and a rectangle,
  * to the line's start point.
  *
  * @param rect rectangle
  * @return Point if there is closest intersection point to the line's start point, otherwise null.
  */
 public Point closestIntersectionToStartOfLine(Rectangle rect) {
  List<Point> intersectionPoints = rect.intersectionPoints(this);
  if (intersectionPoints.size() == 0) {
   return null;
  }
  if (intersectionPoints.size() == 1) {
   return intersectionPoints.get(0);
  }
  int i = 0; //Index used for keeping the min distance while j is running on the array
  for (int j = 1; j < intersectionPoints.size(); j++) {
   if (intersectionPoints.get(j).distance(this.start) < intersectionPoints.get(i).distance(this.start)) {
    i = j;
   }
  }
  return intersectionPoints.get(i);
 }
}
