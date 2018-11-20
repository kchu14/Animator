package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;
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
  private ReadOnlyModel model;
  private Timer t;
  private AnimatorPanel animatorPanel;
  private boolean isAnimationOver;
  private boolean endOnNextLoop;
  private boolean isForward;
  private boolean isPaused;


  /**
   * Constructs a action listener to be used in the display of the animation.
   *
   * @param tick the current tick
   * @param model the model to be displayed
   * @param t the timer
   */
  public MyTimerActionListener(int tick, ReadOnlyModel model, Timer t,
      AnimatorPanel animatorPanel) {
    this.tick = tick;
    this.originalTick = tick;
    this.model = model;
    this.t = t;
    this.animatorPanel = animatorPanel;
    this.isAnimationOver = false;
    this.endOnNextLoop = false;
    this.isForward = true;

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    animatorPanel.updateUI();
    animatorPanel.setShapes(model.getTickListShapes().get(tick));
    if (!isPaused) {
      if (this.isForward) {
        tick++;
      } else {
        tick--;
      }
    }
    if (isAnimationOver) {
      this.endOnNextLoop = true;

    }
    if (endOnNextLoop && (tick > model.getLastTick() || tick <= 0)) {
      t.stop();
      System.out.println("animation ended");
    }
    if (tick > model.getLastTick()) {
      tick = originalTick;
    } else if (tick < 1) {
      tick = model.getLastTick();
    }
  }

  protected void setIsAnimationOver(Boolean b) {
    this.isAnimationOver = b;
  }

  protected void rewind() {
    isForward = !isForward;
  }

  protected void fastforward() {
    if (t.getDelay() == 1) {
      t.setDelay(t.getInitialDelay());
    } else {
      t.setDelay(t.getDelay() / 5);
    }
    System.out.println(t.getDelay());
  }


  protected void slowDown() {
    if (t.getDelay() >= 1000) {
      t.setDelay(t.getInitialDelay());
    } else {
      t.setDelay(t.getDelay() * 5);
    }
    System.out.println(t.getDelay());
  }

  protected void pause() {
    isPaused = !isPaused;
  }
}
