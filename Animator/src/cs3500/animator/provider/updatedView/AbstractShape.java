package cs3500.animator.provider.updatedView;

import java.awt.Point;
import java.awt.Color;

//CHANGES FROM HOMEWORK 5: Raised adjust() since it can be written in terms of remake()

/**
 * Represents a top-level implementation of the shape interface, with each shape being one-color and
 * having an anchor point at its top left (represented by the location field). Additionally, each
 * shape has a width and height corresponding to the minimal rectangular space in which they can be
 * drawn to provide a standard system for the textual description.
 */
public abstract class AbstractShape implements Shape {

  protected Point location;
  protected Color color;
  protected int width;
  protected int height;

  /**
   * Generates the location, dimensions, and color as a string of format "x_location y_location
   * width height red green blue" where red/green/blue are the RGB components of the shape's color.
   *
   * @return a String containing this information
   */
  protected String generateShapeInformation() {
    return location.x + " " + location.y + " " + width + " " + height + " " + color.getRed() + " "
        + color.getGreen() + " " + color.getBlue();
  }

  /**
   * Get location of shape.
   *
   * @return point location
   */
  @Override
  public Point getLocation() {
    return new Point(this.location.x, this.location.y);
  }

  /**
   * Get color of shape.
   *
   * @return color of shape
   */
  @Override
  public Color getColor() {
    return new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public Shape adjust(int deltaWidth, int deltaHeight, int deltaX, int deltaY, int red, int green,
      int blue) {
    //System.out.println("colors");
    //System.out.println(red);
    //System.out.println(green);
    //System.out.println(blue);
    if (this.color.getRed() + red > 255 || this.color.getRed() + red < 0
        || this.color.getGreen() + green > 255 || this.color.getGreen() + green < 0
        || this.color.getBlue() + blue > 255 || this.color.getBlue() + blue < 0) {
      throw new IllegalArgumentException("Given color components would cause existing color to go "
          + "out of the range [0, 255]");
    }
    //System.out.println("total" + this.width + deltaWidth);
    //System.out.println("this.width " + this.width);
    //System.out.println("deltaWidth" + deltaWidth);
    return this.remake(this.width + deltaWidth, this.height + deltaHeight, this.location.x + deltaX,
        this.location.y + deltaY, this.color.getRed() + red, this.color.getGreen() + green,
        this.color.getBlue() + blue);
  }

  /**
   * Get min drawing width of shape.
   *
   * @return int min drawing width
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get min drawing height of shape.
   *
   * @return int min drawing height
   */
  @Override
  public int getHeight() {
    return this.height;
  }
}
