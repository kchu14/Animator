package cs3500.animator.provider.model;

import java.awt.Color;
import java.util.List;

//CHANGES FROM HOMEWORK 6: Added methods to modify existing keyframes

/**
 * Represents a motion of a Shape: the change in its values over time.
 */
public interface Animation {

  /**
   * Generates a 2-Dimensional Array of colors, representing a pixel-by-pixel visual
   * representation of the frame of the animation at the given tick.
   *
   * @param n the tick at which the frame is being pulled
   * @param width the width of the canvas on which the frame would be drawn
   * @param height the height of the canvas on which the frame would be drawn
   * @return a visual representation of this frame at the given tick
   */
  public Color[][] getVisualFrame(int n, int width, int height);

  /**
   * Generates a verbose textual description of the frame of this animation at the given tick,
   * including such details as the size, position, and color of the shape this animation is on.
   *
   * @param n the tick at which the frame is being pulled
   * @return a String containing such a description
   */
  public String getTextFrame(int n);

  /**
   * Generates the Shape at the given tick of this animation.
   *
   * @param n the tick at which the frame is being pulled
   * @return a copy of the Shape generated by the animation at the given tick
   */
  public Shape getState(int n, int startN);

  /**
   * Gets the value of the length of this animation in ticks.
   *
   * @return the length of this animation
   */
  public int getLength();

  /**
   * Gets the shape this animation is acting on.
   *
   * @return the value of the original shape this animation is modifying
   */
  public ShapeTuple getOriginalShape();

  /**
   * Gets the shape as a result of this animation.
   *
   * @return the value of the what the shape should look like at the end of the animation
   */
  public ShapeTuple getFinalShape();

  /**
   * Gets the keyframes of this animation.
   *
   * @return a list of all keyframes within this motion
   */
  public List<Keyframe> getFrames();

  /**
   * Removes the given keyframe from the current motion.
   *
   * @param keyframe the keyframe to be deleted from the motion
   */
  public void deleteFrame(Keyframe keyframe);

  /**
   * Adds a keyframe with the given parameters to this motion.
   *
   * @param tick the absolute tick at which the keyframe is being added
   * @param index the index of the
   * @param startN the local tick at which the keyframe is being added
   * @param name the name of the shape to which the keyframe is being added
   * @return the keyframe with values equal to the given parameters
   */
  public Keyframe addFrame(int tick, int index, int startN, ShapeTuple name);

  /**
   * Updates the keyframe at the given tick (in relative time) with the given ShapeTuple.
   *
   * @param shape the shape being modified in this motion
   * @param tick the tick at which the keyframe occurs
   */
  public void updateKeyframe(ShapeTuple shape, int tick);
}
