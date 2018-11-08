package cs3500.animator.model;

import java.awt.Color;
import java.awt.Shape;

/**
 * This interface specifies the operations of a shape. A shape is characterized by a name, position
 * (x,y), a size (width, height), and a color (r,g,b). The following commands modify the shape.
 */
public interface IShape {

  /**
   * Moves the shape to a given position.
   *
   * @param x the desired x coordinate of the shape
   * @param y the desired y coordinate of the shape
   */
  void move(int startX, int startY, int endX, int endY, int totalTicks, int tick, int startTime, int endTime);

  /**
   * Changes the color of this shape to a new one.
   *
   * @param color the desired r,g,b color
   * @param totalTicks the length of the animation for this shape
   */
  void changeColor(Color startColor, Color endColor, int totalTicks, int tick, int startTime, int endTime);

  /**
   * Changes the size of this shape.
   *
   * @param width the desired width of the final shape
   * @param height the desired height of the final shape
   */
  void changeSize(int startWidth, int startHeight, int endWidth, int endheight, int totalTicks, int tick, int startTime, int endTime);

  /**
   * Gets the java Shape object stored inside of this shape that represents its visual aspects.
   * @return  A Java Shape object that can be drawn.
   */
  Shape getShape();

  /**
   * Allows the caller to use the color of this IShape.
   * @return  The Color of the this IShape.
   */
  Color getColor();



}
