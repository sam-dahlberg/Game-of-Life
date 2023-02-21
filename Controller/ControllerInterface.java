package Controller;

import Model.ModelInterface;
import java.awt.Point;
import java.util.ArrayList;

/**
 * Contoller for the Game of Life.
 *
 * The idea of a controller is to take in all of the controlls for the game, and properly call
 * the model based on the imputs.
 *
 * All code written by Samuel Dahlberg.
 */

public interface ControllerInterface {

  int tickspeed = 10;

  int height = 20;
  int width = 20;

  void playGame() throws InterruptedException;

  void tick(ArrayList<Point> newCells);

  void quit();
}
