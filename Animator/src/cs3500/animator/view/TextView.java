package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyModel;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is an implementation of the Animator View interface that represents the text view. It
 * outputs our animation to a text file or to system.out by default.
 */
public class TextView implements ITextView {

  private String fileOutput;
  protected String fileOutputString;


  /**
   * Constructs a text view.
   *
   * @param fileOutput the output file name.
   */
  public TextView(String fileOutput) {
    this.fileOutput = fileOutput;
  }

  @Override
  public String getFileOutput() {
    return new String(fileOutputString);
  }

  /**
   * Produces a text output representation of the set of motions input into this animation.
   *
   * @return A String representing the animation through a list of motions.
   */
  private String produceTextView(Map<String, List<Motion>> nameMotion,
      Map<String, String> nameType) {
    StringBuilder result = new StringBuilder();
    for (Entry<String, List<Motion>> set : nameMotion.entrySet()) {
      String key = set.getKey();
      List<Motion> value = set.getValue();
      result.append("Shape " + key + " " + nameType.get(key) + "\n");
      for (Motion m : value) {
        result.append(m.getTextResult());
      }
    }
    this.fileOutputString = result.toString();
    return result.toString();
  }


  @Override
  public void playAnimation(ReadOnlyModel model) {
    if (this.fileOutput.equals("System.out")) {
      System.out.println(this.produceTextView(model.getMotions(), model.getNameType()));
    } else {
      BufferedWriter writer = null;

      String[] motions = this.produceTextView(model.getMotions(),
          model.getNameType()).split("\n");

      try {
        writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(fileOutput)));
        for (String m : motions) {
          writer.write(m);
           writer.newLine();
        }

      } catch (IOException ex) {
        throw new IllegalArgumentException(ex.getMessage());
      } finally {
        try {
          writer.close();
        } catch (Exception ex) {
          throw new IllegalArgumentException(ex.getMessage());
        }
      }
    }
  }


}
