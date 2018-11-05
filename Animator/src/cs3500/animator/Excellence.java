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

public final class Excellence {

  public static void main(String[] args) {
    //-in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "integer-ticks-per-second"
    String fileName = "";
    String viewType = "";
    String outType = "System.out";
    int speed = 1;
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        fileName = args[i++];
      }
      if (args[i].equals("-view")) {
        viewType = args[i++];
      }
      if (args[i].equals("-out")) {
        outType = args[i++];
      }
      if (args[i].equals("-speed")) {
        speed = Integer.parseInt(args[i++]);
      }
    }
    if (fileName.equals("") || viewType.equals("")) {
      throw new IllegalArgumentException("need to specify file name and view type");
    }

    AnimatorModel model;
    try {
      FileReader fr =
          new FileReader(fileName);
       model = AnimationReader
          .parseFile(fr, new AnimatorModelImpl.Builder());
      model.checkForValidMotions();
    } catch (Exception e) {
      throw new IllegalArgumentException("given file doesn't exist");
    }

    AnimatorView view = null;
    if(viewType.equals("text")) {
      view = new TextView();
    }

    if(viewType.equals("svg")) {
      view = new SvgView();
    }

    if(viewType.equals("visual")) {
      view = new VisualGraphicsView();
    }

    view.playAnimation(model);
//    IController controller = new MVCCommandController(model, view);
//    controller.go();

//    try {
//      new MarbleSolitaireControllerImpl(new InputStreamReader(System.in), System.out)
//          .playGame(model);
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }
}
