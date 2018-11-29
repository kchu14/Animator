package cs3500.animator.provider.model;

import java.awt.Color;
import java.awt.Point;

//CHANGES FROM HOMEWORK 5: Added a public method remake which constructs a new shape of the same
//type given new values, and raised adjust to

public interface Shape {

  /**
   * Generates a Two-Dimensional Array of colors, representing a pixel-by-pixel visual
   * representation of the shape on a canvas.
   *
   * @param frameWidth the width of the canvas in which the shape is being drawn
   * @param frameHeight the height of the canvas in which the shape is being drawn
   * @return a representation of this shape on a canvas of the given parameters
   */
  public Color[][] getVisualRepresentation(int frameWidth, int frameHeight);


  /**
   * Generates a verbose textual description of the shape in the format "Shape_type x_location
   * y_location width height red green blue" where red/green/blue are the RGB components of the
   * shape's color.
   *
   * @return a String containing such a description
   */
  public String getTextRepresentation();


  /**
   * Constructs a new shape with all the same values as this shape.
   *
   * @return a shape with all the same values, but not an alias of This shape
   */
  public Shape copy();

  /**
   * Constructs a new shape with the values of each given argument. This is largely a way to easily
   * construct new shapes of the same type without knowing their type using double-dispatch
   *
   * @param newWidth the value of the minimum-drawing width of this shape
   * @param newHeight the value of the minimum-drawing height of this shape
   * @param newX the x-component of the anchor of this point
   * @param newY the y-component of the anchor of this point
   * @param red the red value of the color of this shape
   * @param green the red value of the color of this shape
   * @param blue the red value of the color of this shape
   * @return a shape the same type with the new values
   */
  public Shape remake(int newWidth, int newHeight, int newX, int newY, int red, int green,
      int blue);

  /**
   * Constructs a new shape with the values of this shape adjusted by each given argument.
   *
   * @param deltaWidth the amount to adjust the width of the shape
   * @param deltaHeight the amount to adjust the height of the shape
   * @param deltaX the amount to adjust the x-component of the anchor point
   * @param deltaY the amount to adjust the y-component of the anchor point
   * @param red the amount to adjust the red value of the color of this shape
   * @param green the amount to adjust the green value of the color of this shape
   * @param blue the amount to adjust the blue value of the color of this shape
   * @return a shape with the adjusted values
   */
  public Shape adjust(int deltaWidth, int deltaHeight, int deltaX, int deltaY, int red, int green,
      int blue);

  /**
   * Gets the values of the anchor point of this shape.
   *
   * @return the a new Point at the same location as the anchor point of this shape
   */
  public Point getLocation();

  /**
   * Gets the value of the color of this shape.
   *
   * @return the a new Color with the same values as the color of this shape
   */
  public Color getColor();

  /**
   * Gets the value of the width of this shape.
   *
   * @return the value of this.width
   */
  public int getWidth();

  /**
   * Gets the value of the height of this shape.
   *
   * @return the value of this.height
   */
  public int getHeight();
}

