package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is an implementation of the IView interface that uses Java Swing to draw the results of the
 * turtle. It shows any error messages using a pop-up dialog box, and shows the turtle position and
 * heading
 */
public class VisualGraphicsView extends JFrame implements AnimatorView {

  private AnimatorPanel animatorPanel;
  private JLabel display;

  public VisualGraphicsView() {
    super();
    this.setTitle("Animator");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();

    // todo
    // scroll bars (setBounds model)


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
    for() {
      refresh();
    }

  }
}
