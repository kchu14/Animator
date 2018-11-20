package cs3500.animator.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * CHANGE LOG Created a read only model to be passed into the view. This allows the view to gather
 * the information from the model without mutation being allowed.
 */

/**
 * This class represents a read only model of the given AnimatorModelImpl. This class only contains
 * methods that are immutable to the given animator model. This read only model is passed to the
 * view to construct a visual representation.
 */
public class ReadOnlyModel {

  private AnimatorModel model;

  /**
   * Constructs a read only model.
   *
   * @param model the given Animator model that becomes read only.
   */
  public ReadOnlyModel(AnimatorModel model) {
    this.model = model;
  }

  /**
   * Allows the caller to see the final tick of this animation.
   *
   * @return The final tick of this animation.
   */
  public int getLastTick() {
    return model.getLastTick();
  }

  /**
   * Allows the caller to see the shapes to be animated on every tick.
   *
   * @return The map of ticks and shapes to be animated.
   */
  public Map<Integer, List<IShape>> getTickListShapes() {
    return Collections.unmodifiableMap(model.getTickListShapes());
  }

  /**
   * Allows the caller to see the height of this animation.
   *
   * @return The height of this animation as an integer.
   */
  public int getHeight() {
    return model.getHeight();
  }

  /**
   * Allows the caller to see the width of this animation.
   *
   * @return The width of this animation as an integer.
   */
  public int getWidth() {
    return model.getWidth();
  }

  /**
   * Allows the caller to see the x coordinate of the top left of the window that holds this
   * animation.
   *
   * @return The x coordinate of the top left of the window that holds this anmation.
   */
  public int getWindowX() {
    return model.getWindowX();
  }

  /**
   * Allows the caller to see the y coordinate of the top left of the window that holds this
   * animation.
   *
   * @return The y coordinate of the top left of the window that holds this anmation.
   */
  public int getWindowY() {
    return model.getWindowY();
  }

  /**
   * Allows the caller to see a copy of the map of motions of this animation.
   *
   * @return A copy of the map of motions of this animation.
   */
  public Map<String, List<Motion>> getMotions() {
    return Collections.unmodifiableMap(model.getMotions());
  }

  /**
   * Allows the caller to see a copy of the map of shapes of this animation.
   *
   * @return A copy of the map of shapes of this animation.
   */
  public Map<String, IShape> getShapes() {
    return Collections.unmodifiableMap(model.getShapes());
  }

  /**
   * Allows the caller to see a copy of the map of shape names and types.
   *
   * @return A copy of the map of shapes and types of this animation.
   */
  public Map<String, String> getNameType() {
    return Collections.unmodifiableMap(model.getNameType());
  }

  /**
   * Allows the caller to see a copy of the keyframes map. Converts our motions which contain a
   * start and end time into keyframe variations which have the same start and end time. These key
   * frames are used in displaying the editable keyframes in the editable view.
   *
   * @return A copy of the map shape names and the keyframes for the shape.
   */
  public Map<String, List<Motion>> getKeyFrames() {
    return Collections.unmodifiableMap(model.getKeyFrames());
  }

}
