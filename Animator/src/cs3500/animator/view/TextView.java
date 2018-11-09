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

<<<<<<< HEAD
<<<<<<< HEAD
  //
//    List<String> lines = Arrays.asList("The first line", "The second line");
//    Path file = Paths.get("the-file-name.txt");
//    Files.write(file, lines, Charset.forName("UTF-8"));
  //Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
=======
>>>>>>> parent of 591ed9c... different version
  @Override
  public void makeVisible() {
=======
  private String fileOutput;

>>>>>>> 93c9189c790647e0737526dc310c219ff00a35cd

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
