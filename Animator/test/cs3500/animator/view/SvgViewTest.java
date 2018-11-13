package cs3500.animator.view;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import org.junit.Test;

public class SvgViewTest {

  @Test
  public void testSvgOutputChangeColor() {
    SvgView view = new SvgView("asdf", 1);
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();
    view.playAnimation(m);
    assertEquals(
        "<svg width=\"0\" height=\"0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n\n"
            + "<rect id=\"r\" x=\"100.0\" y=\"100.0\" width=\"50.0\" "
            + "height=\"50.0\" fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(255, 0, 0)\" to=\"rgb(0, 0, 255)\"/>\n"
            + "\n"
            + "</rect>\n"
            + "\n"
            + "</svg>", view.getOutPutText());
  }

  @Test
  public void testSvgOutputMove() {
    SvgView view = new SvgView("asdff", 1);
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 150, 250, 50, 50, 255, 0, 0).build();
    view.playAnimation(m);
    assertEquals(
        "<svg width=\"0\" height=\"0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n\n"
            + "<rect id=\"r\" x=\"100.0\" y=\"100.0\" width=\"50.0\" height=\"50.0\" "
            + "fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"x\" from=\"100.0\" to=\"150.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"y\" from=\"100.0\" to=\"250.0\" fill=\"freeze\" />\n"
            + "\n"
            + "</rect>\n"
            + "\n"
            + "</svg>", view.getOutPutText());
  }

  @Test
  public void testSvgOutputNoChange() {
    SvgView view = new SvgView("asdfff", 1);
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 255, 0, 0).build();
    view.playAnimation(m);
    assertEquals(
        "<svg width=\"0\" height=\"0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n\n"
            + "<rect id=\"r\" x=\"100.0\" y=\"100.0\" width=\"50.0\" height=\"50.0\""
            + " fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n"
            + "</rect>\n"
            + "\n"
            + "</svg>", view.getOutPutText());
  }

  @Test
  public void testSvgOutputChangeSize() {
    SvgView view = new SvgView("asdfffa", 1);
    AnimatorModel m = new AnimatorModelImpl.Builder().declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 100, 100, 255, 0, 0).build();
    view.playAnimation(m);
    assertEquals(
        "<svg width=\"0\" height=\"0\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n\n"
            + "<rect id=\"r\" x=\"100.0\" y=\"100.0\" width=\"50.0\" height=\"50.0\" "
            + "fill=\"rgb(255, 0, 0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"100.0\" fill=\"freeze\"/>\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"10000.0ms\" "
            + "attributeName=\"height\" from=\"50.0\" to=\"100.0\" fill=\"freeze\"/>\n"
            + "</rect>\n"
            + "\n"
            + "</svg>", view.getOutPutText());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSvgOutputNoAnimation() {
    AnimatorView view = new SvgView("outadsf", 1);
    AnimatorModel m = new AnimatorModelImpl.Builder().build();
    view.playAnimation(m);
  }
}