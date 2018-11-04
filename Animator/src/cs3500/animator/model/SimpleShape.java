package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;

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

  public SimpleShape(String name, String type, int x, int y, int width, int height,
      Color color) {
    this.name = name;
    this.type = type;
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




}
