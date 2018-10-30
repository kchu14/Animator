package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

public class ChangeSize implements ICommand {
  String shapeName;
  int hDelta, vDelta, totalTicks;

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().get(shapeName).changeSize(hDelta, vDelta, totalTicks);
  }

}
