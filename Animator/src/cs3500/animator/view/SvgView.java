package cs3500.animator.view;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;

public class SvgView implements AnimatorView {

  private String fileOut;
  private int speed;
  public SvgView(String fileOut, int speed) {
    this.fileOut = fileOut;
    this.speed = speed;
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void playAnimation(AnimatorModel model) {
    StringBuilder inSvg = new StringBuilder();
    inSvg.append("<svg width=\"" + model.getWidth() + "\" height=\"" + model.getHeight()
        +"\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n\n");
    for (Entry<String, IShape> set : model.getShapes().entrySet()) {
      String key = set.getKey();
      IShape value = set.getValue();
      inSvg.append(value.toSVG());
      for (Entry<String, List<Motion>> set2 : model.getMotions().entrySet()) {
        String key2 = set2.getKey();
        List<Motion> value2 = set2.getValue();
        if (key.equals(key2)) {
          for (Motion m : value2) {
            inSvg.append(m.toSVG(speed));
          }
        }
      }
      if (value.getType().equals("rectangle")) {
        inSvg.append("</rect>\n\n");
      } else {
        inSvg.append("</ellipse>\n\n");
      }
    }
    inSvg.append("</svg>");


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
  public void setWindow(int width, int height, int x, int y) {

  }



}
