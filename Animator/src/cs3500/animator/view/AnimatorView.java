package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;

/**
 * This interface represents the broadest interpretation of an animator view. Each individual
 * implementation of the view inherits the methods inside of this interface.
 */
public interface AnimatorView {
  /**
   * This model executes the animation and begins the drawing process.
   *
   * @param model the given model to be animated.
   */
  void playAnimation(ReadOnlyModel model);

}
