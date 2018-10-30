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
    ICommand changeColor = new ChangeColor("rr", new Color(255, 255, 255), 1);
    ICommand changeSize = new ChangeSize("oo", 2, 3, 1);
    ICommand delete = new Delete("rr");
    List<ICommand> result = new ArrayList<>();
    result.add(move);
    result.add(changeColor);
    result.add(changeSize);
    result.add(delete);
    return result;
  }


  private List<ICommand> initCommands2() {
    ICommand move = new Move("rr", 100, 100, 1);
    ICommand changeColor = new ChangeColor("rr", new Color(255, 255, 255), 1);
    ICommand changeSize = new ChangeSize("oo", 2, 3, 1);
    List<ICommand> result = new ArrayList<>();
    result.add(move);
    result.add(changeColor);
    result.add(changeSize);
    return result;
  }

  @Test
  public void isAnimationOver() {
    assertFalse(m.isAnimationOver());
  }

  @Test
  public void emptyAnimation() {

    assertEquals("", m.getModelState());
    assertEquals(0, m.getShapes().size());
    assertEquals(0, m.getCommands().size());

  }

  @Test
  public void addShape() {
    initData();
    assertEquals(2, m.getShapes().size());

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
    assertEquals(2, m.getShapes().size());
  }

  @Test
  public void testTickResult() {
    initData();
    List<ICommand> c = initCommands2();
    m.addCommand(c, 1, 5);
    /**
     *
     * asdfasdf
     *
     *
     *
     *
     *
     *
     *
     */
  }

  @Test
  public void testGetModelState() {
    initData();
    List<ICommand> c = initCommands2();
    m.addCommand(c, 1, 5);
    assertEquals("shape r rectangle\n"
        + "motion r 1 200 200 50 100 255 0 0 \n"
        + "motion r 1 100 100 50 100 255 255 255 \n"
        + "motion r 2 100 100 50 100 255 255 255 \n"
        + "motion r 2 100 100 50 100 255 255 255 \n"
        + "motion r 3 100 100 50 100 255 255 255 \n"
        + "motion r 3 100 100 50 100 255 255 255 \n"
        + "motion r 4 100 100 50 100 255 255 255 \n"
        + "motion r 4 100 100 50 100 255 255 255 \n"
        + "shape o oval\n"
        + "motion o 1 200 200 2 3 255 0 0 \n"
        + "motion o 2 200 200 2 3 255 0 0 \n"
        + "motion o 3 200 200 2 3 255 0 0 \n"
        + "motion o 4 200 200 2 3 255 0 0 \n", m.getModelState());

  }
}