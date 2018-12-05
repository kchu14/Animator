package cs3500.animator.provider.updatedView;
import java.awt.Point;
import java.awt.Color;
import java.util.Objects;

/**
 * Represents a rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs a rectangle given anchor point, width, height, and color.
   *
   * @param width int width
   * @param height int height
   * @param anchor anchor point
   * @param color color of shape
   */
  public Rectangle(int width, int height, Point anchor, Color color) {
    if (anchor == null || color == null) {
      throw new IllegalArgumentException("One or both of given anchor point and color were null");
    } else if (width < 1 || height < 1) {
      throw new IllegalArgumentException("One or both of width and height were non-positive");
    }

    this.width = width;
    this.height = height;
    this.location = anchor;
    this.color = color;
  }

  /**
   * Get visual representation of rectangle.
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
        if (row >= this.location.y && row <= this.location.y + this.height && col >= this.location.x
            && col <= this.location.x + this.width) {
          canvas[row][col] = this.color;
        } else {
          canvas[row][col] = Color.WHITE;
        }
      }
    }
    return canvas;
  }

  /**
   * Get textual representation of rectangle.
   *
   * @return string description of rectangle
   */
  @Override
  public String getTextRepresentation() {
    return "Rectangle " + super.generateShapeInformation();
  }

  /**
   * Get copy of rectangle.
   *
   * @return shape copy of this
   */
  @Override
  public Shape copy() {
    return new Rectangle(this.width,
        this.height,
        new Point(this.location.x, this.location.y),
        new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue()));
  }

  @Override
  public Shape remake(int newWidth, int newHeight, int newX, int newY, int red, int green,
      int blue) {
    //System.out.println(newWidth);
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Given width or height is non-positive");
    } else if (red > 255 || red < 0 || green > 255 || green < 0 || blue > 255 || blue < 0) {
      throw new IllegalArgumentException("Given color components out of the range [0, 255]");
    }
    return new Rectangle(newWidth, newHeight, new Point(newX, newY), new Color(red, green, blue));
  }

  /**
   * Check if this is equal to given object.
   *
   * @param o object to check
   * @return boolean true if equivalent
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof Rectangle)) {
      return false;
    }
    Rectangle that = (Rectangle) o;
    return this.width == that.width && this.height == that.height
        && this.location.x == that.location.x && this.location.y == that.location.y
        && this.color.getRed() == that.color.getRed()
        && this.color.getGreen() == that.color.getGreen()
        && this.color.getBlue() == that.color.getBlue();

  }

  /**
   * Get hashcode of this rectangle.
   *
   * @return int hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height, this.location, this.color);
  }
}
