package cs3500.animator.model.adapters;

import cs3500.animator.model.SimpleShape;
import cs3500.animator.provider.model.Shape;
import java.awt.Color;
import java.awt.Point;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

/**
 * Represents an adapter to allow our SimpleShape implementation to implement the methods from
 * Shape interface.
 */
public class ShapeAdapter extends SimpleShape implements Shape {

  /**
   * Constructs a ShapeAdapter given the fields of a SimpleShape.
   *
   * @param name the given name
   * @param type the given type (rectangle, ellipse)
   * @param x the given x coord
   * @param y the given y coord
   * @param width the given width
   * @param height the given height
   * @param color the given color
   */
  public ShapeAdapter(String name, String type, double x, double y, double width, double height,
      Color color) {
    super(name, type, x, y, width, height, color);
    this.getCurShape();
  }

  /**
   * Constructs a ShapeAdapter given an IShape.
   *
   * @param shape the given shape.
   */
  public ShapeAdapter(SimpleShape shape) {
    super(shape);
  }

  @Override
  public Color[][] getVisualRepresentation(int frameWidth, int frameHeight) {
    throw new UnsupportedOperationException("This is never used in the provided views.");
  }

  @Override
  public String getTextRepresentation() {
    return this.getType();
  }

  @Override
  public Shape copy() {
    throw new UnsupportedOperationException("This is never used in the provided views.");
  }

  @Override
  public Shape remake(int newWidth, int newHeight, int newX, int newY, int red, int green,
      int blue) {
    return new ShapeAdapter(this.name, this.type, newWidth, newHeight, newX, newY,
        new Color(red, green, blue));
  }

  @Override
  public Shape adjust(int deltaWidth, int deltaHeight, int deltaX, int deltaY, int red, int green,
      int blue) {
    throw new UnsupportedOperationException("This is never used in the provided views");
  }

  @Override
  public Point getLocation() {
    return new Point((int) this.x, (int) this.y);
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int getWidth() {
    return (int) this.width;
  }

  @Override
  public int getHeight() {
    return (int) this.height;
  }
}
