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

  public String toSVG(double speed) {
    String result = "";
    String thisScaleType1 = "";
    String thisScaleType2 = "";
    String thisPosType1 = "";
    String thisPosType2 = "";
    if (type.equals("rectangle")) {
      thisScaleType1 = "width";
      thisScaleType2 = "height";
      thisPosType1 = "x";
      thisPosType2 = "y";
    }
    else {
      thisScaleType1 = "rx";
      thisScaleType2 = "ry";
      thisPosType1 = "cx";
      thisPosType2 = "cy";
    }

    if(startX - endX != 0 || startY - endY != 0) {
      result = "<animate attributeType=\"xml\" begin=\""
          + Double.toString((startTime) * (1000 / speed)) + "ms\" dur=\""
          + Double.valueOf((endTime - startTime) * (1000 / speed))
          + "ms\" attributeName=\"" + thisPosType1 + "\"" + " from=\"" + Double.toString(this.startX)
          + "\" to=\"" + Double.toString(this.endX) + "\" fill=\"freeze\"/>\n"
          + "<animate attributeType=\"xml\" begin=\""
          + Double.toString(startTime * (1000 / speed)) + "ms\" dur=\""
          + Double.toString((endTime - startTime) * (1000 / speed))
          + "ms\" attributeName=\"" + thisPosType2 + "\"" + " from=\""
          + Double.toString(this.startY) + "\" to=\"" + Double.toString(this.endY)
          + "\" fill=\"freeze\" />\n\n";
    }

    if(startWidth - endWidth != 0 || startHeight - endHeight != 0) {
      result += "<animate attributeType=\"xml\" begin=\""
          + Double.toString(startTime * (1000 / speed)) + "ms\" dur=\""
          + Double.toString((endTime - startTime) * (1000 / speed))
          + "\" attributeName=\"" + thisScaleType1 + "\"" + " from=\"" + Double
          .toString(this.startWidth)
          + "\" to=\"" + Float.toString(this.endWidth) + "\" fill=\"freeze\"/>\n"
          + "<animate attributeType=\"xml\" begin=\""
          + Double.toString(startTime * (1000 / speed)) + "ms\" dur=\""
          + Double.toString((endTime - startTime) * (1000 / speed))
          + "ms\" attributeName=\"" + thisScaleType2 + "\"" + " from=\"" + Double
          .toString(this.startHeight)
          + "\" to=\"" + Double.toString(this.endHeight) + "\" fill=\"freeze\"/>\n";
    }

    if(startColor.getRGB() != endColor.getRGB()) {
      result += "<animate attributeType=\"xml\" begin=\""
          + Double.toString(startTime * (1000 / speed)) + "ms\" dur=\""
          + Double.valueOf((endTime - startTime) * (1000 / speed))
          + "ms\" attributeName=\"fill\"" + " from=\"" + "rgb("
          + Integer.toString(this.startColor.getRed()) + ", "
          + Integer.toString(this.startColor.getGreen()) + ", "
          + Integer.toString(this.startColor.getBlue()) + ")\""
          + " to=\"" + "rgb(" + Integer.toString(this.endColor.getRed()) + ", "
          + Integer.toString(this.endColor.getGreen()) + ", "
          + Integer.toString(this.endColor.getBlue()) + ")\"/>\n\n";
    }
    return result;



  }
}
