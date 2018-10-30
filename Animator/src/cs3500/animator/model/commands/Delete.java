package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

public class Delete implements ICommand {
  String shapeName;

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().remove(shapeName);
  }

}
