package Controller;

import Model.GameModel;
import Model.ModelInterface;
import View.JFrameView;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameController implements ControllerInterface, ActionListener {

  public static void main(String[] args) throws InterruptedException {
      int height = 20;
      int width  = 20;

      GameModel model = new GameModel(height, width);
      JFrameView view = new JFrameView(height,width);
      GameController controller = new GameController(model, view, height, width);
      controller.playGame();
  }

  // These two bools should be in an abstract class, along with the quit method, but this is a small project.
  private boolean isPaused = false;
  private boolean isQuit = false;

  private ModelInterface model;
  private JFrameView view;

  private ArrayList<Point> newCells;
  private int height;
  private int width;

  public GameController(ModelInterface model, JFrameView view, int height, int width) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Input is null");
    }

    this.model = model;
    this.view = view;

    this.height = height;
    this.width = width;
    this.newCells = new ArrayList<Point>();
  }

  public void playGame() throws InterruptedException {
    view.setListener(this);
    view.display();

    while(!this.isQuit) {
      Thread.sleep(1000);
      tick(newCells);
    }
  }

  @Override
  public void tick(ArrayList<Point> newCells) {
    if (!isPaused) {
      model.update(newCells);
      ArrayList<Point> updateCells = model.returnMap();
      view.updateView(updateCells);
      newCells.clear();
    }
  }

  @Override
  public void quit() {
    this.isQuit = true;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand().equals("Pause")) {
      isPaused = !isPaused;
    } else {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          if (e.getActionCommand().equals(i + " " + j)) {
            newCells.add(new Point(i, j));
            view.addIndividual(new Point(i, j));
          }
        }
      }
    }
  }
}



