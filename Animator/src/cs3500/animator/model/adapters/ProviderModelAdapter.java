package cs3500.animator.model.adapters;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.SimpleShape;
import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.ShapeTuple;
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
    List<Shape> shapeList = new ArrayList<>();
    for (IShape shape : ourModel.getTickListShapes().get(n)) {
      shapeList.add(new ShapeAdapter((SimpleShape) shape));
    }
    return shapeList;
  }

  @Override
  public ExcelAnimatorModel addAnimation(AnimationTuple animT) {
    throw new UnsupportedOperationException("This method is never used in the provided views.");
  }

  @Override
  public ExcelAnimatorModel removeAnimation(AnimationTuple animT) {
    throw new UnsupportedOperationException("This method is never used in the provided views.");
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
      result.add(new ShapeTuple(shape.getKey(), new ShapeAdapter((SimpleShape) shape.getValue())));
    }

    return result;
  }

  @Override
  public List<AnimationTuple> getMotionsOfShape(String name) {
    List<AnimationTuple> result = new ArrayList<>();

    for(Motion m : ourModel.getMotions().get(name)) {
      result.add(new AnimationTuple(new Animation(m), m.getStartTime()));
    }

    return result;
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
