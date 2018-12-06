package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface specifies the operations of a model. The model is what controls the functionality
 * of the animation. Operations include producing the text view, and checking for valid motions.
 */
public interface AnimatorModel {

  /**
   * Allows the caller to see the final tick of this animation.
   *
   * @return The final tick of this animation.
   */
  int getLastTick();


  /**
   * Allows the caller to see the shapes to be animated on every tick.
   *
   * @return The map of ticks and shapes to be animated.
   */
  Map<Integer, List<IShape>> getTickListShapes();

  /**
   * Allows the caller to see the height of this animation.
   *
   * @return The height of this animation as an integer.
   */
  int getHeight();

  /**
   * Allows the caller to see the width of this animation.
   *
   * @return The width of this animation as an integer.
   */
  int getWidth();

  /**
   * Allows the caller to see the x coordinate of the top left of the window that holds this
   * animation.
   *
   * @return The x coordinate of the top left of the window that holds this anmation.
   */
  int getWindowX();

  /**
   * Allows the caller to see the y coordinate of the top left of the window that holds this
   * animation.
   *
   * @return The y coordinate of the top left of the window that holds this anmation.
   */
  int getWindowY();

//  /**
//   * Allows the caller to see a copy of the map of motions of this animation.
//   *
//   * @return A copy of the map of motions of this animation.
//   */
//  Map<String, List<IMotion>> getMotions();

  /**
   * Allows the caller to see a copy of the map of shapes of this animation.
   *
   * @return A copy of the map of shapes of this animation.
   */
  Map<String, IShape> getShapes();

  /**
   * Allows the caller to see a copy of the map of shape names and types.
   *
   * @return A copy of the map of shapes and types of this animation.
   */
  Map<String, String> getNameType();

  /**
   * Allows the caller to see a copy of the keyframes map. Converts our motions which contain
   * a start and end time into keyframe variations which have the same start and end time. These
   * key frames are used in displaying the editable keyframes in the editable view.
   *
   * @return A copy of the map shape names and the keyframes for the shape.
   */
  Map<String, List<IMotion>> getKeyFrames();

  /**
   * Allows the user to declare a new shape (add a shape to the model).
   *
   * @param shape The given shape to be added to the model.
   */
  void declareNewShape(IShape shape);

  /**
   * Removes a given shape from the model.
   *
   * @param name the name of the shape to be removed.
   */
  void removeShape(String name);

  /**
   * Adds a given motion to the model.
   *
   * @param m The given motion to be added to the model.
   */
  void addNewMotion(IMotion m);

  /**
   * Edits a given motion inside the model.
   *
   * @param newMotion the given motion to be edited.
   */
  void editMotion(IMotion newMotion);

  /**
   * Removes a motion from the model.
   *
   * @param motion The given motion to be removed from the model.
   */
  void removeMotion(IMotion motion);

  /**
   * Adds a shape to our shapes map given a motion (the initial shape of this motion).
   *
   * @param m the given motion that is acted on a shape.
   */
  void addShape(IMotion m);

}
