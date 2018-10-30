package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

public class Move implements ICommand {
  String shapeName;
  int x, y, totalTicks;

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().get(shapeName).move(x, y, totalTicks);
  }

}
