package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

public class Move implements ICommand {
  String shapeName;
  int x, y, totalTicks;

  public Move(String shapeName, int x, int y, int totalTicks) {
    this.shapeName = shapeName;
    this.x = x;
    this.y = y;
    this.totalTicks = totalTicks;
  }

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().get(shapeName).move(x, y, totalTicks);
  }

}
