package game.UserInterfaces;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;
//import javafx.scene.Node;
import sprites.BackGround;

import java.util.LinkedList;
import java.util.List;

public class MenuAnimation<T> implements Menu<T> {
 //Fields
 private String headLine;
 private KeyboardSensor keyboard;
 private List<List<Object>> listAddLists;
 private boolean running;
 private T pressedT;
 private BackGround bG;
 private boolean isAlreadyStopped;
 private boolean isAlreadyPressed;

 public MenuAnimation(String hL, KeyboardSensor ks) {
  this.headLine = hL;
  this.keyboard = ks;
  this.listAddLists = new LinkedList<>();
  this.running = false;
  this.pressedT = null;
  this.bG = null;
  this.isAlreadyStopped = false;
  this.isAlreadyPressed = false;
 }

 public MenuAnimation(KeyboardSensor ks) {
  this.headLine = "Menu";
  this.keyboard = ks;
  this.listAddLists = new LinkedList<>();
  this.running = false;
  this.pressedT = null;
  this.bG = null;
  this.isAlreadyStopped = false;
  this.isAlreadyPressed = false;
 }

 @Override
 public void doOneFrame(DrawSurface d) {
  if (this.bG != null) {
   this.bG.drawOn(d);
  }
  if (checkIfPressed()) {
   this.running = true;
  }
 }

 public void getBackGround(BackGround newbG) {
  this.bG = newbG;
 }

 @Override
 public boolean shouldStop() {
  /*if (!this.isAlreadyStopped) {
   return this.running;
  } else {
   this.running = false;
   this.isAlreadyStopped = false;
  }*/

  if (!isAlreadyStopped) {
   if (this.running) {
    this.isAlreadyStopped = true;
    return this.running;
   }
  } else {
   this.isAlreadyStopped = false;
   this.running = false;
  }
  return this.running;
 }

 @Override
 public void addSelection(String key, String line, T t) {
  List<Object> l = new LinkedList<>();
  l.add(key);
  l.add(line);
  l.add(t);
  this.listAddLists.add(l);
 }

 private boolean checkIfPressed() {
  List l;
  //List<Object> l1 = new LinkedList();
  for (int i = 0; i < this.listAddLists.size(); i++) {
   l = this.listAddLists.get(i);
   if (this.keyboard.isPressed((String) l.get(0))) {
    //this.isAlreadyPressed = false;
    this.pressedT = (T) l.get(2);
    return true;
    /*if (!this.isAlreadyPressed) {
     this.pressedT = null;
    } else {

    }*/
   }
  }
  return false;
 }

 @Override
 public T getStatus() {
  return this.pressedT;
 }
}
