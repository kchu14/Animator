package cs3500.animator.model;

import java.awt.Color;

public class ComplexShape implements IShape {
  private String name;
  private int[] x, y;
  private int sides;
  private Color color;



  /**
   * Constructs a complex shape out of two arrays of integers representing vertices.
   *
   * @param name The identifier for the shape.
   * @param x The array of x coordinates of the shape.
   * @param y The array of y coordinates of the shape.
   * @param color The color of the shape.
   */
  //maybe use enums to represent shape for view instead of weird name thing
  // getters and setters for shapes
  public ComplexShape(String name, int[] x, int[] y, Color color) {
    this.name = "polygon" + name;
    this.x = x;
    this.y = y;
    this.color = color;
    this.sides = x.length;
  }

  @Override
  public void move(int x, int y) {
    int xdif = this.x[0] - x;
    int ydif = this.y[0] - y;
    for(int i = 0; i < sides; i++) {
      this.x[i] += xdif;
      this.y[i] += ydif;
    }
  }

  @Override
  public void changeColor(Color color) {
    this.color = color;
  }

  @Override
  public void changeSize(int hShift, int vShift) {
    for(int i = 0; i < sides; i++) {
      this.x[i] *= hShift;
      this.y[i] *= vShift;
    }
  }

  @Override
  public void delete() {
    this.x = new int[]{};
    this.y = new int[]{};
  }
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getX() {
    return x;
  }

  public void setX(int[] x) {
    this.x = x;
  }

  public int[] getY() {
    return y;
  }

  public void setY(int[] y) {
    this.y = y;
  }

  public int getSides() {
    return sides;
  }

  public void setSides(int sides) {
    this.sides = sides;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
