package cs3500.animator.model.adapters;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.SimpleShape;
import cs3500.animator.provider.model.Animation;
import cs3500.animator.provider.model.AnimationImp;
import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.ShapeTuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
    IMotion m = new Motion(animT.getKey().getOriginalShape().getKey(),
        (animT.getKey().getOriginalShape().getValue().getTextRepresentation().equals("Rectangle"))
            ? "rectangle" : "ellipse",
        animT.getValue(), (int) animT.getKey().getOriginalShape().getValue().getLocation().getX(),
        (int) animT.getKey().getOriginalShape().getValue().getLocation().getY(),
        animT.getKey().getOriginalShape().getValue().getWidth(),
        animT.getKey().getOriginalShape().getValue().getHeight(),
        animT.getKey().getOriginalShape().getValue().getColor(),
        animT.getValue(), (int) animT.getKey().getOriginalShape().getValue().getLocation().getX(),
        (int) animT.getKey().getOriginalShape().getValue().getLocation().getY(),
        animT.getKey().getOriginalShape().getValue().getWidth(),
        animT.getKey().getOriginalShape().getValue().getHeight(),
        animT.getKey().getOriginalShape().getValue().getColor());
    System.out.println(animT.getKey().getOriginalShape().getValue().getTextRepresentation());
    ourModel.addShape(m);
    ourModel.addNewMotion(m);
    return this;

  }

  @Override
  public ExcelAnimatorModel removeAnimation(AnimationTuple animT) {
    IMotion m = new Motion(animT.getKey().getOriginalShape().getKey(),
        (animT.getKey().getOriginalShape().getValue().getTextRepresentation().equals("Rectangle"))
            ? "rectangle" : "ellipse",
        animT.getValue(), (int) animT.getKey().getOriginalShape().getValue().getLocation().getX(),
        (int) animT.getKey().getOriginalShape().getValue().getLocation().getY(),
        animT.getKey().getOriginalShape().getValue().getWidth(),
        animT.getKey().getOriginalShape().getValue().getHeight(),
        animT.getKey().getOriginalShape().getValue().getColor(),
        animT.getValue(), (int) animT.getKey().getOriginalShape().getValue().getLocation().getX(),
        (int) animT.getKey().getOriginalShape().getValue().getLocation().getY(),
        animT.getKey().getOriginalShape().getValue().getWidth(),
        animT.getKey().getOriginalShape().getValue().getHeight(),
        animT.getKey().getOriginalShape().getValue().getColor());
    ourModel.removeMotion(m);
    return this;
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

    int count = 0;
    if (ourModel.getMotions().get(name) != null) {
      for (IMotion m : ourModel.getMotions().get(name)) {
        ShapeTuple st = new ShapeTuple(m.getName(), new ShapeAdapter((SimpleShape) m.getShape()));
        Animation ai = new AnimationImp(st);
        ai.addFrame(m.getStartTime(), count, m.getStartTime(), st);
        count++;
        MotionAdapter mo = new MotionAdapter(ai, m.getStartTime());
        mo.addFrame(m.getStartTime(), 0, 0, st);
        result.add(new AnimationTuple(mo,
            0));

        if (count == ourModel.getMotions().get(name).size()) {
          result.add(new AnimationTuple(mo, m.getEndTime() - m.getStartTime()));
        }

      }
    }

    return result;
  }

  @Override
  public int getLastTick() {
    return ourModel.getLastTick();
  }

  @Override
  public void addShape(ShapeTuple shape) {
    ourModel.declareNewShape(new SimpleShape(shape.getKey(),
        (shape.getValue().getTextRepresentation().equals("Rectangle"))
            ? "rectangle" : "ellipse",
        shape.getValue().getLocation().getX(),
        shape.getValue().getLocation().getY(), shape.getValue().getWidth(),
        shape.getValue().getHeight(), shape.getValue().getColor()));
    IMotion m = new Motion(shape.getKey(),
        (shape.getValue().getTextRepresentation().equals("Rectangle"))
            ? "rectangle" : "ellipse",
        0, (int) shape.getValue().getLocation().getX(),
        (int) shape.getValue().getLocation().getY(),
        shape.getValue().getWidth(),
        shape.getValue().getHeight(),
        shape.getValue().getColor(),
        0, (int) shape.getValue().getLocation().getX(),
        (int) shape.getValue().getLocation().getY(),
        shape.getValue().getWidth(),
        shape.getValue().getHeight(),
        shape.getValue().getColor());
    ourModel.addShape(m);
  }

  @Override
  public void removeShape(String name) {
    ourModel.removeShape(name);
  }

  @Override
  public void updateKeyframeOfAnimation(ShapeTuple shape, int tick) {
    IMotion m = new Motion(shape.getKey(),
        (shape.getValue().getTextRepresentation().equals("Rectangle"))
            ? "rectangle" : "ellipse",
        tick, (int) shape.getValue().getLocation().getX(),
        (int) shape.getValue().getLocation().getY(),
        shape.getValue().getWidth(),
        shape.getValue().getHeight(),
        shape.getValue().getColor(),
        tick, (int) shape.getValue().getLocation().getX(),
        (int) shape.getValue().getLocation().getY(),
        shape.getValue().getWidth(),
        shape.getValue().getHeight(),
        shape.getValue().getColor());
    ourModel.editMotion(m);
  }
}
