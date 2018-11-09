package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextView implements AnimatorView {

  private String fileOutput;

  public TextView(String fileOutput) {
    this.fileOutput = fileOutput;
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
//    Path file = Paths.get(fileOutput);
//    String output = model.produceTextView();
//    Files.write(file, output);
//    Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

    if (this.fileOutput.equals("System.out")) {
      System.out.println(model.produceTextView());
    } else {
      Writer writer = null;

      String[] motions = model.produceTextView().split("\n");

      try {
        writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(fileOutput)));
        for (String m : motions) {
          writer.write(m);
          ((BufferedWriter) writer).newLine();
        }

      } catch (IOException ex) {
        this.showErrorMessage(ex.getMessage());
      } finally {
        try {
          writer.close();
        } catch (Exception ex) {
          this.showErrorMessage(ex.getMessage());
        }
      }
    }
  }

  @Override
  public void setWindow(int width, int height, int x, int y) {

  }

}
