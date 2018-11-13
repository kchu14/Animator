package cs3500.animator.view;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import org.junit.Test;

public class TextViewTest {

  @Test
  public void testTextOutput() {
    TextView view = new TextView("asdfasdf");
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 0, 0, 255,
            10, 100, 100, 50, 50, 255, 0, 0)
        .addMotion("r", 10, 100, 100, 50, 50, 255, 0, 0,
            20, 200, 200, 50, 50, 255, 0, 0)
        .addMotion("r", 20, 200, 200, 50, 50, 255, 0, 0,
            30, 200, 200, 150, 150, 255, 0, 0)
        .addMotion("r", 30, 200, 200, 150, 150, 255, 0, 0,
            40, 200, 200, 150, 150, 255, 0, 0).build();
    view.playAnimation(m);
    assertEquals(
        "Shape r rectangle\n"
            + "motion r 0 100 100 50 50 0 255 0 10 100 100 50 50 255 0 0 \n"
            + "motion r 10 100 100 50 50 255 0 0 20 200 200 50 50 255 0 0 \n"
            + "motion r 20 200 200 50 50 255 0 0 30 200 200 150 150 255 0 0 \n"
            + "motion r 30 200 200 150 150 255 0 0 40 200 200 150 150 255 0 0 \n",
        view.fileOutputString);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testTextOutputNoAnimation() {
    AnimatorView view = new TextView("asdfasdf");
    AnimatorModel m = new AnimatorModelImpl.Builder().build();
    view.playAnimation(m);
  }

}