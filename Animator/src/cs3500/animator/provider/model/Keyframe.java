package cs3500.animator.provider.model;


public class Keyframe implements Tuple<ShapeTuple, Integer> {

  private ShapeTuple shape;
  private int tick;

  /**
   * Constructs an animation tuple given an animation and start tick.
   *
   * @param shape shapeTuple for tuple
   * @param tick tick at which the shape exists in this form
   */

  public Keyframe(ShapeTuple shape, Integer tick) {
    this.shape = shape;
    this.tick = tick;

  }
//
//getFrames
//  getTextRepresentation

  @Override
  public ShapeTuple getKey() {
    return this.shape;
  }

  @Override
  public Integer getValue() {
    return this.tick;
  }
}
