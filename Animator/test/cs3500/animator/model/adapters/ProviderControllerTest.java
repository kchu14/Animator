package cs3500.animator.model.adapters;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.provider.model.AnimationImp;
import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.ShapeTuple;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.ExcelAnimatorController;
import java.awt.Color;
import org.junit.Test;

public class ProviderControllerTest {

  @Test
  public void testAddKeyFrame() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ExcelAnimatorController c = new ProviderController(m, 1.0);
    EditorView eView = new EditorView(c);
    c.setView(eView);
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 1.0,
        2.0, 20.0, 20.0, Color.BLACK));
    assertEquals(2, m.getMotionsOfShape("r").size());
    c.addKeyFrame(s, 15);
    assertEquals(3, m.getMotionsOfShape("r").size());
    assertEquals(1, m.getAnimationState(0).size());
    assertEquals(100, m.getAnimationState(0).get(0).getLocation().getY(), .001);
    assertEquals(100, m.getAnimationState(0).get(0).getLocation().getX(), .001);


  }

  @Test
  public void testdeleteKeyFrame() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ExcelAnimatorController c = new ProviderController(m, 1.0);
    EditorView eView = new EditorView(c);
    c.setView(eView);
    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 1.0,
        2.0, 20.0, 20.0, Color.BLACK));
    c.addKeyFrame(s, 15);
    assertEquals(3, m.getMotionsOfShape("r").size());
    c.deleteKeyFrame(s, new Keyframe(s, 15));
    assertEquals(2, m.getMotionsOfShape("r").size());
  }

  @Test
  public void testaddShape() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ExcelAnimatorController c = new ProviderController(m, 1.0);
    EditorView eView = new EditorView(c);
    c.setView(eView);

    ShapeTuple s = new ShapeTuple("aer", new ShapeAdapter("aer", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    c.addShape(s);
    assertEquals(20.0,
        m.getShapes().get(1).getValue().getWidth(), .001);
    assertEquals(2.0,
        m.getShapes().get(1).getValue().getLocation().getY(), .001);
  }

  @Test
  public void testRemoveShape() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ExcelAnimatorController c = new ProviderController(m, 1.0);
    EditorView eView = new EditorView(c);
    c.setView(eView);

    ShapeTuple s = new ShapeTuple("aer", new ShapeAdapter("aer", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));
    AnimationTuple at = new AnimationTuple(new AnimationImp(s), 20);

    c.addShape(s);
    assertEquals(20.0,
        m.getShapes().get(1).getValue().getWidth(), .001);
    assertEquals(2.0,
        m.getShapes().get(1).getValue().getLocation().getY(), .001);
    assertEquals(2,
        m.getShapes().size());
    c.removeShape("aer");
    assertEquals(1,
        m.getShapes().size());
  }

  @Test
  public void testUpdateKeyFrameAtTick() {
    ExcelAnimatorModel m = new ProviderModelAdapter(
        new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
            .declareShape("r", "rectangle")
            .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
                10, 100, 100, 50, 50, 0, 0, 255).build());
    ExcelAnimatorController c = new ProviderController(m, 1.0);
    EditorView eView = new EditorView(c);
    c.setView(eView);

    ShapeTuple s = new ShapeTuple("r", new ShapeAdapter("r", "rectangle", 100,
        2.0, 20.0, 20.0, Color.BLACK));

    c.updateKeyframeOfAnimation(s, 10);
    assertEquals(20.0, m.getAnimationState(10).get(0).getWidth(), .001);
    assertEquals(2.0, m.getAnimationState(10).get(0).getLocation().getY(), .001);
  }

}