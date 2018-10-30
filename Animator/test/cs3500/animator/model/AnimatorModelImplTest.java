package cs3500.animator.model;

import static org.junit.Assert.*;

import cs3500.animator.model.commands.ChangeColor;
import cs3500.animator.model.commands.ChangeSize;
import cs3500.animator.model.commands.Delete;
import cs3500.animator.model.commands.Move;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class AnimatorModelImplTest {

  AnimatorModel m = new AnimatorModelImpl(1);

  private void initData() {
    m = new AnimatorModelImpl(1);
    m.addShape(new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0)));
    m.addShape(new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0)));

  }

  private List<ICommand> initCommands() {
    ICommand move = new Move("rr", 100, 100, 1);
    ICommand changeColor = new ChangeColor("rr", new Color(255,255,255), 1);
    ICommand changeSize = new ChangeSize("oo", 2, 3, 1);
    ICommand delete = new Delete("rr");
    List<ICommand> result = new ArrayList<>();
    result.add(move);
    result.add(changeColor);
    result.add(changeSize);
    result.add(delete);
    return result;
  }


  @Test
  public void isAnimationOver() {
    assertFalse(m.isAnimationOver());
  }

  @Test
  public void addShape() {
    initData();
    assertEquals(2, m.getShapes().size());
    List<IShape> ls = new ArrayList<IShape>();
    ls.add(new SimpleShape("o", 1, 200, 200, 50, 100, new Color(255, 0, 0)));
    ls.add(new SimpleShape("r", 4, 200, 200, 50, 100, new Color(255, 0, 0)));

   // assertEquals(ls.toArray()[0]., m.tickResult().toArray()[0]);
  }

  @Test
  public void addCommand() {
    List<ICommand> c = initCommands();
    m.addCommand(c, 5, 20);
    assertEquals(4, m.getCommands().get(5).size());
  }


  @Test
  public void getShapes() {
    initData();
    assertEquals(2,m.getShapes().size());
  }
}