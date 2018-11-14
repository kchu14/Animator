package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * This is an implementation of the Animator View interface that uses Java Swing to draw the results
 * of the animation. It plays the animation using a timer to the users specified speed.
 */
public class VisualGraphicsView extends JFrame implements IVisualGraphicsView {

  private AnimatorPanel animatorPanel;
  private int speed;


  /**
   * Constructs a visual graphics view.
   *
   * @param speed the given speed of the animation.
   */
  public VisualGraphicsView(int speed) {
    super();
    this.speed = speed;


  }

  /**
   * Sets the window to be visible.
   */
  private void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Refreshes the panel with the new shapes that are drawn.
   */
  private void refresh() {
    this.repaint();
  }

  @Override
  public void playAnimation(AnimatorModel model) {
    this.setTitle("Animator");
    this.setSize(model.getWidth(), model.getHeight());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(model.getWindowX(), model.getWindowY());
    this.setLayout(new BorderLayout());
    animatorPanel = new AnimatorPanel();
    animatorPanel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    JScrollPane pane = new JScrollPane(animatorPanel);
    this.add(pane, BorderLayout.CENTER);
    this.pack();
    makeVisible();
    int i = 0;
    Timer t = new Timer(1000 / speed, null);
    ActionListener listener = new MyTimerActionListener(i, model, t);
    t.addActionListener(listener);
    t.start();
  }

  @Override
  public JPanel getAnimation() {
//    animatorPanel = new AnimatorPanel();
//    animatorPanel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
//    JScrollPane pane = new JScrollPane(animatorPanel);
//    this.add(pane, BorderLayout.CENTER);
//    this.pack();
//    makeVisible();
//    int i = 0;
//    Timer t = new Timer(1000 / speed, null);
//    ActionListener listener = new MyTimerActionListener(i, model, t);
//    t.addActionListener(listener);
//    t.start();
    return animatorPanel;
  }

  /**
   * This is an implementation of the action listener interface. It represents our action listener
   * that is used to control the speed of the animation.
   */
  class MyTimerActionListener implements ActionListener {

    private int tick;
    private AnimatorModel model;
    private Timer t;

    /**
     * Constructs a action listener to be used in the display of the animation.
     *
     * @param tick the current tick
     * @param model the model to be displayed
     * @param t the timer
     */
    private MyTimerActionListener(int tick, AnimatorModel model, Timer t) {
      this.tick = tick;
      this.model = model;
      this.t = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      refresh();
      animatorPanel.setShapes(model.getTickListShapes().get(tick));
      tick++;
      if (tick > model.getLastTick()) {
        t.stop();
      }
    }
  }


}
