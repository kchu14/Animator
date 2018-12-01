package cs3500.animator.provider.model;

import cs3500.animator.model.adapters.ShapeAdapter;
import java.awt.Color;
import java.awt.Point;

public class Rectangle implements Shape {
  private int width;
  private int height;
  private Point anchor;
  private Color color;

  /**
   * Constructs a rectangle given anchor point, width, height, and color.
   *
   * @param width int width
   * @param height int height
   * @param anchor anchor point
   * @param color color of shape
   */
  public Rectangle(int width, int height, Point anchor, Color color) {
     this.width = width;
     this.height = height;
     this.anchor = anchor;
     this.color = color;
  }

  @Override
  public Color[][] getVisualRepresentation(int frameWidth, int frameHeight) {
    throw new UnsupportedOperationException("Non necessary operation");
  }

  @Override
  public String getTextRepresentation() {
    throw new UnsupportedOperationException("Non necessary operation");
  }

  @Override
  public Shape copy() {
    throw new UnsupportedOperationException("Non necessary operation");
  }

  @Override
  public Shape remake(int newWidth, int newHeight, int newX, int newY, int red, int green,
      int blue) {
    throw new UnsupportedOperationException("Non necessary operation");
  }

  @Override
  public Shape adjust(int deltaWidth, int deltaHeight, int deltaX, int deltaY, int red, int green,
      int blue) {
    throw new UnsupportedOperationException("Non necessary operation");
  }

  @Override
  public Point getLocation() {
    return anchor;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }
}
