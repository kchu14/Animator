package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Motion;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is an implementation of the Animator View interface that represents the text view. It outputs our animation to a
 * text file or to system.out by default.
 */
public class TextView implements AnimatorView {

  private String fileOutput;


  /**
   * Constructs a text view.
    * @param fileOutput the output file name.
   */
  public TextView(String fileOutput) {
    this.fileOutput = fileOutput;
  }


  @Override
  public void showErrorMessage(String error) {

  }

  private String produceTextView(Map<String, List<Motion>> nameMotion, Map<String, String> nameType) {
    StringBuilder result = new StringBuilder();
    for (Entry<String, List<Motion>> set : nameMotion.entrySet()) {
      String key = set.getKey();
      List<Motion> value = set.getValue();
      result.append("Shape " + key + " " + nameType.get(key) + "\n");
      for (Motion m : value) {
        result.append(m.getTextResult());
      }
    }
    return result.toString();
  }


  @Override
  public void playAnimation(AnimatorModel model) {
    if (this.fileOutput.equals("System.out")) {
      System.out.println(this.produceTextView(model.getMotions(), model.getNameType()));
    } else {
      Writer writer = null;

      String[] motions = this.produceTextView(model.getMotions(), model.getNameType()).split("\n");

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


}
