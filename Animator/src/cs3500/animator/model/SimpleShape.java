package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This class represents a generic shape and implements all of its associated operations.
 */
public class SimpleShape implements IShape {

  protected String name;
  protected String type;
  protected double x;
  protected double y;
  protected double width;
  protected double height;
  protected Color color;
  protected Shape shape;

  public SimpleShape(String name, String type, double x, double y, double width, double height,
      Color color) {
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.color = color;
    getCurShape();

  }

  public void getCurShape() {
    if (type.equals("rectangle")) {
      this.shape = new Rectangle2D.Double( x, y, width, height);

    }
    if (type.equals("ellipse")) {
      this.shape = new Ellipse2D.Double(x, y, width, height);
    }
  }

  @Override
  public void move(int startX, int startY, int endX, int endY, int totalTicksi, int currentTicki,
      int startTimei, int endTimei) {

    double totalTicks = (double) totalTicksi;
    double currentTick = (double) currentTicki;
    double startTime = (double) startTimei;
    double endTime = (double) endTimei;

    this.x = (startX * ((endTime - currentTick) / totalTicks)
        + endX * ((currentTick - startTime) / totalTicks));

    this.y = (startY * ((endTime - currentTick) / totalTicks)
        + endY * ((currentTick - startTime) / totalTicks));
    getCurShape();


  }

  @Override
  public void changeColor(Color startColor, Color endColor,int totalTicksi, int currentTicki,
      int startTimei, int endTimei) {

    double totalTicks = (double) totalTicksi;
    double currentTick = (double) currentTicki;
    double startTime = (double) startTimei;
    double endTime = (double) endTimei;

    int red  = (int) (startColor.getRed() * ((endTime - currentTick) / totalTicks)
        + endColor.getRed() * ((currentTick - startTime) / totalTicks));

    int green = (int) (startColor.getGreen() * ((endTime - currentTick) / totalTicks)
        + endColor.getGreen() * ((currentTick - startTime) / totalTicks));

    int blue= (int) (startColor.getBlue() * ((endTime - currentTick) / totalTicks)
        + endColor.getBlue() * ((currentTick - startTime) / totalTicks));

    this.color = new Color(red, green, blue);
    getCurShape();
  }

  @Override
  public void changeSize(int startWidth, int startHeight, int endWidth, int endHeight, int totalTicksi, int currentTicki,
      int startTimei, int endTimei) {

    double totalTicks = (double) totalTicksi;
    double currentTick = (double) currentTicki;
    double startTime = (double) startTimei;
    double endTime = (double) endTimei;

    width  = (startWidth * ((endTime - currentTick) / totalTicks)
        + endWidth * ((currentTick - startTime) / totalTicks));

    height = (startHeight * ((endTime - currentTick) / totalTicks)
        + endHeight* ((currentTick - startTime) / totalTicks));
    getCurShape();
  }

  @Override
  public Shape getShape() {
    return shape;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String toSVG() {
    String thisType = "";
    if (type.equals("rectangle")) {
      thisType = "<rect id=\"" + name + "\" x=\""
          + this.x + "\" y=\"" + this.y + "\" width=\"" + width
          + "\" height=\"" + height + "\" fill="
          + "\"rgb(" + Integer.toString((this.color.getRed()))
          + ", " + Integer.toString((this.color.getGreen())) + ", "
          + Integer.toString((this.color.getBlue()))
          + ")\"" + " visibility=\"visible\">\n";;

    }
    else {
      thisType = "<ellipse id=\"" + name + "\" cx=\""
          + this.x + "\" cy=\"" + this.y + "\" rx=\"" + width
          + "\" ry=\"" + height + "\" fill="
          + "\"rgb(" + Integer.toString((this.color.getRed() ))
          + ", " + Integer.toString((this.color.getGreen() )) + ", "
          + Integer.toString((this.color.getBlue()))
          + ")\"" + " visibility=\"visible\">\n";;
    }
    return thisType;
  }

  @Override
  public String getType() {
    return new String(this.type);
  }


}
