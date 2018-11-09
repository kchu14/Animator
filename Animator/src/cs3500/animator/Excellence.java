package cs3500.animator;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.VisualGraphicsView;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.scene.shape.SVGPath;

/**
 * This class contains the main method and runs the animation based off of the given command line
 * arguments.
 */
public final class Excellence {

  /**
   * The main method that runs the given animation.
   *
   * @param args the command line arguments that specifies the type of animation.
   */
  public static void main(String[] args) {
    //-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"
    String fileName = "";
    String viewType = "";
    String outType = "System.out";
    int speed = 1;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        fileName = args[i += 1];
      }
      if (args[i].equals("-view")) {
        viewType = args[i += 1];
      }
      if (args[i].equals("-out")) {
        outType = args[i += 1];
      }
      if (args[i].equals("-speed")) {
        speed = Integer.parseInt(args[i += 1]);
      }
    }
    if (fileName.equals("") || viewType.equals("")) {
      throw new IllegalArgumentException("need to specify file name and view type");
    }

    AnimatorModel model;
    AnimatorView view = constructView(viewType, outType, speed);

    try {
      FileReader fr =
          new FileReader(fileName);
      model = AnimationReader
          .parseFile(fr, new AnimatorModelImpl.Builder());
      model.checkForValidMotions();
    } catch (Exception e) {
      // add making error window
      view.showErrorMessage(e.getMessage());
      throw new IllegalArgumentException(e.getMessage());
    }

    // make it a read only model
    view.playAnimation(model);

  }

  /**
   * Constructs the correct view to be used given based on the given inputs.
   *
   * @param viewType the type of view (visual, text, svg)
   * @param outType the file output name
   * @param speed the speed of the animation
   * @return the view that follows the given parameters
   */
  private static AnimatorView constructView(String viewType, String outType, int speed) {
    AnimatorView view = null;
    if (viewType.equals("text")) {
      view = new TextView(outType);
    }

    if (viewType.equals("svg")) {
      view = new SvgView(outType, speed);
    }

    if (viewType.equals("visual")) {
      view = new VisualGraphicsView(speed);
    }

    return view;
  }
}
