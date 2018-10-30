package cs3500.animator.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

/**
 * This class represents a generic shape and implements all of its associated operations.
 */
public class SimpleShape implements IShape {

  private String name;
  private int x, y, width, height;
  private int sides;
  private Color color;


  /**
   * Constructs a shape.
   *
   * @param name The identifier for the shape.
   * @param sides The number of sides on the shape
   * @param x The x coordinate of the shape.
   * @param y The y coordinate of the shape.
   * @param width The width of the shape.
   * @param height The height of the shape.
   * @param color The color of the shape.
   */
  //maybe use enums to represent shape for view instead of weird name thing
  // getters and setters for shapes
  public SimpleShape(String name, int sides, int x, int y, int width, int height,
      Color color) {
    if (sides == 4) {
      this.name = "r" + name;
    } else if (sides == 1) {
      this.name = "o" + name;
    } else {
      throw new IllegalArgumentException("given input is not a simple shape (not rectangle, oval)");
    }
    this.sides = sides;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Override
  public void move(int x, int y, int totalTicks) throws IllegalArgumentException {
    if (totalTicks <= 0) {
      throw new IllegalArgumentException("invalid tick duration");
    }

    this.x += (x - this.x) / totalTicks;
    this.y += (y - this.y) / totalTicks;
  }

  @Override
  public void changeColor(Color color, int totalTicks) throws IllegalArgumentException {
    if (totalTicks <= 0) {
      throw new IllegalArgumentException("invalid tick duration");
    }
    int redDelta = (color.getRed() - this.color.getRed()) / totalTicks;
    int greenDelta = (color.getGreen() - this.color.getGreen()) / totalTicks;
    int blueDelta = (color.getBlue() - this.color.getBlue()) / totalTicks;
    this.color = new Color(this.color.getRed() + redDelta, this.color.getGreen() + greenDelta,
        this.color.getBlue() + blueDelta);
  }

  @Override
  public void changeSize(int width, int height, int totalTicks) throws IllegalArgumentException {
    if (totalTicks <= 0) {
      throw new IllegalArgumentException("invalid tick duration");
    }
    this.width += (width - this.width) / totalTicks;
    this.height += (height - this.height) / totalTicks;
  }


  @Override
  public String getName() {
    return name;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public String getShapeState(int tick) {
    StringBuilder result = new StringBuilder("");
    result.append(
        "motion " + name.substring(1, name.length()) + " " + tick + " " + x + " " + y + " " + width
            + " " + height + " " + color +
            "\n");

    return result.toString();
  }


}
