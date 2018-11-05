package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

/**
 * This class represents a move function object command and implements apply.
 */
public class Move implements ICommand {

  String shapeName;
  String commandType;
  int x;
  int y;
  int totalTicks;

  /**
   * Constructs a move object to be used in the model.
   *
   * @param shapeName the given shape name.
   * @param x the desired x coord
   * @param y the desired y coord
   * @param totalTicks the total duration of the animation
   */
  public Move(String shapeName, int x, int y, int totalTicks) {
    this.shapeName = shapeName;
    this.x = x;
    this.y = y;
    this.totalTicks = totalTicks;
    this.commandType = "move";
  }

  @Override
  public void apply(AnimatorModel a) {
   // a.getShapes().get(shapeName).move(x, y, totalTicks);
  }

  @Override
  public String getShapeName() {
    return shapeName;
  }

  @Override
  public String getCommandType() {
    return commandType;
  }

}
