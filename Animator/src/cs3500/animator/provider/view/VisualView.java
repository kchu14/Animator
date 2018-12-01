package cs3500.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import cs3500.model.ExcelAnimatorModel;
import cs3500.model.shape.Shape;

//CHANGES FROM HOMEWORK 6: Made VisualPanel Package-Private and static to be used in the EditorView

/**
 * Represents a Visual view, playing the inputted animation with Java Swing.
 */
public class VisualView extends JFrame implements ExcelAnimatorView {
  private Double speed;
  private ExcelAnimatorModel model;
  private VisualPanel panel;
  private List<Shape> shapes;

  /**
   * Consutrctor for a visual view.
   * @param model model for view
   * @param speed spped to run view at
   */
  public VisualView(ExcelAnimatorModel model, double speed) {
    super("excelAnimator");
    JScrollPane scrollPane;
    this.model = model;
    this.speed = speed;
    this.setPreferredSize(new Dimension(1000, 1000));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.panel = new VisualPanel();
    int[] bounds = this.model.getBounds();
    this.panel.setPreferredSize(new Dimension(bounds[0] + bounds[2], bounds[1] + bounds[3]));
    scrollPane = new JScrollPane(this.panel);
    scrollPane.setBounds(0, 0, 1000, 1000);
    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();


  }

  protected List<Shape> getShapes() {
    return this.shapes;
  }

  /**
   * Draws each frame of animation in the model stored in this view.
   *
   * @throws IOException if output couldn't be appended to
   */
  @Override
  public void play() {
    this.setVisible(true);
    boolean ongoing = true;
    int index = 0;
    this.setVisible(true);

    while (ongoing) {
      this.shapes = this.model.getAnimationState(index);
      this.panel.setShapes(this.shapes);
      this.repaint();
      try {
        TimeUnit.MILLISECONDS.sleep(Math.round((1.0 / this.speed) * 1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      index++;
      if (this.model.getAnimationState(index).size() == 0) {
        ongoing = false;
      }

      //System.out.println(index);

    }

  }


  public void refresh() {
    this.repaint();
  }

  static class VisualPanel extends JPanel {
    List<Shape> shapes;

    VisualPanel() {
      shapes = new ArrayList<>();

    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      String type;
      for (Shape s : this.shapes) {
        type = s.getTextRepresentation().split(" ")[0];
        g2d.setColor(s.getColor());
        if (type.equals("Rectangle") || type.equals("Square")) {
          g2d.fillRect(s.getLocation().x, s.getLocation().y, s.getWidth(), s.getHeight());
        } else if (type.equals("Ellipse") || type.equals("Circle")) {
          g2d.fillOval(s.getLocation().x, s.getLocation().y, s.getWidth(), s.getHeight());
        }
      }
      g2d.dispose();
    }

    public void setShapes(List<Shape> shapes) {
      this.shapes = shapes;
    }
  }
}
