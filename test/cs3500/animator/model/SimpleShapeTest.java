package cs3500.animator.model;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import org.junit.Test;

public class SimpleShapeTest {

  @Test
  public void testGetColor() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    assertEquals(simpleShape.getColor(), new Color(255, 0, 0));
    IShape ovalShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    assertEquals(ovalShape.getColor(), new Color(255, 0, 0));
  }

  @Test
  public void testGetShape() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    assertEquals(simpleShape.getShape(), new Rectangle(200, 200, 50, 100));
    IShape ovalShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    assertEquals(ovalShape.getShape(), new Ellipse2D.Double(200, 200, 50, 100));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveRectangleZeroTick() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveRectangleNegativeTick() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, -10);
  }
/*
  @Test
  public void moveRectangleSamePosition() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 1);
    assertEquals(200, simpleShape.getX());
    assertEquals(200, simpleShape.getY());
  }
*//*
  @Test
  public void moveRectangleOneTick() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 1);
    assertEquals(100, simpleShape.getX());
    assertEquals(100, simpleShape.getY());
  }

  @Test
  public void moveRectangleMoreThanOneTick() {
    IShape simpleShape =
            new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 100);
    assertEquals(199, simpleShape.getX());
    assertEquals(199, simpleShape.getY());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOvalZeroTick() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMoveOvalNegativeTick() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, -10);
  }

  @Test
  public void moveOvalSamePosition() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 1);
    assertEquals(200, simpleShape.getX());
    assertEquals(200, simpleShape.getY());
  }

  @Test
  public void moveOvalOneTick() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 1);
    assertEquals(100, simpleShape.getX());
    assertEquals(100, simpleShape.getY());
    simpleShape.move(200, 200, 1);
    assertEquals(200, simpleShape.getX());
    assertEquals(200, simpleShape.getY());
  }

  @Test
  public void moveOvalMoreThanOneTick() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(100, 100, 100);
    assertEquals(199, simpleShape.getX());
    assertEquals(199, simpleShape.getY());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorChange() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 255, 255), -10);
  }

  @Test
  public void changeColor() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 255, 255), 1);
    assertEquals(new Color(255, 255, 255), simpleShape.getColor());
  }

  @Test
  public void changeColorMoreThanOneTick() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 255, 255), 255);
    assertEquals(new Color(255, 1, 1), simpleShape.getColor());
  }

  @Test
  public void changeColorDecreaseColors() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(200, 200, 200), 55);
    assertEquals(new Color(254, 254, 254), simpleShape.getColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSizeChange() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeSize(10, 10, -10);
  }

  @Test
  public void changeSize() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeSize(1, 50, 1);
    assertEquals(1, simpleShape.getWidth());
    assertEquals(50, simpleShape.getHeight());
  }

  @Test
  public void changeSizeMoreThanOneTick() {
    IShape simpleShape =
            new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeSize(100, 150, 50);
    assertEquals(51, simpleShape.getWidth());
    assertEquals(101, simpleShape.getHeight());
  }*/

}