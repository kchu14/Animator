package cs3500.animator;

import cs3500.animator.controller.EditController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.adapters.ProviderModelAdapter;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.ExcelAnimatorController;
import cs3500.animator.model.adapters.ProviderController;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.EditableView;
import cs3500.animator.view.SvgView;
import cs3500.animator.view.TextView;
import cs3500.animator.view.IVisualGraphicsView;
import cs3500.animator.view.VisualGraphicsView;
import java.io.FileReader;

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
    String fileName = "";
    String viewType = "";
    String outType = "System.out";
    String errorMsg = "invalid arguments";
    boolean error = false;
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
      error = true;
      errorMsg = "need to specify file name and view type";
    }

    AnimatorModel model;
    AnimatorView view = null;
    if (!viewType.equals("provider")) {
      view = constructView(viewType, outType, speed);
    } else {
      if (view == null || error) {
        IVisualGraphicsView v = new VisualGraphicsView(speed);
        v.showErrorMessage(errorMsg);
        System.exit(0);
      }
    }
    try {
      FileReader fr =
          new FileReader(fileName);
      model = AnimationReader
          .parseFile(fr, new AnimatorModelImpl.Builder());
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    if (viewType.equals("provider")) {
      ExcelAnimatorController c = new ProviderController(new ProviderModelAdapter(model),
          (double) speed);
      EditorView providerView = new EditorView(c);
      c.setView(providerView);
      c.play();
    } else {
      ReadOnlyModel readOnlyModel = new ReadOnlyModel(model);
      view.playAnimation(readOnlyModel);
      if (view instanceof EditableView) {
        IController controller = new EditController(model, (EditableView) view);
      }
    }

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

    if (viewType.equals("edit")) {
      view = new EditableView(new VisualGraphicsView(speed));
    }

    return view;
  }
}
