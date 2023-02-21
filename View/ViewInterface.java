package View;

import java.awt.Point;
import java.util.ArrayList;

/**
 * View for the Game of Life.
 *
 * The idea of a view is to deal with what the player sees on the screen.
 *
 * All code written by Samuel Dahlberg.
 */

public interface ViewInterface {

  void updateView(ArrayList<Point> newCells);

  void addIndividual(Point cell);

}
