package Model;

import java.util.ArrayList;

public class Cell {
  private boolean living;

  public Cell (boolean living) {
    this.living = living;
  }

  public boolean isLiving() {
    return living;
  }

  public void updateCell(ArrayList<Cell> neighbors) {
    int liveNeighbors = 0;
    for(Cell neighbor : neighbors) {
      if(neighbor.isLiving()) {
        liveNeighbors++;
      }
    }

    if(this.living) {
      // Rules for living cell
      if(liveNeighbors < 2) {
        this.living = false;
      }
      else if(liveNeighbors > 3) {
        this.living = false;
      }
    } else {
      // Rules for dead cell
      if(liveNeighbors == 3) {
        this.living = true;
      }
    }
  }
}
