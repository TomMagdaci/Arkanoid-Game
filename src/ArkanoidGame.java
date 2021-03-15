import game.AnimationRunner;
import game.GameFlow;
import game.levels.LevelDirectHit;
import game.levels.LevelWideEasy;
import game.levels.LevelInformation;
import game.levels.LevelGreen3;
import game.levels.LevelFinalFour;

import java.util.LinkedList;
import java.util.List;

/**
 * Create a game object, initializes and runs it.
 *
 * @author Tom Magdaci 316603604
 */
public class ArkanoidGame {

 /**
  * Main method: create new game object, initializes and runs it.
  *
  * @param args array of strings arguments
  */
 public static void main(String[] args) {
  if (args.length == 0) {
   runRegularMode();
  } else {
   runSpecialMode(args);
  }
 }

 /**
  * Run game in regular mode (with all levels and according to their difficulty order).
  */
 public static void runRegularMode() {
  List<LevelInformation> levels = new LinkedList<>();
  levels.add(new LevelDirectHit());
  levels.add(new LevelWideEasy());
  levels.add(new LevelGreen3());
  levels.add(new LevelFinalFour());
  AnimationRunner ar = new AnimationRunner();
  GameFlow gameF = new GameFlow(ar, ar.getGuiKeyboard());
  gameF.runLevels(levels);
  ar.getGui().close();
 }

 /**
  * Run game in manual mode accoridng to the user's input.
  * @param args array of strings
  */
 public static void runSpecialMode(String[] args) {
  List<LevelInformation> levels = new LinkedList<>();
  for (String s : args) {
   switch (s) {
    case "1":
     levels.add(new LevelDirectHit());
     break;
    case "2":
     levels.add(new LevelWideEasy());
     break;
    case "3":
     levels.add(new LevelGreen3());
     break;
    case "4":
     levels.add(new LevelFinalFour());
     break;
    default:
     break;
   }
  }
  if (levels.size() == 0) {
   System.out.println("Wrong Input");
   return;
  }
  AnimationRunner ar = new AnimationRunner();
  GameFlow gameF = new GameFlow(ar, ar.getGuiKeyboard());
  gameF.runLevels(levels);
  ar.getGui().close();
 }
}
