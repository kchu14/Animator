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
  public void move(int x, int y, int totalTicks) {
    int xdif = (this.x[0] - x) / totalTicks;
    int ydif = (this.y[0] - y) / totalTicks;
    for (int i = 0; i < sides; i++) {
      this.x[i] += xdif;
      this.y[i] += ydif;
    }
  }

  @Override
  public void changeColor(Color color, int totalTicks) {
    int redDelta = (color.getRed() - this.color.getRed()) / totalTicks;
    int greenDelta = (color.getGreen() - this.color.getGreen()) / totalTicks;
    int blueDelta = (color.getBlue() - this.color.getBlue()) / totalTicks;
    this.color = new Color(this.color.getRed() + redDelta, this.color.getGreen() + greenDelta,
        this.color.getBlue() + blueDelta);
  }

  // this cannot be implemented until we know how polygons should be implemented.
  // unsure of how to designate the width and height of a complex shape (n-gon, octagon etc.).
  @Override
  public void changeSize(int hShift, int vShift, int totalTicks) {
    for (int i = 0; i < sides; i++) {
      this.x[i] *= hShift;
      this.y[i] *= vShift;
    }
  }

  @Override
  public String getName() {
    return name;
  }




}
