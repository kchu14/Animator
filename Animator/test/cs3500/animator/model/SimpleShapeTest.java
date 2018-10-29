package cs3500.animator.model;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;

public class SimpleShapeTest {

  @Test
  public void move() {
    IShape simpleShape =
        new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100);
    assertEquals(, );
  }

  @Test
  public void changeColor() {
  }

  @Test
  public void changeSize() {
  }

  @Test
  public void delete() {
  }
}