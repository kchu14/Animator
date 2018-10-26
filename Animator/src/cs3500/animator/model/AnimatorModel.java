package cs3500.animator.model;

import java.util.List;

/**
 * This interface specifies the operations of an AnimatorModel.
 *
 */
public interface AnimatorModel {

  /**
   * This method enumerates all of the shapes in the model to one list to be visualized.
   * @return a list of all of the shapes that exist inside of this model
   */
  List<IShape> draw();

  /**
   * Checks to see if the animation has been completed.
   * @return true if the animation is over, and false if it is not.
   */
  boolean isAnimationOver();

  /**
   * Adds a shape to the map of shapes inside of the animator model.
   * @param s The shape that is to be added
   */
  void addShape(AShape s);

}
