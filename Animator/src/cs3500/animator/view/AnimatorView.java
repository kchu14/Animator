package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

public interface AnimatorView {
  /**
   * This model executes the animation and begins the drawing process.
   *
   * @param model the given model to be animated.
   */
  void playAnimation(AnimatorModel model);

}
