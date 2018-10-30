package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;
import java.awt.Color;

public class ChangeColor implements ICommand {
  String shapeName;
  Color color;
  int totalTicks;

  public ChangeColor(String shapeName, Color color, int totalTicks) {
    this.shapeName = shapeName;
    this.color = color;
    this.totalTicks = totalTicks;
  }

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().get(shapeName).changeColor(color, totalTicks);
  }

}
