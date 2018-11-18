package cs3500.animator.model;

import java.awt.Color;
import java.awt.Shape;

/**
 * This interface specifies the operations of a shape. A shape is characterized by a name, position
 * (x,y), a size (width, height), and a color (r,g,b). The following commands modify the shape.
 */
public interface IShape {

  /**
   * This method moves the shape and does the tweening aspect.
   *
   * @param startX the starting x position of the shape
   * @param startY the starting y position of the shape
   * @param endX the ending x position of the shape
   * @param endY the ending y position of the shape
   * @param totalTicks the total duration of the motion
   * @param tick the current tick of the motion
   * @param startTime the start time of the motion
   * @param endTime the end time of the motion
   */
  void move(int startX, int startY, int endX, int endY, int totalTicks, int tick, int startTime,
      int endTime);

  /**
   * This method changes the color of the shape and does the tweening aspect.
   *
   * @param startColor the starting color of the shape
   * @param totalTicks the total duration of the motion
   * @param tick the current tick of the motion
   * @param startTime the start time of the motion
   * @param endTime the end time of the motion
   */
  void changeColor(Color startColor, Color endColor, int totalTicks, int tick, int startTime,
      int endTime);

  /**
   * This method changes the color of the shape and does the tweening aspect.
   *
   * @param startWidth the starting width of the shape
   * @param startHeight the starting height of the shape
   * @param totalTicks the total duration of the motion
   * @param tick the current tick of the motion
   * @param startTime the start time of the motion
   * @param endTime the end time of the motion
   */
  void changeSize(int startWidth, int startHeight, int endWidth, int endheight, int totalTicks,
      int tick, int startTime, int endTime);

  /**
   * Gets the java Shape object stored inside of this shape that represents its visual aspects.
   *
   * @return A Java Shape object that can be drawn.
   */
  Shape getShape();

  /**
   * Allows the caller to use the color of this IShape.
   *
   * @return The Color of the this IShape.
   */
  Color getColor();


  /**
   * Converts the shapes properties into a string formatted in the svg style.
   *
   * @return string formatted to fit svg parameters.
   */
  String toSVG();
  String getName();
  String getType();

}
