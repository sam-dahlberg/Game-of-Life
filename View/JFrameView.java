package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class JFrameView extends JFrame implements ViewInterface {
  final private int cellSize = 20;
  private int height;
  private int width;
  private Tile[][] gameboard;
  JPanel board;
  JPanel button;
  JPanel layer;
  JToggleButton pause;
  Tile tile;

  // width and height are the amount of cells in the map
  public JFrameView(int width, int height) {
    super("Game of Life");
    this.board = new JPanel(new GridLayout(height,width, 0, 0));
    this.height = height;
    this.width = width;
    this.gameboard = new Tile[height][width];
    this.setSize(this.width * cellSize,this.height * cellSize);
    this.setLocation(200,200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    for(int i = 0; i < height; i++) {
      for(int j = 0; j < width; j++) {
        tile = new Tile(i, j,  "");
        tile.setActionCommand(i + " " + j);
        tile.setBackground(Color.WHITE);
        tile.setPreferredSize(new Dimension(cellSize, cellSize));
        tile.setMargin(new Insets(0,0,0,0));
        this.gameboard[i][j] = tile;
        this.board.add(gameboard[i][j]);
        this.button = new JPanel();
        pause = new JToggleButton("Pause");
        this.button.add(pause);
      }
    }
    this.layer = new JPanel(new BorderLayout());
    this.layer.add(button,BorderLayout.PAGE_START);
    this.layer.add(board,BorderLayout.CENTER);
    this.add(layer);
  }

  public void display() { setVisible(true); }

  public void setListener(ActionListener listener) {
    this.pause.addActionListener(listener);
    for(int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.gameboard[i][j].addActionListener(listener);
      }
    }
  }

  public void updateView(ArrayList<Point> newCells) {
    for(int j = 0; j < height; j++) {
      for(int k = 0; k < width; k++) {
        this.gameboard[j][k].setBackground(Color.WHITE);
      }
    }
    for(int i = 0; i < newCells.size(); i++) {
        this.gameboard[newCells.get(i).x][newCells.get(i).y].setBackground(Color.BLACK);
    }
  }

  public void addIndividual(Point cell) {
    this.gameboard[cell.x][cell.y].setBackground(Color.BLACK);
  }

}
