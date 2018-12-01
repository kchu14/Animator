package cs3500.animator.provider.model;

import java.awt.Color;
import java.awt.Point;

public class Ellipse implements Shape {
  private int horizontalRadius;
  private int verticalRadius;
  private Point anchor;
  private Color color;

  /**
   * Constructs a rectangle given anchor point, width, height, and color.
   *
   * @param horizontalRadius int horizontalRadius
   * @param verticalRadius int verticalRadius
   * @param anchor anchor point
   * @param color color of shape
   */
  public Ellipse(int horizontalRadius, int verticalRadius, Point anchor, Color color) {
    this.horizontalRadius = horizontalRadius;
    this.verticalRadius = verticalRadius;
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
    return horizontalRadius;
  }

  @Override
  public int getHeight() {
    return verticalRadius;
  }
}
