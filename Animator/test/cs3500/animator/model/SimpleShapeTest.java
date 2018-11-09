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


  @Test
  public void moveRectangleSamePosition() {
    IShape simpleShape =
        new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 200, 200, 1, 2, 1, 2);
    assertEquals(
        "<rect id=\"r\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n",
        simpleShape.toSVG());
  }

  @Test
  public void moveRectangleOneTick() {
    IShape simpleShape =
        new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 201, 201, 1, 2, 1, 2);
    assertEquals(
        "<rect id=\"r\" x=\"201.0\" y=\"201.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n",
        simpleShape.toSVG());
  }

  @Test
  public void moveRectangleMoreThanOneTick() {
    IShape simpleShape =
        new SimpleShape("r", "rectangle", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.move(200, 200, 210, 210, 9, 10, 1, 10);
    assertEquals(
        "<rect id=\"r\" x=\"210.0\" y=\"210.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n",
        simpleShape.toSVG());
  }


  @Test
  public void changeColor() {
    IShape simpleShape =
        new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 0, 0), new Color(0, 0, 0), 1, 2, 1, 2);
    assertEquals(new Color(0, 0, 0), simpleShape.getColor());
  }

  @Test
  public void changeColorMoreThanOneTick() {
    IShape simpleShape =
        new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 0, 0), new Color(255, 255, 255), 9, 10, 1, 10);
    assertEquals(new Color(255, 255, 255), simpleShape.getColor());
  }

  @Test
  public void changeColorDecreaseColors() {
    IShape simpleShape =
        new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeColor(new Color(255, 0, 0), new Color(0, 0, 0), 1, 2, 1, 2);
    assertEquals(new Color(0, 0, 0), simpleShape.getColor());
  }

  @Test
  public void changeSize() {
    IShape simpleShape =
        new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeSize(200, 200, 300, 300, 1, 2, 1, 2);
    assertEquals(300, ((SimpleShape) simpleShape).height, .01);
    assertEquals(300, ((SimpleShape) simpleShape).width, .01);
  }

  @Test
  public void changeSizeMoreThanOneTick() {
    IShape simpleShape =
        new SimpleShape("o", "ellipse", 200, 200, 50, 100, new Color(255, 0, 0));
    simpleShape.changeSize(200, 200, 300, 300, 9, 10, 1, 10);
    assertEquals(300, ((SimpleShape) simpleShape).height, .01);
    assertEquals(300, ((SimpleShape) simpleShape).width, .01);
  }

}