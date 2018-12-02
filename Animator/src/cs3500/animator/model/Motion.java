package cs3500.animator.model;

import java.awt.Color;
/**
 * CHANGE LOG Added several methods that mutate the motion so that's endings /  beginnings match the
 * given motion. We did this because we wanted the flexibility of keyframes while continuing to use
 * our motion implementation.
 */

/**
 * This class represents a motion object. A motion is defined as a set of two key frames that a user
 * may input into the animation to cause movement from the first key frame to the next.
 */
public class Motion implements IMotion {

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


  /**
   * Constructs a motion given the following parameters.
   *
   * @param name the name of the shape the motion is acting on
   * @param type the type of the shape the motion is acting on
   * @param startTime the start time of the motion
   * @param startX the starting x position of the shape
   * @param startY the starting y position of the shape
   * @param startWidth the starting width of the shape
   * @param startHeight the starting height of the shape
   * @param startColor the starting color of the shape
   * @param endTime the end time of the motion
   * @param endX the ending x position of the shape
   * @param endY the ending y position of the shape
   * @param endWidth the ending width of the shape
   * @param endHeight the ending height of the shape
   * @param endColor the ending color of the shape
   */
  public Motion(String name, String type, int startTime, int startX, int startY,
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

  @Override
  public int getStartX() {
    return startX;
  }

  @Override
  public int getStartY() {
    return startY;
  }

  @Override
  public int getStartWidth() {
    return startWidth;
  }

  @Override
  public int getStartHeight() {
    return startHeight;
  }

  @Override
  public Color getStartColor() {
    return startColor;
  }

  @Override
  public int getEndX() {
    return endX;
  }

  @Override
  public int getEndY() {
    return endY;
  }

  @Override
  public int getEndWidth() {
    return endWidth;
  }

  @Override
  public int getEndHeight() {
    return endHeight;
  }

  @Override
  public Color getEndColor() {
    return endColor;
  }

  @Override
  public String getTextResult() {
    return String.format(
        "motion %s %d %d %d %d %d %d %d %d "
            + "%d %d %d %d %d %d %d %d \n",
        name, startTime, startX, startY, startWidth, startHeight, startColor.getRed(),
        startColor.getGreen(), startColor.getBlue(),
        endTime, endX, endY, endWidth, endHeight, endColor.getRed(),
        endColor.getGreen(), endColor.getBlue());
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public int compareTo(IMotion m) {
    return this.startTime - m.getStartTime();
  }

  @Override
  public IShape executeMotion(int tick) {
    int totalTicks = endTime - startTime;
    if (totalTicks != 0) {
      shape.changeColor(startColor, endColor, totalTicks, tick, startTime, endTime);
      shape.changeSize(startWidth, startHeight, endWidth, endHeight, totalTicks, tick, startTime,
          endTime);
      shape.move(startX, startY, endX, endY, totalTicks, tick, startTime, endTime);
    }
    return new SimpleShape((SimpleShape) shape);
  }

  @Override
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
    } else {
      thisScaleType1 = "rx";
      thisScaleType2 = "ry";
      thisPosType1 = "cx";
      thisPosType2 = "cy";
    }

    // svg format of move
    if (startX - endX != 0 || startY - endY != 0) {
      result = "<animate attributeType=\"xml\" begin=\""
          + Double.toString((startTime) * (1000 / speed)) + "ms\" dur=\""
          + Double.valueOf((endTime - startTime) * (1000 / speed))
          + "ms\" attributeName=\"" + thisPosType1 + "\"" + " from=\"" + Double
          .toString(this.startX)
          + "\" to=\"" + Double.toString(this.endX) + "\" fill=\"freeze\"/>\n"
          + "<animate attributeType=\"xml\" begin=\""
          + Double.toString(startTime * (1000 / speed)) + "ms\" dur=\""
          + Double.toString((endTime - startTime) * (1000 / speed))
          + "ms\" attributeName=\"" + thisPosType2 + "\"" + " from=\""
          + Double.toString(this.startY) + "\" to=\"" + Double.toString(this.endY)
          + "\" fill=\"freeze\" />\n\n";
    }
    // svg format of change size
    if (startWidth - endWidth != 0 || startHeight - endHeight != 0) {
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

    // svg format of change color
    if (startColor.getRGB() != endColor.getRGB()) {
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


  /**
   * Mutates this motion into the given motion.
   *
   * @param newMotion the given motion that this motion is changing into.
   */
  protected void changeTo(IMotion newMotion) {
    this.startX = newMotion.getStartX();
    this.startY = newMotion.getStartY();
    this.startWidth = newMotion.getStartWidth();
    this.startHeight = newMotion.getStartHeight();
    this.startColor = newMotion.getStartColor();
    this.shape = new SimpleShape(name, type, startX, startY, startWidth, startHeight, startColor);
  }

  @Override
  public void fixEndings(IMotion m) {
    this.endX = m.getStartX();
    this.endY = m.getStartY();
    this.endWidth = m.getStartWidth();
    this.endHeight = m.getStartHeight();
    this.endColor = m.getStartColor();
    this.endTime = m.getStartTime();
  }

    @Override
    public void fixBeginning(IMotion m) {
    this.startX = m.getEndX();
    this.startY = m.getEndY();
    this.startWidth = m.getEndWidth();
    this.startHeight = m.getEndHeight();
    this.startColor = m.getEndColor();
    this.startTime = m.getEndTime();
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public IShape getShape() {
    return this.shape;
  }

  @Override
  public void fixLastEndings(IMotion m) {
    this.endX = m.getEndX();
    this.endY = m.getEndY();
    this.endWidth = m.getEndWidth();
    this.endHeight = m.getEndHeight();
    this.endColor = m.getEndColor();
    this.endTime = m.getEndTime();
  }


  @Override
  public void becomesKeyframe(boolean isEndKeyFrame) {
    if (isEndKeyFrame) {
      this.startX = this.endX;
      this.startY = this.endY;
      this.startWidth = this.endWidth;
      this.startHeight = this.endHeight;
      this.startColor = this.endColor;
      this.startTime = this.endTime;
    } else {
      this.endX = this.startX;
      this.endY = this.startY;
      this.endWidth = this.startWidth;
      this.endHeight = this.startHeight;
      this.endColor = this.startColor;
      this.endTime = this.startTime;
    }
  }
}
