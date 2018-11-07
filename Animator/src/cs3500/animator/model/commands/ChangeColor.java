package cs3500.animator.model.commands;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.ICommand;
import cs3500.animator.model.IShape;
import cs3500.animator.model.SimpleShape;
import java.awt.Color;

/**
 * This class represents a change color function object command and implements apply.
 */
public class ChangeColor implements ICommand {

  IShape shape;
  Color futureColor;
  int startTick;
  int totalTicks;

  /**
   * Constructs a change color object to be used in the model.
   *
   * @param shapeName the given shape name of the shape to be changed
   * @param color the desired color
   * @param totalTicks the total duration of the animation
   */
  public ChangeColor(String shapeName, Color color, int totalTicks) {
    this.shapeName = shapeName;
    this.color = color;
    this.totalTicks = totalTicks;
    this.commandType = "changecolor";
  }

  @Override
  public IShape apply(AnimatorModel a) {
   return new SimpleShape( )a.getShapes().get(shapeName).changeColor(color, totalTicks);
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
