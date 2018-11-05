package cs3500.animator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;


import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Delete;
import cs3500.animator.model.commands.Move;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class test {

  @Test
  public void testText() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle")
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0).build();

    assertEquals("Shape r rectangle\n"
        + "motion r 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0", m.produceTextView());
  }

  @Test
  public void testText2() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").declareShape("c", "ellipse")
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .addMotion("c", 6, 440, 70, 120, 60, 0, 0, 255,
            20, 440, 70, 120, 60, 0, 0, 255).build();

    assertEquals("Shape r rectangle\n"
        + "motion r 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0 \n"
        + "Shape c ellipse\n"
        + "motion c 6 440 70 120 60 0 255 0 20 440 70 120 60 0 255 0 \n", m.produceTextView());
  }

  @Test
  public void testOverlap() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build();
    m.checkOverlaps();
    assertEquals("", m.produceTextView());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOverlapFail() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            9, 200, 200, 50, 100, 255, 0, 0)
        .build();
    m.checkOverlaps();
    assertEquals("", m.produceTextView());
  }

  @Test (expected =  IllegalArgumentException.class)
  public void testTeleport() {
    AnimatorModel m = new AnimatorModelImpl.Builder().setBounds(0, 0, 100, 100)
        .declareShape("r", "rectangle").addMotion("r", 10, 201, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0)
        .addMotion("r", 1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0)
        .build();
    m.checkOverlaps();
    assertEquals("", m.produceTextView());
  }
}