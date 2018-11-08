package cs3500.animator.model.commands;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.ICommand;
import cs3500.animator.model.SimpleShape;
import java.awt.Color;
import org.junit.Test;

public class CommandsTest {
//
//  AnimatorModel m = new AnimatorModelImpl(1);
//
//  private void initData() {
//    m = new AnimatorModelImpl(1);
//    m.addShape(new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0)));
//    m.addShape(new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0)));
//
//  }
//
//  @Test
//  public void testApply() {
//    initData();
//    ICommand move = new Move("rr", 100, 100, 1);
//    m.getShapes();
//    move.apply(m);
//    assertEquals(100, m.getShapes().get("rr").getX());
//    assertEquals(100, m.getShapes().get("rr").getY());
//
//    ICommand changeColor = new ChangeColor("rr", new Color(255, 255, 255), 1);
//    changeColor.apply(m);
//    assertEquals(new Color(255, 255, 255), m.getShapes().get("rr").getColor());
//
//    ICommand changeSize = new ChangeSize("oo", 2, 3, 1);
//    changeSize.apply(m);
//    assertEquals(2, m.getShapes().get("oo").getWidth());
//    assertEquals(3, m.getShapes().get("oo").getHeight());
//
//    ICommand delete = new Delete("rr");
//    delete.apply(m);
//    assertEquals(1, m.getShapes().size());
//  }
}