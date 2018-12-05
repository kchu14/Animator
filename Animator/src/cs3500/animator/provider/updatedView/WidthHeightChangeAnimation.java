package cs3500.animator.provider.updatedView;

import cs3500.model.shape.ShapeTuple;

/**
 * Represents a motion changing the width and/or height of a Shape.
 */

public class WidthHeightChangeAnimation extends AbstractAnimation {

  /**
   * Constructs a scaling image given a starting shape, animation length, and scale factor.
   *
   * @param length animation length
   * @param shape starting shape
   * @param finalWidth the final value of the width of this shape
   * @param finalHeight the final value of the height of this shape
   */
  public WidthHeightChangeAnimation(int length, ShapeTuple shape, int finalWidth, int finalHeight) {
    super(shape, length, new ShapeTuple(shape.getKey(), shape.getValue()
        .remake(finalWidth, finalHeight, shape.getValue().getLocation().x,
            shape.getValue().getLocation().y,
            shape.getValue().getColor().getRed(), shape.getValue().getColor().getGreen(),
            shape.getValue().getColor().getBlue())));
  }

}
