package cs3500.animator.provider.model;


import cs3500.animator.model.IMotion;
import cs3500.animator.model.Motion;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AnimationImp implements Animation {

  private ShapeTuple shapeTuple;

  private List<Keyframe> frames;
  public AnimationImp(ShapeTuple shapeTuple) {
    this.shapeTuple = shapeTuple;
    this.frames = new ArrayList<>();
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
  public int getLength() {
    throw new UnsupportedOperationException("This method is not called in our implementation.");

  }

  @Override
  public ShapeTuple getOriginalShape() {
    return this.shapeTuple;
  }

  @Override
  public ShapeTuple getFinalShape() {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }

  @Override
  public List<Keyframe> getFrames() {
    throw new UnsupportedOperationException("This method is not called in our implementation.");

  }

  @Override
  public void deleteFrame(Keyframe keyframe) {
      frames.remove(keyframe);
  }

  @Override
  public Keyframe addFrame(int tick, int index, int startN, ShapeTuple name) {
    Keyframe k = new Keyframe(name, tick);
    frames.add(k);
    return k;
  }

  @Override
  public void updateKeyframe(ShapeTuple shape, int tick) {
    throw new UnsupportedOperationException("This method is not called in our implementation.");
  }
}
