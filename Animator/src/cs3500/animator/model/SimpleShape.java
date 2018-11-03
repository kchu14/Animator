package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;

/**
 * This class represents a generic shape and implements all of its associated operations.
 */
public class SimpleShape implements IShape {

  protected String name;
  protected String type;
  protected int startTime;
  protected int startX;
  protected int startY;
  protected int startWidth;
  protected int startHeight;
  protected Color startColor;
  protected int endTime;
  protected int endX;
  protected int endY;
  protected int endWidth;
  protected int endHeight;
  protected Color endColor;

  public SimpleShape(String name, String type, int startTime, int startX, int startY,
      int startWidth,
      int startHeight, Color startColor, int endTime, int endX, int endY, int endWidth,
      int endHeight, Color endColor) {
    this.name = name;
    this.type = type;
    this.startTime = startTime;
    this.startX = startX;
    this.startY = startY;
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.startColor = startColor;
    this.endTime = endTime;
    this.endX = endX;
    this.endY = endY;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.endColor = endColor;
  }

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
  public String getShapeState(int tick) {
    StringBuilder result = new StringBuilder("");
    result.append(
        "motion " + name.substring(1, name.length()) + " " + tick + " " + x + " " + y + " " + width
            + " " + height + " " + color.getRed() + " " + color.getBlue() + " " + color.getGreen()
            + " " +
            "\n");

    return result.toString();
  }


}
