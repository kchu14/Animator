package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * This is an implementation of the IView interface that uses Java Swing to draw the results of the
 * turtle. It shows any error messages using a pop-up dialog box, and shows the turtle position and
 * heading
 */
public class VisualGraphicsView extends JFrame implements AnimatorView {

  private AnimatorPanel animatorPanel;
  private JLabel display;
  private int width;
  private int height;


  public VisualGraphicsView() {
    super();


  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void playAnimation(AnimatorModel model) {
    makeVisible();
    int i = 0;
    Timer t = new Timer(2, null);
    ActionListener listener = new MyTimerActionListener(i, model, t);
    t.addActionListener(listener);
    t.start();
  }

  class MyTimerActionListener implements ActionListener {

    private int tick;
    private AnimatorModel model;
    private Timer t;

    private MyTimerActionListener(int tick, AnimatorModel model, Timer t) {
      this.tick = tick;
      this.model = model;
      this.t = t;
    }

    public void actionPerformed(ActionEvent e) {
      refresh();
      animatorPanel.setShapes(model.update(tick));
      tick++;
      if(tick > model.getLastTick()) {
        t.stop();
      }
    }
  }


  @Override
  public void setWindow(int width, int height, int x, int y) {
    this.height = height;
    this.setTitle("Animator");
    this.setSize(width, height);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(x, y);
    this.setLayout(new BorderLayout());
    animatorPanel = new AnimatorPanel();
    animatorPanel.setPreferredSize(new Dimension(width, height));
    JScrollPane pane = new JScrollPane(animatorPanel);
    this.add(pane, BorderLayout.CENTER);
    this.pack();

  }

}
