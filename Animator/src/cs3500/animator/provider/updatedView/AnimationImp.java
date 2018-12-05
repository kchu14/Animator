package cs3500.animator.provider.updatedView;
import cs3500.model.shape.ShapeTuple;

public class AnimationImp extends AbstractAnimation {


  /**
   * Constructs a static 1 keyframe shape
   * @param shape
   */
  public AnimationImp(ShapeTuple shape, int tick) {
    super(shape, tick);
    System.out.println("Keyframes length: " + this.keyframes.size());

  }

}