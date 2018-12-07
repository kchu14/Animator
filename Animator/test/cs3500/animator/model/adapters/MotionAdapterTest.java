package cs3500.animator.model.adapters;

import static org.junit.Assert.assertEquals;

import cs3500.animator.provider.model.Animation;
import cs3500.animator.provider.model.AnimationImp;
import cs3500.animator.provider.model.ShapeTuple;
import java.awt.Color;
import org.junit.Test;

public class MotionAdapterTest {

  @Test
  public void testGetLength() {
    ShapeTuple s = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 20.0, 20.0, Color.BLACK));
    ShapeTuple s2 = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 40.0, 40.0, Color.RED));
    Animation a = new AnimationImp(s);
    MotionAdapter m = new MotionAdapter(a, 5);
    assertEquals(0, m.getLength());
    m.addFrame(10, 1, 0, s2);
    assertEquals(1, m.getLength());
    m.addFrame(20, 0, 0, s);
    assertEquals(2, m.getLength());
  }

  @Test
  public void testGetFrames() {
    ShapeTuple s = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 20.0, 20.0, Color.BLACK));
    ShapeTuple s2 = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 40.0, 40.0, Color.RED));
    Animation a = new AnimationImp(s);
    MotionAdapter m = new MotionAdapter(a, 5);
    m.addFrame(10, 1, 0, s2);
    m.addFrame(20, 0, 0, s);
    assertEquals((Integer) 10, m.getFrames().get(0).getValue());
  }

  @Test
  public void testAddKeyFrameSameImage() {
    ShapeTuple s = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 20.0, 20.0, Color.BLACK));
    Animation a = new AnimationImp(s);
    MotionAdapter m = new MotionAdapter(a, 5);
    assertEquals(0, m.getFrames().size());
    m.addFrame(10, 0, 0, s);
    assertEquals(1, m.getFrames().size());
    m.addFrame(20, 0, 0, s);
    assertEquals(2, m.getFrames().size());
  }

  @Test
  public void testAddKeyFrameDifferentImage() {
    ShapeTuple s = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 20.0, 20.0, Color.BLACK));
    ShapeTuple s2 = new ShapeTuple("c", new ShapeAdapter("c", "ellipse", 1.0,
        2.0, 40.0, 40.0, Color.RED));
    Animation a = new AnimationImp(s);
    MotionAdapter m = new MotionAdapter(a, 5);
    assertEquals(0, m.getFrames().size());
    m.addFrame(10, 1, 0, s2);
    assertEquals(1, m.getFrames().size());
    m.addFrame(20, 0, 0, s);
    assertEquals(2, m.getFrames().size());
  }


}