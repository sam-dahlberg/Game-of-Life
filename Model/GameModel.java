package Model;

import java.awt.Point;
import java.util.ArrayList;

public class GameModel implements ModelInterface{

  // map storage in the form of [height][width]
  private Cell[][] map;

  // Might need this to return a Cell[][]
  public GameModel(int height, int width) {
    this.map = new Cell[height][width];

    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        map[i][j] = new Cell(false);
      }
    }
  }

  @Override
  public void update(ArrayList<Point> newCells) {
    Cell[][] updatedMap = map;
    Cell[][] newMap = new Cell[map.length][map[1].length];

    // Add all the manually introduced cells into the map
    for(int i = 0; i < newCells.size(); i++) {
      int height = newCells.get(i).x;
      int width = newCells.get(i).y;
      // Might need to just update cell instead of creating new one. In that case need to make makeLiving function
      // Im pretty sure in C++ this would create inaccessible memory
      updatedMap[height][width] = new Cell(true);
    }

    for(int y = 0; y < updatedMap.length; y++) {
      for(int x = 0; x < updatedMap[1].length; x++) {
        newMap[y][x] = new Cell(updatedMap[y][x].isLiving());
      }
    }

    // Update the cells in map according to game rules
    for(int y = 0; y < updatedMap.length; y++) {
      for(int x = 0; x < updatedMap[1].length; x++) {
        // Make an array list of neighbors, pass that into function that deals with cell updating
        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        // take care of edge cases when adding in neighbors
        if(y != 0) {
          neighbors.add(updatedMap[y-1][x]);

          if(x != 0) {
            neighbors.add(updatedMap[y-1][x-1]);
          }
          if(x != updatedMap[1].length-1) {
            neighbors.add(updatedMap[y-1][x+1]);
          }
        }
        if(y != updatedMap.length-1) {
          neighbors.add(updatedMap[y+1][x]);

          if(x != 0) {
            neighbors.add(updatedMap[y+1][x-1]);
          }
          if(x != updatedMap[1].length-1) {
            neighbors.add(updatedMap[y+1][x+1]);
          }
        }
        if(x != 0) {
          neighbors.add(updatedMap[y][x-1]);
        }
        if(x != updatedMap[1].length-1) {
          neighbors.add(updatedMap[y][x+1]);
        }

        // update the cell using the function in Cell
        newMap[y][x].updateCell(neighbors);
      }
    }

    this.map = newMap;
  }

  public ArrayList<Point> returnMap() {
    ArrayList<Point> livingCells = new ArrayList<Point>();
    for(int i = 0; i < map.length; i++) {
      for(int j = 0; j < map[1].length; j++) {
        if(map[i][j].isLiving()) {
          livingCells.add(new Point(i,j));
        }
      }
    }
    return livingCells;

  }
}
