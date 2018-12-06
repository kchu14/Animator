package cs3500.animator.model.adapters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.provider.model.AnimationImp;
import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.ShapeTuple;
import java.awt.Color;
import org.junit.Test;

public class ProviderModelAdapterTest {

  @Test
  public void testGetAnimationState() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    assertEquals(1, m.getAnimationState(0).size());
    assertEquals(100, m.getAnimationState(0).get(0).getLocation().getY(), .001);
    assertEquals(100, m.getAnimationState(0).get(0).getLocation().getX(), .001);
    assertEquals(50, m.getAnimationState(0).get(0).getWidth());
    assertEquals(50, m.getAnimationState(0).get(0).getHeight());
  }

  @Test
  public void testaddAnimation() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);
    m.addAnimation(at);
    assertEquals(1, m.getAnimationState(0).size());
  }


  @Test
  public void testremoveAnimation() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);
    m.removeAnimation(at);
    assertEquals(1, m.getAnimationState(0).size());
  }


  @Test
  public void testgetBounds() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    assertArrayEquals(new int[]{0, 0, 500, 500}, m.getBounds());
  }

  @Test
  public void testgetShapes() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    assertEquals(50, m.getShapes().get(0).getValue().getWidth());
    assertEquals(100, m.getShapes().get(0).getValue().getLocation().getY(), .001);

  }

  @Test
  public void testgetMotionsOfShape() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    assertEquals(50,
        m.getMotionsOfShape("r").get(0).getKey().getFrames().get(0).getKey().getValue().getWidth());
    assertEquals(100,
        m.getMotionsOfShape("r").get(0).getKey().getFrames().get(0).getKey().getValue()
            .getLocation().getY(), .001);


  }

  @Test
  public void testgetLastTick() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    assertEquals(10,
        m.getLastTick());
  }

  @Test
  public void testaddShape() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("aer", new ShapeAdapter("aer", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    m.addShape(s);
    assertEquals(20.0,
        m.getShapes().get(1).getValue().getWidth(), .001);
    assertEquals(2.0,
        m.getShapes().get(1).getValue().getLocation().getY(), .001);
  }

  @Test
  public void testremoveShape() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("aer", new ShapeAdapter("aer", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    m.addShape(s);
    assertEquals(20.0,
        m.getShapes().get(1).getValue().getWidth(), .001);
    assertEquals(2.0,
        m.getShapes().get(1).getValue().getLocation().getY(), .001);
    assertEquals(2,
        m.getShapes().size());
    m.removeShape("aer");
    assertEquals(1,
        m.getShapes().size());
  }

  @Test
  public void testupdateKeyframeOfAnimation() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));

    m.updateKeyframeOfAnimation(s, 10);
    assertEquals(20.0, m.getAnimationState(10).get(0).getWidth(), .001);
    assertEquals(2.0, m.getAnimationState(10).get(0).getLocation().getY(), .001);
  }
}