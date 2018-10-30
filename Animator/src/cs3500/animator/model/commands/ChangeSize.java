package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

/**
 * This class represents a change size function object command and implements apply.
 */
public class ChangeSize implements ICommand {

  String shapeName;
  int width, height, totalTicks;

  /**
   * Constructs a change size object to be used in the model.
   *
   * @param shapeName the given name of the shape to be changed
   * @param width the desired width
   * @param height the desired height
   * @param totalTicks the total duration of the animation
   */
  public ChangeSize(String shapeName, int width, int height, int totalTicks) {
    this.shapeName = shapeName;
    this.width = width;
    this.height = height;
    this.totalTicks = totalTicks;
  }

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().get(shapeName).changeSize(width, height, totalTicks);
  }

  @Override
  public String getShapeName() {
    return shapeName;
  }

}
