package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import cs3500.animator.view.TextView;
import java.awt.Color;
import org.junit.Test;

public class AnimatorModelImplTest {

  @Test
  public void testGetLastTick() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();

    assertEquals(10, m.getLastTick());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testUpdateNoMotion() {
    ReadOnlyModel m = new ReadOnlyModel(new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle").build());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNoShape() {
    ReadOnlyModel m = new ReadOnlyModel(new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build());
  }


  @Test
  public void testAddMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();
    assertEquals(1, m.getMotions().size());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testTwoShapesSameName() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle").declareShape("r", "oval").build();
  }

  @Test
  public void testManyShapesUniqueNames() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle").declareShape("o", "oval")
        .declareShape("o2", "oval")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0, 10, 100, 100, 50, 50, 0, 0, 255)
        .addMotion("o", 0, 100, 100, 50, 50, 255, 0, 0, 10, 100, 100, 50, 50, 0, 0, 255)
        .addMotion("o2", 0, 100, 100, 50, 50, 255, 0, 0, 10, 100, 100, 50, 50, 0, 0, 255)
        .build();
    assertEquals(3, m.getShapes().size());
  }


  @Test
  public void testOverlap() {
    TextView view = new TextView("outtie2");
    ReadOnlyModel m = new ReadOnlyModel(new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build());
    view.playAnimation(m);
    assertEquals("Shape r rectangle\n"
        + "motion r 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0 \n"
        + "motion r 10 200 200 50 100 255 0 0 50 300 300 50 100 255 0 0 \n", view.getFileOutput());
  }


  @Test
  public void testdeclareNewShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build();
    assertEquals(1, m.getNameType().size());
    m.declareNewShape(new SimpleShape("c", "ellipse"));
    assertEquals(2, m.getNameType().size());
  }

  @Test
  public void testRemoveShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build();
    assertEquals(1, m.getNameType().size());
    m.removeShape("r");
    assertEquals(0, m.getNameType().size());
  }

  @Test
  public void testAddNewMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .build();
    assertEquals(1, m.getMotions().get("r").size());
    m.addNewMotion(new Motion("r", "rectangle", 1, 200, 200, 50, 100, new Color(255, 0, 0),
        10, 200, 200, 50, 100, new Color(255, 0, 0)));
    assertEquals(2, m.getMotions().get("r").size());
  }

  @Test
  public void testEditMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build();
    m.editMotion(new Motion("r", "rectangle", 10, 200, 200, 50, 100, new Color(0, 0, 255),
        10, 200, 200, 50, 100, new Color(0, 0, 255)));
    assertEquals(new Color(0, 0, 255), m.getMotions().get("r").get(0).endColor);

  }

  @Test
  public void testRemoveMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build();
    assertEquals(2, m.getMotions().get("r").size());
    m.removeMotion(new Motion("r", "rectangle", 1, 200, 200, 50, 100, new Color(255, 0, 0),
        10, 200, 200, 50, 100, new Color(255, 0, 0)));
    assertEquals(1, m.getMotions().get("r").size());
  }

  @Test
  public void testAddShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .build();
    assertEquals(1, m.getShapes().size());
    m.declareNewShape(new SimpleShape("c", "ellipse"));
    m.addShape(new Motion("c", "ellipse", 51, 200, 200, 50, 100, new Color(255, 0, 0),
        55, 300, 300, 50, 100, new Color(255, 0, 0)));
    assertEquals(2, m.getShapes().size());
  }

  @Test
  public void testAddSameShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .build();
    assertEquals(1, m.getShapes().size());
    m.addShape(new Motion("r", "ellipse", 10, 200, 200, 50, 100, new Color(255, 0, 0),
        50, 300, 300, 50, 100, new Color(255, 0, 0)));
    assertEquals(1, m.getShapes().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void declareSameNamedShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .build();
    m.declareNewShape(new SimpleShape("r", "ellipse"));
  }

  @Test
  public void testGetKeyFramesAndMotions() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .build();
    assertEquals(1, m.getMotions().get("r").size());
    assertEquals(2, m.getKeyFrames().get("r").size());
    m.addNewMotion(new Motion("r", "rectangle", 51, 200, 200, 50, 100, new Color(255, 0, 0),
        80, 200, 200, 50, 100, new Color(255, 0, 0)));
    assertEquals(2, m.getMotions().get("r").size());
    assertEquals(3, m.getKeyFrames().get("r").size());
  }


}