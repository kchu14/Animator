package cs3500.animator.view;


import cs3500.animator.model.IReadOnlyModel;
import javax.swing.JPanel;

/**
 * This interface represents the operations that can be performed on a visual graphics view. These
 * actions include playing the animation and showing error messages.
 */
public interface IVisualGraphicsView extends AnimatorView {


  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   */
  void showErrorMessage(String error);

  /**
   * Returns the panel on which the animation is drawn.
   *
   * @return the drawn animation.
   */
  JPanel getAnimation();

  /**
   * Starts the timer on which the animation runs.
   *
   * @param model the given model of the animation to be run.
   * @param animatorPanel the given panel that the animation is drawn on.
   */
  void initiateTimer(IReadOnlyModel model, AnimatorPanel animatorPanel);

  /**
   * Starts the timer on which the animation runs.
   *
   * @param model the given model of the animation to be run.
   * @param animatorPanel the given panel that the animation is drawn on.
   * @param v The editable view with the scrubber to be manipulated.
   */
  void initiateTimerWithView(IReadOnlyModel model, AnimatorPanel animatorPanel, EditorPanel v);

  /**
   * Sets if the animation is over.
   *
   * @param b boolean stating whether or not the animation is over.
   */
  void setIsAnimationOver(Boolean b);

  /**
   * Rewinds the animations. (Plays the animation in reverse)
   *
   * @param model the model of the animation to played backwards.
   */
  void rewind(IReadOnlyModel model);

  /**
   * Speeds up the rate at which the animation is displayed.
   */
  void fastforward();

  /**
   * Slows the rate at which the animation is displayed.
   */
  void slowDown();

  /**
   * Toggles Pause/Play for the animation.
   */
  void pause();

  /**
   * Sets the value of the view to be paused.
   *
   * @param paused True if paused, false if resumed.
   */
  void setPaused(boolean paused);

  /**
   * Displays the current tick in the animation.
   *
   * @param tick The tick meant to be displayed.
   */
  void displayTick(int tick);
}
