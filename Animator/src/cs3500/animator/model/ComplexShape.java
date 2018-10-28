package cs3500.animator.model;

import java.awt.Color;

public class ComplexShape implements IShape {
  String name;
  int[] x, y;
  int sides;
  Color color;
  /**
   * Constructs a complex shape out of two arrays of integers representing vertices.
   *
   * @param name The identifier for the shape.
   * @param x The array of x coordinates of the shape.
   * @param y The array of y coordinates of the shape.
   * @param color The color of the shape.
   */
  //maybe use enums to represent shape for view instead of weird name thing
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
}
