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

    this.sides = sides;
    if(sides == 4) {
      this.name = "rectangle" + name;
    }
    if(sides == 1) {
      this.name = "oval" + name;
    }
    else {
      throw new IllegalArgumentException("given input is not a simple shape (not rectangle, oval)");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Override
  public void move(int x, int y, int totalTicks) {
    this.x += (this.x - x) / totalTicks;
    this.y += (this.y - y) / totalTicks;
  }

  @Override
  public void changeColor(Color color, int totalTicks) {
    int redDelta = (color.getRed() - this.color.getRed()) / totalTicks;
    int greenDelta = (color.getGreen() - this.color.getGreen()) / totalTicks;
    int blueDelta = (color.getBlue() - this.color.getBlue()) / totalTicks;
    this.color = new Color(this.color.getRed() + redDelta, this.color.getGreen() + greenDelta,
        this.color.getBlue() + blueDelta);
  }

  @Override
  public void changeSize(int hDelta, int vDelta, int totalTicks) {
    this.width += (this.width - width) / totalTicks;
    this.height += (this.height - height) / totalTicks;
  }




  @Override
  public String getName() {
    return name;
  }


}
