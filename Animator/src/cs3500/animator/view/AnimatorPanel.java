package cs3500.animator.view;

import cs3500.animator.model.IShape;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * This class represents the animator panel on which the animation is drawn. The overridden method
 * paint component refreshes the panel with the new shapes.
 */
public class AnimatorPanel extends JPanel {

  private List<IShape> shapes;

  /**
   * Constructs the animation panel and sets the shapes list to an array list.
   */
  public AnimatorPanel() {
    super();
    this.shapes = new ArrayList<>();

  }

  /**
   * Sets the shapes to be drawn.
   *
   * @param shapes the given shapes to be drawn.
   */
  public void setShapes(List<IShape> shapes) {
    this.shapes = shapes;
  }


  @Override
  protected void paintComponent(Graphics g) {
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

