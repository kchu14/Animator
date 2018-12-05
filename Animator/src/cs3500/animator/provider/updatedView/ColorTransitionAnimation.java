package cs3500.animator.provider.updatedView;

import cs3500.model.shape.ShapeTuple;

/**
 * Animation representing a shape changing color over time.
 */
public class ColorTransitionAnimation extends AbstractAnimation {

  /**
   * Constructs a color transition animation given shape, animation length, and final color values.
   *
   * @param length animation length
   * @param shape starting shape to animate color change on
   * @param finalRed final red color value for transition
   * @param finalGreen final green color value for transition
   * @param finalBlue final blue color value for transition
   */
  public ColorTransitionAnimation(int length, ShapeTuple shape, int finalRed, int finalGreen,
      int finalBlue) {
    super(shape, length, new ShapeTuple(shape.getKey(), shape.getValue()
        .remake(shape.getValue().getWidth(), shape.getValue().getHeight(),
            shape.getValue().getLocation().x, shape.getValue().getLocation().y,
            finalRed, finalGreen, finalBlue)));
  }


}
