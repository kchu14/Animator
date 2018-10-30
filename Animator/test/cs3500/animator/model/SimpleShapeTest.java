package cs3500.animator.model;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;

public class SimpleShapeTest {

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveRectangleZeroTick() {
    SimpleShape simpleShape =
        new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveRectangleNegativeTick() {
    SimpleShape simpleShape =
        new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, -10);
  }

  @Test
  public void moveRectangleSamePosition() {
    SimpleShape simpleShape =
        new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 1);
    assertEquals(200, simpleShape.getX());
    assertEquals(200, simpleShape.getY());
  }

  @Test
  public void moveRectangleOneTick() {
    SimpleShape simpleShape =
        new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 1);
    assertEquals(100, simpleShape.getX());
    assertEquals(100, simpleShape.getY());
  }

  @Test
  public void moveRectangleMoreThanOneTick() {
    SimpleShape simpleShape =
        new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 100);
    assertEquals(199, simpleShape.getX());
    assertEquals(199, simpleShape.getY());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOvalZeroTick() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOvalNegativeTick() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, -10);
  }

  @Test
  public void moveOvalSamePosition() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 1);
    assertEquals(200, simpleShape.getX());
    assertEquals(200, simpleShape.getY());
  }

  @Test
  public void moveOvalOneTick() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 1);
    assertEquals(100, simpleShape.getX());
    assertEquals(100, simpleShape.getY());
    simpleShape.move(200, 200, 1);
    assertEquals(200, simpleShape.getX());
    assertEquals(200, simpleShape.getY());
  }

  @Test
  public void moveOvalMoreThanOneTick() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 100);
    assertEquals(199, simpleShape.getX());
    assertEquals(199, simpleShape.getY());
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidColorChange() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 255,255), -10);
  }

  @Test
  public void changeColor() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 255,255), 1);
    assertEquals(new Color(255, 255,255), simpleShape.getColor());
  }

  @Test
  public void changeColorMoreThanOneTick() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 255,255), 255);
    assertEquals(new Color(255, 1,1), simpleShape.getColor());
  }

  @Test
  public void changeColorDecreaseColors() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 255, 255));
    simpleShape.changeColor(new Color(200, 200,200), 55);
    assertEquals(new Color(254, 254,254), simpleShape.getColor());
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidSizeChange() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeSize(10 , 10, -10);
  }

  @Test
  public void changeSize() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 255, 255));
    simpleShape.changeSize(1, 50, 1);
    assertEquals(1, simpleShape.getWidth());
    assertEquals(50, simpleShape.getHeight());
  }

  @Test
  public void changeSizeMoreThanOneTick() {
    SimpleShape simpleShape =
        new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 255, 255));
    simpleShape.changeSize(100, 150, 50);
    assertEquals(51, simpleShape.getWidth());
    assertEquals(101, simpleShape.getHeight());
  }

}