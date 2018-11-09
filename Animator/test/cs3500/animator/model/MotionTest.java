package cs3500.animator.model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class MotionTest {

  @Test
  public void testGetTextResult() {
    Motion m = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
        new Color(255, 0, 0), 10, 200, 200, 50, 100, new Color(255, 0, 0));
    assertEquals("motion R 1 200 200 50 100 255 0 0 10 200 200 50 100 255 0 0 \n",
        m.getTextResult());
  }

  @Test
  public void testCompareTo() {
    Motion earlier = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
        new Color(255, 0, 0), 10, 200, 200, 50, 200, new Color(255, 0, 0));
    Motion later = new Motion("R", "rectangle", 20, 200, 200, 50, 100,
        new Color(255, 0, 0), 300, 200, 200, 50, 200, new Color(255, 0, 0));
    assertEquals(-19, earlier.compareTo(later));
    assertEquals(19, later.compareTo(earlier));
    assertEquals(0, earlier.compareTo(earlier));
  }

  @Test
  public void testExecuteMotion() {
    Motion m = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
        new Color(255, 0, 0), 11, 200, 200, 50, 200, new Color(255, 0, 0));
    assertEquals(new SimpleShape("R", "rectangle", 200,
        200, 50, 110, new Color(255, 0, 0)).toSVG(), m.executeMotion(2).toSVG());
  }

  @Test
  public void testToSvg() {
    Motion m = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
        new Color(255, 0, 0), 11, 200, 200, 50, 200, new Color(255, 0, 0));
    assertEquals(
        "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"10000.0\" attributeName=\"width\" from=\"50.0\" to=\"50.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"10000.0ms\" attributeName=\"height\" from=\"100.0\" to=\"200.0\" fill=\"freeze\"/>\n",
        m.toSVG(1));
  }
}