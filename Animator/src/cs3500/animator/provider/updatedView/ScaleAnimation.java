package cs3500.animator.provider.updatedView;

import cs3500.model.shape.ShapeTuple;

//CHANGES FROM HOMEWORK 5: This class is "deprecated," as it's too specific to be useful and can
//be completely done in terms of a WidthHeightChangeAnimation

/**
 * Animation representing a scaling motion.
 */
public class ScaleAnimation extends AbstractAnimation {

  /**
   * Constructs a scaling image given a starting shape, animation length, and scale factor.
   *
   * @param length animation length
   * @param shape starting shape
   * @param scaleFactor scale factor
   */
  public ScaleAnimation(int length, ShapeTuple shape, int scaleFactor) {
    super(shape, length, new ShapeTuple(shape.getKey(), shape.getValue()
        .remake(shape.getValue().getWidth() * scaleFactor,
            shape.getValue().getHeight() * scaleFactor,
            shape.getValue().getLocation().x, shape.getValue().getLocation().y,
            shape.getValue().getColor().getRed(),
            shape.getValue().getColor().getGreen(), shape.getValue().getColor().getBlue())));
  }

}
