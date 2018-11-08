//package cs3500.animator.model;
//
//import java.awt.Color;
//import java.awt.Shape;
//
///**
// * This class represents ComplexShape (n-gon, polygons) and implements all of the associated shape
// * operations.
// */
//public class ComplexShape implements IShape {
//
//  private String name;
//  private int[] x;
//  private int[] y;
//  private int sides;
//  private Color color;
//
//
//  /**
//   * Constructs a complex shape out of two arrays of integers representing vertices.
//   *
//   * @param name The identifier for the shape.
//   * @param x The array of x coordinates of the shape.
//   * @param y The array of y coordinates of the shape.
//   * @param color The color of the shape.
//   */
//
//  public ComplexShape(String name, int[] x, int[] y, Color color) {
//    this.name = "p" + name;
//    this.x = x;
//    this.y = y;
//    this.color = color;
//    this.sides = x.length;
//  }
//
//
//  @Override
//  public void move(int startX, int startY, int endX, int endY, int totalTicks, int tick,
//      int startTime, int endTime) {
//    if (totalTicks <= 0) {
//      throw new IllegalArgumentException("invalid tick duration");
//    }
////    int xdif = (x - this.x[0]) / totalTicks;
////    int ydif = (y - this.y[0]) / totalTicks;
////    for (int i = 0; i < sides; i++) {
////      this.x[i] += xdif;
////      this.y[i] += ydif;
////    }
//  }
//
//  @Override
//  public void changeColor(Color color, int totalTicks) throws IllegalArgumentException {
//    if (totalTicks <= 0) {
//      throw new IllegalArgumentException("invalid tick duration");
//    }
//    int redDelta = (color.getRed() - this.color.getRed()) / totalTicks;
//    int greenDelta = (color.getGreen() - this.color.getGreen()) / totalTicks;
//    int blueDelta = (color.getBlue() - this.color.getBlue()) / totalTicks;
//    this.color = new Color(this.color.getRed() + redDelta, this.color.getGreen() + greenDelta,
//        this.color.getBlue() + blueDelta);
//  }
//
//  // this cannot be implemented until we know how polygons should be implemented.
//  // unsure of how to designate the width and height of a complex shape (n-gon, octagon etc.).
//  @Override
//  public void changeSize(int width, int height, int totalTicks) throws IllegalArgumentException {
//    if (totalTicks <= 0) {
//      throw new IllegalArgumentException("invalid tick duration");
//    }
//    for (int i = 0; i < sides; i++) {
//      this.x[i] *= width;
//      this.y[i] *= height;
//    }
//  }
//
//  @Override
//  public Shape getShape() {
//    return null;
//  }
//
//  @Override
//  public Color getColor() {
//    return null;
//  }
//
////  @Override
////  public String getName() {
////    return name;
////  }
////
////  @Override
////  public Color getColor() {
////    return color;
////  }
////
////  @Override
////  public int getX() {
////    // need more information about animating complex polygons before implementing.
////    // currently implemented to only return one coordinate of the polygon
////    return x[0];
////  }
////
////  @Override
////  public int getY() {
////    // need more information about animating complex polygons before implementing.
////    // currently implemented to only return one coordinate of the polygon
////    return y[0];
////  }
////
////  @Override
////  public int getWidth() {
////    // need more information about animating complex polygons before implementing.
////    // unsure of what the definition of width will be in a complex polygon
////    return 0;
////  }
////
////  @Override
////  public int getHeight() {
////    // need more information about animating complex polygons before implementing.
////    // unsure of what the definition of height will be in a complex polygon
////    return 0;
////  }
//
//
//
//}
