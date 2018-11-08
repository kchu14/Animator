package cs3500.animator.model;

import java.awt.Color;

/**
 * This class represents a motion object. A motion is defined as a set of two key frames that a
 * user may input into the animation to cause movement from the first key frame to the next.
 */
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

  /**
   * Formats the motion into a readable version that displays each of the factors of the motion.
   * @return  A string representing the motion.
   */
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

  /**
   * Mutates the IShape associated with this motion to allow it to accurately represent a shape
   * that is changing as the tick count is rising.
   * @return An updated shape based on the motion that should be happening at this tick.
   */
  public IShape executeMotion(int tick) {
    int totalTicks = endTime - startTime;
    shape.changeColor(startColor, endColor, totalTicks, tick, startTime, endTime);
    shape.changeSize(startWidth, startHeight, endWidth,endHeight, totalTicks, tick, startTime, endTime);
    shape.move(startX, startY, endX, endY, totalTicks, tick, startTime, endTime);
    return shape;

  }

}
