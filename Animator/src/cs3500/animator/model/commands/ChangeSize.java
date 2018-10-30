package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

public class ChangeSize implements ICommand {
  String shapeName;
  int width, height, totalTicks;

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

}
