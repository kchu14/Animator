package cs3500.animator.model;

import java.awt.Color;

public class Motion implements Comparable<Motion>{

  protected String name;
  protected String type;
  protected int startTime;
  protected int startX;
  protected int startY;
  protected int startWidth;
  protected int startHeight;
  protected Color startColor;
  protected int endTime;
  protected int endX;
  protected int endY;
  protected int endWidth;
  protected int endHeight;
  protected Color endColor;
  protected IShape shape;

  Motion(String name, String type, int startTime, int startX, int startY,
      int startWidth,
      int startHeight, Color startColor, int endTime, int endX, int endY, int endWidth,
      int endHeight, Color endColor) {
    this.name = name;
    this.type = type;
    this.startTime = startTime;
    this.startX = startX;
    this.startY = startY;
    this.startWidth = startWidth;
    this.startHeight = startHeight;
    this.startColor = startColor;
    this.endTime = endTime;
    this.endX = endX;
    this.endY = endY;
    this.endWidth = endWidth;
    this.endHeight = endHeight;
    this.endColor = endColor;
    this.shape = new SimpleShape(name, type, startX, startY, startWidth, startHeight, startColor);
  }

  // execute the motion on a shape
  // create a hashmap of ticks and shape objects

  String getTextResult() {
    return String.format(
             "motion %s %d %d %d %d %d %d %d %d "
            + "%d %d %d %d %d %d %d %d \n",
        name, startTime, startX, startY, startWidth, startHeight, startColor.getRed(),
        startColor.getBlue(), startColor.getGreen(),
        endTime, endX, endY, endWidth, endHeight, endColor.getRed(),
        endColor.getBlue(), endColor.getGreen());
  }

  @Override
  public int compareTo(Motion m) {
      return this.startTime - m.startTime;
  }

  public IShape executeMotion() {
    int totalTicks = endTime - startTime;
    shape.changeColor(endColor, totalTicks);
    shape.changeSize(endWidth,endHeight, totalTicks);
    shape.move(endX, endY, totalTicks);
    return shape;

  }

}
