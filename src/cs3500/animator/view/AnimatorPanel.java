package cs3500.animator.view;

import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

public class AnimatorPanel extends JPanel {

  private List<IShape> shapes;

  public AnimatorPanel() {
    super();
    this.shapes = new ArrayList<>();

  }

  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }


  @Override
  protected void paintComponent(Graphics g) {
    //never forget to call super.paintComponent!
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    AffineTransform originalTransform = g2d.getTransform();
    for (IShape s : shapes) {
      g2d.setColor(s.getColor());
      g2d.fill(s.getShape());
    }
    g2d.setTransform(originalTransform);
  }


}

