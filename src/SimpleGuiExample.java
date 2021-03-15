import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

public class SimpleGuiExample {
 public void drawRandomCircles() {
  Random rand = new Random(); // create a random-number generator
  // Create a window with the title "Random Circles Example"
  // which is 400 pixels wide and 300 pixels high.
  GUI gui = new GUI("Random Circles Example", 400, 300);
  DrawSurface d = gui.getDrawSurface();
  for (int i = 0; i < 10; ++i) {
   int x = rand.nextInt(400) + 1; // get integer in range 1-400
   int y = rand.nextInt(300) + 1; // get integer in range 1-300
   int r = 5*(rand.nextInt(4) + 1); // get integer in 5,10,15,20
   d.setColor(Color.RED);
   d.fillCircle(x,y,r);
  }
  gui.show(d);
 }

 public static void main(String[] args) {
  SimpleGuiExample example = new SimpleGuiExample();
  example.drawRandomCircles();
 }
}
