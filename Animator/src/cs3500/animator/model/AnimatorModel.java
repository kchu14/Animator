package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface specifies the operations of a model. The model is what controls the functionality
 * of the animation.
 */
public interface AnimatorModel {

  /**
   * Produces a text output representation of the set of motions input into this animation.
   * @return  A String representing the animation through a list of motions.
   */
  String produceTextView();

  /**
   * Checks if any of the motions input into this animation are invalid. This means if they overlap
   * or have gaps in between motions.
   */
  void checkForValidMotions();

  /**
   * Allows the caller to see the final tick of this animation.
   * @return  The final tick of this animation.
   */
  int getLastTick();

  /**
   * Updates the IShapes in the animation based on the tick that the animation is currently at.
   * @param tick  The current tick of the animation.
   * @return  A list of updated IShapes with proper fields that correspond to the current tick.
   */
  List<IShape> update(int tick);

  int getHeight();

  int getWidth();
  int getWindowX();
  int getWindowY();
}
