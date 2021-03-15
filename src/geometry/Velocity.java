package geometry;
/**
 * This class creates a new velocity.
 * Velocity specifies the delta in position on the `x` and the `y` axis.
 *
 * @author Tom Magdaci 316603604
  */
public class Velocity {

 private double dx;
 private double dy;

 /**
  * Constructor with configurable delta x and delta y.
  *
  * @param dx delta in the 'x' axis
  * @param dy delta in the 'y' axis
  */
 public Velocity(double dx, double dy) {
  this.dx = dx;
  this.dy = dy;
 }

 /**
  * Generates velocity object with angle and speed using the constructor above.
  * this method interpret the angle and speed terms into delta in 'x' and 'y' axis.
  *
  * @param angle angle of the progress
  * @param speed units of the progress
  * @return new velocity
  */
 public static Velocity fromAngleAndSpeed(double angle, double speed) {
  //The calculating of the dx and dy will be based on the 'right triangle' traits.
  //In order to advance in the requierd angle and speed,
  //the alg attributes the speed as the length of the hypotenuse that is part from a right triangle.
  //and the angle is the angle between the hypotenuse to the side.
  //Then, the advancement on each axis will be calculated separately.
  //It is necessary to use in a dev Angle in order to define the progress as it is required in the exercise orders
  //(angle "0" should be interpreted as direction upward).
  //Then, a new velocity will be created using the calculated dx, dy.
  int straightAngle = 180, devAngle = 90;
  double dx = Math.cos(((angle - devAngle) / straightAngle) * Math.PI) * speed;
  double dy = Math.sin(((angle - devAngle) / straightAngle) * Math.PI) * speed;
  return new Velocity(dx, dy);
 }

 /**
  * Take a point with position (x,y) and return a new point with position of (x+dx, y+dy).
  *
  * @param p point
  * @return point (after the change)
  */
 public Point applyToPoint(Point p) {
  //For this we should create a new point in the plane with p values and the d(x/y) values.
  Point newP = new Point((p.getX() + this.dx), (p.getY() + this.dy));
  p = newP;
  return p;
 }

 /**
  * @return dx value of delta in the x axis.
  */
 public double getDX() {
  return this.dx;
 }

 /**
  * @return dy value of delta in the y axis.
  */
 public double getDY() {
  return this.dy;
 }

 /**
  * In case dx, dy != 0
  * The speed is the length of the hypotenuse that is part of the right triangle
  * that consists dx,dy as his sides.
  * In case dx = 0
  * Speed = dy
  * In case dy = 0
  * Speed = dx
  * this speed is used in MultipleBouncingBallsAnimation class,
  * there for array of balls, each ball should get velocity in the opposite relation to his size.
  * At the same time his "direction" should be arbitrary.
  * Therefore we will use the Velocity.fromAngleAndSpeed method and because of that
  * The speed of each precented ball used for comparing with the next one.
  *
  * @return return the speed of the unit.
  */
 public double getSpeed() {
  if (this.getDX() == 0) {
   return this.getDY();
  }
  if (this.getDY() == 0) {
   return this.getDX();
  }
  return (Math.sqrt(Math.pow(this.getDX(), 2) + Math.pow(this.getDY(), 2)));
 }
}