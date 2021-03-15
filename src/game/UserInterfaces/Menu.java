package game.UserInterfaces;

import game.Animation;

public interface Menu<T> extends Animation {
 void addSelection(String key, String message, T returnVal);
 T getStatus();
}
