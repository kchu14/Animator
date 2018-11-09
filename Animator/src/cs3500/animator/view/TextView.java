package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

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


  @Override
  public void playAnimation(AnimatorModel model) {
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


}
