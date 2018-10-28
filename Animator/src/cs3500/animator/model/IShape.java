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
  void move(int x, int y);

  /**
   * Changes the color of this shape to a new one.
   * @param color the desired r,g,b color
   */
  void changeColor(Color color);

  /**
   * Changes the size of this shape.
   * @param hShift the desired amount of horizontal shift
   * @param vShift the desired amount of vertical shift
   */
  void changeSize(int hShift, int vShift);

//  /**
//   * Rotates this shape by the specified degrees.
//   * @param angleDegrees the desired amount of angular shift
//   */
//  void rotate(double angleDegrees);

  /**
   * Deletes this shape.
   */
  void delete();
}
