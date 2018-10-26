package cs3500.animator.model;

import java.awt.Color;

/**
 * This class represents a generic shape and implements all of its associated operations.
 */
public class AShape implements IShape {
  String name;
  double x, y, width, height;
  int sides;
  Color color;


  /**
   * Constructs a shape.
   * @param name  The identifier for the shape.
   * @param sides The number of sides on the shape
   * @param x The x coordinate of the shape.
   * @param y The y coordinate of the shape.
   * @param width The width of the shape.
   * @param height  The height of the shape.
   * @param color   The color of the shape.
   */
  AShape(String name, int sides, double x, double y, double width, double height, Color color) {
    this.name = name;
    this.sides = sides;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  @Override
  public void move(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void changeColor(Color color) {
    this.color = color;
  }

  @Override
  public void changeSize(double hShift, double vShift) {
    this.width *= hShift;
    this.height *= vShift;
  }

  @Override
  public void rotate(double angleDegrees) {

}

  @Override
  public void delete() {
    this.x = -1000;
    this.y = -1000;
  }
}
