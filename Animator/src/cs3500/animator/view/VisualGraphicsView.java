package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * CHANGE LOG Added methods that allowed the panel to support functionality from the editable view.
 * Functionality included fast forwarding and pause.
 */

/**
 * This is an implementation of the Animator View interface that uses Java Swing to draw the results
 * of the animation. It plays the animation using a timer to the users specified speed.
 */
public class VisualGraphicsView extends JFrame implements IVisualGraphicsView {

  private AnimatorPanel animatorPanel;
  private int speed;
  private MyTimerActionListener listener;


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


  @Override
  public void playAnimation(IReadOnlyModel model) {
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
    initiateTimer(model, animatorPanel);
  }

  @Override
  public void initiateTimer(IReadOnlyModel model, AnimatorPanel animatorPanel) {
    int i = 0;
    Timer t = new Timer(1000 / speed, null);
    listener = new MyTimerActionListener(i, model, t, animatorPanel);
    t.addActionListener(listener);
    t.start();
  }

  @Override
  public void setIsAnimationOver(Boolean b) {
    listener.setIsAnimationOver(b);
  }


  @Override
  public void rewind(IReadOnlyModel model) {
    listener.rewind();
  }

  @Override
  public void fastforward() {
    listener.fastforward();
  }

  @Override
  public void slowDown() {
    listener.slowDown();
  }

  @Override
  public void pause() {
    listener.pause();
  }

  @Override
  public JPanel getAnimation() {
    return animatorPanel;
  }

}
