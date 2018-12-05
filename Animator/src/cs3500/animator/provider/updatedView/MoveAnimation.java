package cs3500.animator.provider.updatedView;

import cs3500.model.shape.ShapeTuple;

/**
 * Animation representing a linear move.
 */
public class MoveAnimation extends AbstractAnimation {

  /**
   * Constructs a linear move animation given starting shape, animation length, and final point.
   *
   * @param length animation length
   * @param shape starting shape
   * @param finalX final x location
   * @param finalY final y location
   */
  public MoveAnimation(int length, ShapeTuple shape, int finalX, int finalY) {
    super(shape, length, new ShapeTuple(shape.getKey(), shape.getValue()
        .remake(shape.getValue().getWidth(), shape.getValue().getHeight(), finalX, finalY,
            shape.getValue().getColor().getRed(),
            shape.getValue().getColor().getGreen(), shape.getValue().getColor().getBlue())));
  }
}
