package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

/**
 * This interface represents the operations that can be performed on a view. These actions include
 * playing the animation and showing error messages.
 */
public interface AnimatorView {


  /**
   * Transmit an error message to the view, in case the command could not be processed correctly
   */
  void showErrorMessage(String error);

  /**
   * This model executes the animation and begins the drawing process.
   *
   * @param model the given model to be animated.
   */
  void playAnimation(AnimatorModel model);


}
