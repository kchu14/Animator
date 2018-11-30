package cs3500.animator.provider.model;


/**
 * Represents an animation and its location in time relative to the model's scale.
 */

public class AnimationTuple implements Tuple<Animation, Integer> {

  private Animation animation;
  private int startTick;

  /**
   * Constructs an animation tuple given an animation and start tick.
   *
   * @param animation animation for tuple
   * @param startTick start tick for animation
   */
  public AnimationTuple(Animation animation, Integer startTick) {
    this.animation = animation;
    this.startTick = startTick;

  }

  @Override
  public Animation getKey() {
    return this.animation;
  }

  @Override
  public Integer getValue() {
    return this.startTick;
  }

}
