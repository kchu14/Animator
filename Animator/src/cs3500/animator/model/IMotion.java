package cs3500.animator.model;

import java.awt.Color;

public interface IMotion extends Comparable<IMotion> {


  /**
   * Formats the motion into a readable version that displays each of the factors of the motion.
   *
   * @return A string representing the motion.
   */
  String getTextResult();

  /**
   * Outputs the start time of this tick for use of other classes.
   *
   * @return An integer representing the starting tick for this motion
   */
  int getStartTime();

  /**
   * Outputs the type of the shape the motion is acting upon.
   *
   * @return An string denoting the type.
   */
  String getType();


  /**
   * This method converts the motions properties into an svg formatted string that represents
   * animate.
   *
   * @param speed the given speed of the animation.
   * @return the svg formatted string of the motion.
   */
  String toSVG(double speed);

  /**
   * Allows the user to see the end time of this motion.
   *
   * @return the end time of this motion.
   */
  int getEndTime();

  /**
   * Allows the user to get the name of the shape of this motion.
   *
   * @return the name of the shape.
   */

  String getName();

  /**
   * Allows the user to get the shape that the motion is acting upon.
   *
   * @return the shape being altered.
   */
  IShape getShape();


  /**
   * Mutates the IShape associated with this motion to allow it to accurately represent a shape that
   * is changing as the tick count is rising.
   *
   * @return An updated shape based on the motion that should be happening at this tick.
   */
  IShape executeMotion(int tick);

  /**
   * Fixes the ending attributes of this motion to match the starting ones of the given motion.
   *
   * @param m the given motion that this motions ending attributes will align with.
   */
  void fixEndings(IMotion m);

  /**
   * Fixes the beginnings of this motion to match the ending attributes of the given motion.
   *
   * @param m the given motion for this motion to align with.
   */
  void fixBeginning(IMotion m);

  /**
   * Converts a motion into a keyframe.
   *
   * @param isEndKeyFrame true if this motion is becoming a keyframe based on this motions ending
   *        time.
   */
  void becomesKeyframe(boolean isEndKeyFrame);

  /**
   * Fixes this motions endings to align with the given motions endings. Used when the last motion
   * on a shape is deleted.
   *
   * @param m the given motion to be aligned with.
   */
  void fixLastEndings(IMotion m);

  /**
   * Gets the starting x value of the shape.
   *
   * @return The x location
   */
  int getStartX();


  /**
   * Gets the starting y value of the shape.
   *
   * @return The y location
   */
  int getStartY();

  /**
   * Gets the starting width value of the shape.
   *
   * @return The starting width
   */
  int getStartWidth();

  /**
   * Gets the starting height value of the shape.
   *
   * @return The starting height
   */
  int getStartHeight();

  /**
   * Gets the starting Color value of the shape.
   *
   * @return The starting Color
   */
  Color getStartColor();

  /**
   * Gets the ending X value of the shape.
   *
   * @return The ending X location
   */
  int getEndX();

  /**
   * Gets the ending Y value of the shape.
   *
   * @return The ending Y location
   */
  int getEndY();

  /**
   * Gets the ending Width value of the shape.
   *
   * @return The Width Color
   */
  int getEndWidth();

  /**
   * Gets the ending Height value of the shape.
   *
   * @return The ending Height
   */
  int getEndHeight();

  /**
   * Gets the ending Color value of the shape.
   *
   * @return The ending Color
   */
  Color getEndColor();
}
