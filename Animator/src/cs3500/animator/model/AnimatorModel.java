package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface specifies the operations of a model. The model is what controls the functionality
 * of the animation. Operations include producing the text view, and checking for valid motions.
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

  /**
   * Allows the caller to see the height of this animation.
   * @return  The height of this animation as an integer.
   */
  int getHeight();

  /**
   * Allows the caller to see the width of this animation.
   * @return  The width of this animation as an integer.
   */
  int getWidth();

  /**
   * Allows the caller to see the x coordinate of the top left
   * of the window that holds this animation.
   * @return  The x coordinate of the top left of the window that holds this anmation.
   */
  int getWindowX();

  /**
   * Allows the caller to see the y coordinate of the top left
   * of the window that holds this animation.
   * @return  The y coordinate of the top left of the window that holds this anmation.
   */
  int getWindowY();

  /**
   * Allows the caller to see a copy of the map of motions of this animation.
   * @return  A copy of the map of motions of this animation.
   */
  Map<String, List<Motion>> getMotions();

  /**
   * Allows the caller to see a copy of the map of shapes of this animation.
   * @return  A copy of the map of shapes of this animation.
   */
  Map<String, IShape> getShapes();

  /**
   * Allows the caller to see a copy of the map of shape names and types.
   * @return  A copy of the map of shapes and types of this animation.
   */
  Map<String, String> getNameType();
}
