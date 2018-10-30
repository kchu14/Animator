package cs3500.animator.model;

import java.awt.Color;

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
  void move(int x, int y, int totalTicks) throws IllegalArgumentException;

  /**
   * Changes the color of this shape to a new one.
   *
   * @param color the desired r,g,b color
   * @param totalTicks the length of the animation for this shape
   */
  void changeColor(Color color, int totalTicks) throws IllegalArgumentException;

  /**
   * Changes the size of this shape.
   *
   * @param width the desired width of the final shape
   * @param height the desired height of the final shape
   */
  void changeSize(int width, int height, int totalTicks) throws IllegalArgumentException;


  /**
   * Returns the shape's name.
   *
   * @return the shape's name.
   */
  String getName();

  Color getColor();

  int getX();
  int getY();

  int getWidth();

  int getHeight();
}
