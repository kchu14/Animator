package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * This class represents a generic shape and implements all of its associated operations.
 */
public class SimpleShape implements IShape {

  protected String name;
  protected String type;
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected Color color;
  protected Shape shape;

  public SimpleShape(String name, String type, int x, int y, int width, int height,
      Color color) {
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    getCurShape();

  }

  public void getCurShape() {
    if (type.equals("rectangle")) {
      this.shape = new Rectangle(x, y, width, height);
    }
    if (type.equals("ellipse")) {
      this.shape = new Ellipse2D.Double(x, y, width, height);
    }
  }

  @Override
  public void move(int x, int y, int totalTicks) throws IllegalArgumentException {
    if (totalTicks <= 0) {
      throw new IllegalArgumentException("invalid tick duration");
    }

    this.x += (x - this.x) / totalTicks;
    this.y += (y - this.y) / totalTicks;
    getCurShape();
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
    getCurShape();
  }

  @Override
  public void changeSize(int width, int height, int totalTicks) throws IllegalArgumentException {
    if (totalTicks <= 0) {
      throw new IllegalArgumentException("invalid tick duration");
    }
    this.width += (width - this.width) / totalTicks;
    this.height += (height - this.height) / totalTicks;
    getCurShape();
  }

  @Override
  public Shape getShape() {
    return shape;
  }

  @Override
  public Color getColor() {
    return this.color;
  }


}
