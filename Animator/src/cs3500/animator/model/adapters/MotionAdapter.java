package cs3500.animator.model.adapters;

import cs3500.animator.model.Motion;
import cs3500.animator.provider.model.Animation;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.ShapeTuple;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts the provider's "Animation" interface to our Motion class.
 */
public class MotionAdapter extends Motion implements Animation {

  private List<Keyframe> keyframes;

  /**
   * Constructs a motionAdapter.
   * @param animation The animation storing shape and values of it from the view.
   * @param tick      The tick of this motion.
   */
  public MotionAdapter(Animation animation, int tick) {
    super(animation.getOriginalShape().getKey(),
        (animation.getOriginalShape().getValue().getTextRepresentation().equals("Rectangle"))
            ? "rectangle" : "ellipse",
        tick, (int) animation.getOriginalShape().getValue().getLocation().getX(),
        (int) animation.getOriginalShape().getValue().getLocation().getY(),
        animation.getOriginalShape().getValue().getWidth(),
        animation.getOriginalShape().getValue().getHeight(),
        animation.getOriginalShape().getValue().getColor(),
        tick, (int) animation.getOriginalShape().getValue().getLocation().getX(),
        (int) animation.getOriginalShape().getValue().getLocation().getY(),
        animation.getOriginalShape().getValue().getWidth(),
        animation.getOriginalShape().getValue().getHeight(),
        animation.getOriginalShape().getValue().getColor());
    System.out.println(animation.getOriginalShape().getValue().getTextRepresentation());
    keyframes = new ArrayList<>();
  }

  @Override
  public Color[][] getVisualFrame(int n, int width, int height) {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public String getTextFrame(int n) {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public Shape getState(int n, int startN) {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }


  @Override
  public ShapeTuple getOriginalShape() {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public ShapeTuple getFinalShape() {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public void updateKeyframe(ShapeTuple shape, int tick) {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public void deleteFrame(Keyframe keyframe) {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public List<Keyframe> getFrames() {
    return keyframes;
  }


  @Override
  public Keyframe addFrame(int tick, int index, int startN, ShapeTuple name) {
    Keyframe result = new Keyframe(name, tick);
    this.keyframes.add(result);
    return result;
  }

  @Override
  public int getLength() {
    return keyframes.size();
  }


}
