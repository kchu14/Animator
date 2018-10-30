package cs3500.animator.model;

import java.awt.Color;

/**
 * This interface specifies the operations of a shape.
 * A shape is characterized by a name, position (x,y), a size (width, height), and a color (r,g,b).
 * The following commands modify the shape.
 */
public interface IShape {

  /**
   * Moves the shape to a given position.
   * @param x the desired x coordinate of the shape
   * @param y the desired y coordinate of the shape
   */
  void move(int x, int y, int totalTicks);

  /**
   * Changes the color of this shape to a new one.
   * @param color the desired r,g,b color
   * @param totalTicks the length of the animation for this shape
   */
  void changeColor(Color color, int totalTicks);

  /**
   * Changes the size of this shape.
   * @param hDelta the desired amount of horizontal shift
   * @param vDelta the desired amount of vertical shift
   */
  void changeSize(int hDelta, int vDelta, int totalTicks);


  /**
   * Returns the shape's name.
   * @return the shape's name.
   */
  String getName();
}
