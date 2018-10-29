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
  public void move(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public void changeColor(Color color) {
    this.color = color;
  }

  @Override
  public void changeSize(int hShift, int vShift) {
    this.width *= hShift;
    this.height *= vShift;
  }

//  @Override
//  public void rotate(int angleDegrees) {
//
//  }

  @Override
  public void delete() {
    this.x = -1000;
    this.y = -1000;
  }


}
