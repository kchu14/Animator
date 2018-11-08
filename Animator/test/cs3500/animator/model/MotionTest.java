package cs3500.animator.model;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class MotionTest {
  @Test
  public void testGetTextResult() {
    Motion m = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
            new Color(255, 0 ,0), 10, 200, 200, 50, 100, new Color(255, 0 , 0));
    assertEquals(m.getTextResult(), "motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0");
  }

  @Test
  public void testCompareTo() {
    Motion earlier = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
            new Color(255, 0 ,0), 10, 200, 200, 50, 200, new Color(255, 0 , 0));
    Motion later = new Motion("R", "rectangle", 20, 200, 200, 50, 100,
            new Color(255, 0 ,0), 300, 200, 200, 50, 200, new Color(255, 0 , 0));
    assertEquals(earlier.compareTo(later), 1);
    assertEquals(later.compareTo(earlier), -1);
    assertEquals(earlier.compareTo(earlier), 0);
  }

  @Test
  public void testExecuteMotion(){
    //height is changed (should go up by 10 after one tick)
    Motion m = new Motion("R", "rectangle", 1, 200, 200, 50, 100,
            new Color(255, 0 ,0), 10, 200, 200, 50, 200, new Color(255, 0 , 0));
    assertEquals(m.executeMotion(), new SimpleShape("R", "rectangle", 200,
            200, 50, 110, new Color(255, 0 ,0)));
  }
}