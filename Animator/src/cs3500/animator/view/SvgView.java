package cs3500.animator.view;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyModel;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;

/**
 * This is an implementation of the Animator View interface that represents the svg view. It outputs
 * our animation to a svg file that can be played in browser.
 */
public class SvgView implements ISvgView {

  private String fileOut;
  private int speed;
  protected String outPutText;

  /**
   * Constructs a svg view.
   *
   * @param fileOut the file output name.
   * @param speed the speed of the animation.
   */
  public SvgView(String fileOut, int speed) {
    this.fileOut = fileOut;
    this.speed = speed;
  }


  @Override
  public void playAnimation(IReadOnlyModel model) {
    StringBuilder inSvg = new StringBuilder();
    inSvg.append("<svg width=\"" + model.getWidth() + "\" height=\"" + model.getHeight()
        + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");

    for (Entry<String, List<Motion>> set : model.getMotions().entrySet()) {
      String key = set.getKey();
      List<Motion> lom = set.getValue();
      inSvg.append(model.getShapes().get(key).toSVG());
      for (Motion m : lom) {
        inSvg.append(m.toSVG(speed));
      }
      if (model.getNameType().get(key).equals("rectangle")) {
        inSvg.append("</rect>\n\n");
      } else {
        inSvg.append("</ellipse>\n\n");
      }
    }
    inSvg.append("</svg>");
    outPutText = inSvg.toString();
    byte[] data = inSvg.toString().getBytes();
    Path p = Paths.get("./", this.fileOut);//String.valueOf(outFile));
    try (OutputStream out = new BufferedOutputStream(
        Files.newOutputStream(p, CREATE, APPEND))) {
      out.write(data, 0, data.length);
    } catch (IOException x) {
      System.err.println(x);
    }
  }


  @Override
  public String getOutPutText() {
    return outPutText;
  }
}
