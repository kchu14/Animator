package cs3500.animator.model;

import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.ShapeTuple;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

/**
 * Represents an adapter to allow our AnimatorModel implementation to implement the methods from
 * ExcelAnimatorModel interface.
 */
public class ProviderModelAdapter implements ExcelAnimatorModel {

  private AnimatorModel ourModel;

  /**
   * Constructs an instance of a ProviderModelAdapter.
   *
   * @param ourModel An animatorModel implementation.
   */
  public ProviderModelAdapter(AnimatorModel ourModel) {
    this.ourModel = ourModel;

  }

  @Override
  public List<Shape> getAnimationState(int n) {
    return null;
  }

  @Override
  public ExcelAnimatorModel addAnimation(AnimationTuple animT) {
    return null;
  }

  @Override
  public ExcelAnimatorModel removeAnimation(AnimationTuple animT) {
    return null;
  }

  @Override
  public String textView() {
    throw new UnsupportedOperationException("This method is never used in the provided views.");
  }

  @Override
  public int[] getBounds() {
    int[] result = {ourModel.getWindowX(), ourModel.getWindowY(), ourModel.getWidth(),
        ourModel.getHeight()};
    return result;
  }

  @Override
  public List<ShapeTuple> getShapes() {
    List<ShapeTuple> result = new ArrayList<>();
    for (Entry<String, IShape> shape : ourModel.getShapes().entrySet()) {
      result.add(new ShapeTuple(shape.getKey(), new ShapeAdapter(shape.getValue())));
    }

    return
  }

  @Override
  public List<AnimationTuple> getMotionsOfShape(String name) {
    return null;
  }

  @Override
  public int getLastTick() {
    return ourModel.getLastTick();
  }

  @Override
  public void addShape(ShapeTuple shape) {

  }

  @Override
  public void removeShape(String name) {
    ourModel.removeShape(name);
  }

  @Override
  public void updateKeyframeOfAnimation(ShapeTuple shape, int tick) {

  }
}
