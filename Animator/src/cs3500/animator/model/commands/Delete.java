package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;

/**
 * This class represents a delete function object command and implements apply.
 */
public class Delete implements ICommand {

  String shapeName;

  /**
   * Constructs a delete object to be used in the model.
   *
   * @param shapeName the given name of the shape to be deleted
   */
  public Delete(String shapeName) {
    this.shapeName = shapeName;
  }

  @Override
  public void apply(AnimatorModel a) {
    a.getShapes().remove(shapeName);
  }

  @Override
  public String getShapeName() {
    return shapeName;
  }

}
