package cs3500.animator.provider.updatedView;
import java.awt.Point;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents an Ellipse.
 */
public class Ellipse extends AbstractShape {

  private final Point center;

  /**
   * Constructs an Ellipse given both radii, center point, and color.
   *
   * @param horizontalRadius horizontal radius
   * @param verticalRadius vertical radius
   * @param anchor center point
   * @param color color of shape
   */
  public Ellipse(int horizontalRadius, int verticalRadius, Point anchor, Color color) {
    if (anchor == null || color == null) {
      throw new IllegalArgumentException("One or both of given anchor point and color were null");
    } else if (horizontalRadius < 1 || verticalRadius < 1) {
      throw new IllegalArgumentException("One or both radii were non-positive");
    }

    this.width = horizontalRadius * 2;
    this.height = verticalRadius * 2;
    this.location = anchor;
    this.color = color;
    this.center = new Point(anchor.x + horizontalRadius, anchor.y + verticalRadius);
  }

  /**
   * Get visual representation of shape given a frame width and height.
   *
   * @param frameWidth the width of the canvas in which the shape is being drawn
   * @param frameHeight the height of the canvas in which the shape is being drawn
   * @return 2-D array of colors
   */
  @Override
  public Color[][] getVisualRepresentation(int frameWidth, int frameHeight) {
    Color[][] canvas = new Color[frameWidth][frameHeight];
    for (int row = 0; row < frameWidth; row += 1) {
      for (int col = 0; col < frameHeight; col += 1) {
        if (shouldBeColored(new Point(col, row))) {
          canvas[row][col] = this.color;
        } else {
          canvas[row][col] = Color.WHITE;
        }
      }
    }
    return canvas;
  }

  /**
   * Get text representation of shape.
   *
   * @return string description of shape
   */
  @Override
  public String getTextRepresentation() {
    return "Ellipse " + super.generateShapeInformation();
  }

  /**
   * Returns true iff the point at (x,y) is within this ellipse, that is: if the integer distance
   * from the center to the given point is less than or equal to the distance from the center to the
   * edge of the ellipse along the same line, this method will return .
   *
   * @param p the point to be tested
   * @return true iff the above constraints are filled
   */
  private boolean shouldBeColored(Point p) {
    Double pythagoreanDistance = (Math.sqrt(Math.pow(this.center.getX() - p.getX(), 2) + Math.pow(
        this.center.getY() - p.getY(),
        2)));
    Double theta = Math.atan(((p.getY() - this.center.getY()) / (p.getX() - this.center.getX())));
    return (pythagoreanDistance.intValue() <= getDistanceFromEdge(theta));
  }

  /**
   * Returns the integer distance between the center of this ellipse and the edge along the line
   * along the given angle. We can use integer distance since we're using pixels, so we'll need to
   * convert to ints anyway.
   *
   * @param theta the angle off of the horizontal centered at the center of this ellipse, in
   *     radians
   */
  private int getDistanceFromEdge(double theta) {
    double horizRad = width / 2.0;
    double vertRad = height / 2.0;
    double coCoordX = Math.pow((Math.cos(theta) / horizRad), 2);
    double coCoordY = Math.pow((Math.sin(theta) / vertRad), 2);
    Double result = (Math.sqrt(1 / coCoordX + coCoordY));
    return result.intValue();
  }

  /**
   * Get copy of shape.
   *
   * @return shape copy
   */
  @Override
  public Shape copy() {
    return new Ellipse(this.width / 2,
        this.height / 2,
        new Point(this.location.x, this.location.y),
        new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()));
  }

  @Override
  public Shape remake(int newWidth, int newHeight, int newX, int newY, int red, int green,
      int blue) {
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Given width or height is non-positive");
    } else if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("Given color components out of the range [0, 255]");
    }
    //Ensures that the shape gets scaled if newWidth = 1
    Double horizRadius = Math.ceil(newWidth / 2.0);
    Double vertRadius = Math.ceil(newHeight / 2.0);
    return new Ellipse(horizRadius.intValue(), vertRadius.intValue(), new Point(newX, newY),
        new Color(red, green, blue));
  }

  /**
   * Check if equal to given object.
   *
   * @param o object to check
   * @return boolean true if equivalent
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Ellipse)) {
      return false;
    }
    Ellipse that = (Ellipse) o;
    return this.width == that.width && this.height == that.height
        && this.location.x == that.location.x && this.location.y == that.location.y
        && this.color.getRed() == that.color.getRed()
        && this.color.getGreen() == that.color.getGreen()
        && this.color.getBlue() == that.color.getBlue();
    //We do not check center because it is calculated based on width and height
  }

  /**
   * Get hashcode of shape.
   *
   * @return int hash of shape
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height, this.location, this.color, this.center);
  }

}
