package Model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Model for the Game of Life
 *
 * The idea of a model is to run all the processes for the game.
 *
 * All code written by Samuel Dahlberg.
 */

public interface ModelInterface {

  // update class, will be called every tick. Takes in a list of new cells added to game.
  // Make the assumption that all new cells are new, and not repeats on the board.
  void update(ArrayList<Point> newCells);

  ArrayList<Point> returnMap();
}
