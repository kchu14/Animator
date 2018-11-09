package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
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

  @Test
  public void testProduceTextView() {
    AnimatorModel m;
    m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();
    assertEquals("Shape r rectangle\n"
        + "motion r 0 100 100 50 50 255 0 0 10 100 100 50 50 0 255 0 \n", m.produceTextView());
  }

  @Test
  public void testUpdate() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 255, 0, 0).build();

    ArrayList<IShape> ls = new ArrayList<>();
    ls.add(new SimpleShape("r", "rectangle", 100.0, 100.0, 50.0, 50.0, new Color(255, 0, 0)));
    assertEquals(ls.get(0).toSVG(), m.update(9).get(0).toSVG());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testUpdateNoMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle").build();

    assertEquals(new ArrayList<IShape>(), m.update(10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionNoShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();
  }


  @Test
  public void testAddMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();
    assertEquals(1, m.getMotions().size());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMotion() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255)
        .addMotion("r", 6, 100, 100, 50, 50, 255, 0, 0,
            13, 100, 100, 50, 50, 0, 0, 255).build();

    m.checkForValidMotions();
  }

  @Test
  public void testValidMotions() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255)
        .addMotion("r", 10, 100, 100, 50, 50, 0, 0, 255,
            14, 100, 100, 50, 50, 0, 0, 255).build();

    m.checkForValidMotions();

    assertEquals("Shape r rectangle\n"
        + "motion r 0 100 100 50 50 255 0 0 10 100 100 50 50 0 255 0 \n"
        + "motion r 10 100 100 50 50 0 255 0 14 100 100 50 50 0 255 0 \n", m.produceTextView());
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
        .build();
  }

  @Test
  public void testCreateShape() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0, 10, 100, 100, 50, 50, 0, 0, 255).build();
    m.checkForValidMotions();
    assertEquals(1, m.getShapes().size());
  }


}