package cs3500.animator.model;

import static org.junit.Assert.*;

import java.awt.Color;
import org.junit.Test;

public class ComplexShapeTest {



  @Test(expected = IllegalArgumentException.class)
  public void invalidMovePolygonZeroTick() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 0, 0));
    c.move(100, 100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMovePolygonNegativeTick() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 0, 0));
    c.move(100, 100, -10);
  }

  @Test
  public void movePolygonSamePosition() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 0, 0));
    c.move(100, 100, 1);
    assertEquals(100, c.getX());
    assertEquals(100, c.getY());
  }

  @Test
  public void movePolygonOneTick() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 0, 0));
    c.move(100, 100, 1);
    assertEquals(100, c.getX());
    assertEquals(100, c.getY());
    c.move(200, 200, 1);
    assertEquals(200, c.getX());
    assertEquals(200, c.getY());
  }

  @Test
  public void moveOvalMoreThanOneTick() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 0, 0));
    c.move(100, 100, 100);
    assertEquals(1, c.getX());
    assertEquals(1, c.getY());
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidColorChange() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 229, 184));
    c.changeColor(Color.BLACK, 0);
  }

  @Test
  public void changeColor() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 229, 184));
    c.changeColor(Color.white, 1);
    assertEquals(new Color(255, 255,255), c.getColor());
  }

  @Test
  public void changeColorMoreThanOneTick() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 0, 0));
    c.changeColor(Color.white, 255);
    assertEquals(new Color(255, 1,1), c.getColor());
  }

  @Test
  public void changeColorDecreaseColors() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 255, 255));
    c.changeColor(new Color(255, 200, 200), 55);
    assertEquals(new Color(255, 254,254), c.getColor());
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidSizeChange() {
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 255, 255));
    c.changeSize(10, 55, 0);
  }

  @Test
  public void changeSize() {
    // unsure of how to implement change size for complex polygons (need more information
    // about animation width and height)
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 255, 255));
    c.changeSize(10, 55, 1);
  }

  @Test
  public void changeSizeMoreThanOneTick() {
    // unsure of how to implement change size for complex polygons (need more information
    // about animation width and height)
    IShape c =
        new ComplexShape("o", new int[] {0,0,1}, new int[]{0,2,1},  new Color(255, 255, 255));
    c.changeSize(10, 55, 100);
  }
}