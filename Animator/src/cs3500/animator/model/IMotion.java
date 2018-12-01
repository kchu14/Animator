package cs3500.animator.model;

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
   * Allows the user to get the name of the shape of this motion
   * @return the name of the shape.
   */

  String getName();

  /**
   * Allows the user to get the shape that the motion is acting upon.
   * @return the shape being altered.
   */
  IShape getShape();

}
