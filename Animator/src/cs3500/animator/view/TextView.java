package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is an implementation of the Animator View interface that represents the text view. It
 * outputs our animation to a text file or to system.out by default.
 */
public class TextView implements ITextView {

  private String fileOutput;
  private String fileOutputString;


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
    return fileOutputString;
  }

  /**
   * Produces a text output representation of the set of motions input into this animation.
   *
   * @return A String representing the animation through a list of motions.
   */
  public String produceTextView(Map<String, List<IMotion>> nameMotion,
      Map<String, String> nameType) {
    StringBuilder result = new StringBuilder();
    for (Entry<String, List<IMotion>> set : nameMotion.entrySet()) {
      String key = set.getKey();
      List<IMotion> value = set.getValue();
      result.append("Shape " + key + " " + nameType.get(key) + "\n");
      for (IMotion m : value) {
        result.append(m.getTextResult());
      }
    }
    this.fileOutputString = result.toString();
    return result.toString();
  }


  @Override
  public void playAnimation(IReadOnlyModel model) {
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
