package cs3500.animator.view;

import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

public class AnimatorPanel extends JPanel {

  private Map<String, IShape> shapes;
  // shape name, list of motions for the shape
  private Map<String, List<Motion>> nameMotion;
  public AnimatorPanel() {
    super();
    this.shapes = new HashMap<>();
    this.shapes = new HashMap<>();
  }

  public void setShapes(Map<String, IShape> shapes) {
    this.shapes = shapes;
  }

  public void setNameMotion(
      Map<String, List<Motion>> nameMotion) {
    this.nameMotion = nameMotion;
  }

  @Override
  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);
  }
}
