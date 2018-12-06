package cs3500.animator.model.adapters;

import static org.junit.Assert.assertEquals;

import cs3500.animator.provider.model.Shape;
import java.awt.Color;
import org.junit.Test;

public class ShapeAdapterTest {

  @Test
  public void testGetTextRepresentation() {
    Shape s = new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 40.0, 40.0, Color.RED);
    assertEquals("Ellipse", s.getTextRepresentation());

    Shape s2 = new ShapeAdapter("r", "rectangle", 1.0,
        2.0, 40.0, 40.0, Color.RED);
    assertEquals("Rectangle", s2.getTextRepresentation());
  }

  @Test
  public void testRemake() {
    Shape s = new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 40.0, 40.0, Color.RED);
    assertEquals(2.0,  s.getLocation().getY(), .001);
    assertEquals(1.0,  s.getLocation().getX(), .001);
    assertEquals(40, s.getWidth());
    assertEquals(40, s.getHeight());

    Shape s2 = s.remake(30, 20, 6, 5, 255, 0, 0);

    assertEquals(2.0,  s.getLocation().getY(), .001);
    assertEquals(1.0,  s.getLocation().getX(), .001);
    assertEquals(40, s.getWidth());
    assertEquals(40, s.getHeight());

    assertEquals(5.0,  s2.getLocation().getY(), .001);
    assertEquals(6.0,  s2.getLocation().getX(), .001);
    assertEquals(30, s2.getWidth());
    assertEquals(20, s2.getHeight());
  }

}