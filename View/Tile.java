package View;

import javax.swing.JButton;

public class Tile extends JButton {
  private int row;
  private int column;

  public Tile (int row, int column, String text) {
    super(text);
    this.row = row;
    this.column = column;
    this.setFocusable(false);
  }
}
