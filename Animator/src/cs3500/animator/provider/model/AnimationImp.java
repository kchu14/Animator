package cs3500.animator.provider.model;


import java.awt.Color;
import java.util.List;

public class AnimationImp implements Animation {

  private ShapeTuple shapeTuple;

  public AnimationImp(ShapeTuple shapeTuple) {
    this.shapeTuple = shapeTuple;
  }

  @Override
  public Color[][] getVisualFrame(int n, int width, int height) {
    return new Color[0][];
  }

  @Override
  public String getTextFrame(int n) {
    return null;
  }

  @Override
  public Shape getState(int n, int startN) {
    return null;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public ShapeTuple getOriginalShape() {
    return null;
  }

  @Override
  public ShapeTuple getFinalShape() {
    return null;
  }

  @Override
  public List<Keyframe> getFrames() {
    return null;
  }

  @Override
  public void deleteFrame(Keyframe keyframe) {

  }

  @Override
  public Keyframe addFrame(int tick, int index, int startN, ShapeTuple name) {
    return null;
  }

  @Override
  public void updateKeyframe(ShapeTuple shape, int tick) {

  }
}
