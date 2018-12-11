package cs3500.animator.view;

import cs3500.animator.model.IReadOnlyModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * CHANGE LOG Changed the timer to support the different editable view functions such as pause and
 * fastforwards.
 */

/**
 * This is an implementation of the action listener interface. It represents our action listener
 * that is used to control the speed of the animation.
 */
class MyTimerActionListener implements ActionListener {

  private int tick;
  private int originalTick;
  private IReadOnlyModel model;
  private Timer t;
  private AnimatorPanel animatorPanel;
  private boolean isAnimationOver;
  private boolean endOnNextLoop;
  private boolean isForward;
  private boolean isPaused;
  private EditorPanel v;


  /**
   * Constructs a action listener to be used in the display of the animation.
   *
   * @param tick the current tick
   * @param model the model to be displayed
   * @param t the timer
   * @param animatorPanel the panel being acted upon
   * @param v the editable panel which has a scrubber.
   */
  public MyTimerActionListener(int tick, IReadOnlyModel model, Timer t,
      AnimatorPanel animatorPanel, EditorPanel v) {
    this.tick = tick;
    this.originalTick = tick;
    this.model = model;
    this.t = t;
    this.animatorPanel = animatorPanel;
    this.isAnimationOver = false;
    this.endOnNextLoop = false;
    this.isForward = true;
    this.v = v;
  }

  /**
   * Constructs a action listener to be used in the display of the animation.
   *
   * @param tick the current tick
   * @param model the model to be displayed
   * @param t the timer
   * @param animatorPanel the panel being acted upon
   */
  public MyTimerActionListener(int tick, IReadOnlyModel model, Timer t,
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
        if (v != null) {
          v.setCurrentTick(tick);
        }
      } else {
        tick--;
        if (v != null) {
          v.setCurrentTick(tick);
        }
      }
    }
    if (isAnimationOver) {
      this.endOnNextLoop = true;

    }
    if (endOnNextLoop && (tick > model.getLastTick() || tick <= 0)) {
      t.stop();
    }
    if (tick > model.getLastTick()) {
      tick = originalTick;
    } else if (tick < 1) {
      tick = model.getLastTick();
    }

  }

  /**
   * Sets if the animation is over.
   *
   * @param b boolean stating whether if the animation is over.
   */
  protected void setIsAnimationOver(Boolean b) {
    this.isAnimationOver = b;
  }


  /**
   * Rewinds the animations. (Plays the animation in reverse)
   */
  protected void rewind() {
    isForward = !isForward;
  }

  /**
   * Speeds up the rate at which the animation is displayed.
   */
  protected void fastforward() {
    if (t.getDelay() <= 1) {
      t.setDelay(t.getInitialDelay());
    } else {
      t.setDelay(t.getDelay() / 5);
    }
  }


  /**
   * Slows down the rate at which the animation is displayed.
   */
  protected void slowDown() {
    if (t.getDelay() >= 1000) {
      t.setDelay(t.getInitialDelay());
    } else {
      t.setDelay(t.getDelay() * 5);
    }
  }

  /**
   * Pauses the animation. And flips the button if paused turns to resume.
   */
  protected void pause() {
    isPaused = !isPaused;
  }

  /**
   * Sets the value of the view to be paused.
   *
   * @param paused True if paused, false if resumed.
   */
  void setPaused(boolean paused) {
    isPaused = paused;
  }

  /**
   * Sets the tick of the animation (used in scrubbing).
   *
   * @param tick the tick to be set.
   */
  void setTick(int tick) {
    this.tick = tick;
  }
}
