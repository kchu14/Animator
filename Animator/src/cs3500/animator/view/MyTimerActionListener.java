package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This is an implementation of the action listener interface. It represents our action listener
 * that is used to control the speed of the animation.
 */
class MyTimerActionListener implements ActionListener {

  private int tick;
  private int originalTick;
  private AnimatorModel model;
  private Timer t;
  private AnimatorPanel animatorPanel;
  private boolean isAnimationOver;
  private boolean endOnNextLoop;


  /**
   * Constructs a action listener to be used in the display of the animation.
   *
   * @param tick the current tick
   * @param model the model to be displayed
   * @param t the timer
   */
  public MyTimerActionListener(int tick, AnimatorModel model, Timer t,
      AnimatorPanel animatorPanel) {
    this.tick = tick;
    this.originalTick = tick;
    this.model = model;
    this.t = t;
    this.animatorPanel = animatorPanel;
    this.isAnimationOver = false;
    this.endOnNextLoop = false;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    animatorPanel.updateUI();
    animatorPanel.setShapes(model.getTickListShapes().get(tick));
    tick++;
    if (tick > model.getLastTick()) {
      tick = originalTick;
    }
    if (isAnimationOver) {
      endOnNextLoop = true;
      System.out.println("animation ended");

    }
    if (endOnNextLoop && tick > model.getLastTick()) {
      t.stop();
    }
  }

  public void setIsAnimationOver(Boolean b) {
    this.isAnimationOver = b;
  }
}
