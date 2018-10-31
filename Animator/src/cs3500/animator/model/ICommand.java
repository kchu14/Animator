package cs3500.animator.model;

/**
 * This interface specifies the operations of a command. A command is a given user motive or
 * objective like move or change color.
 */
public interface ICommand {

  /**
   * Applies the command function object to the animator model and the desired shape (based on its
   * name).
   *
   * @param a The animator model that is being manipulated by this command.
   */
  void apply(AnimatorModel a);

  /**
   * Gets the name of the shape that this command is executing on.
   *
   * @return The name of the shape being altered.
   */
  String getShapeName();

  /**
   * Gets the type of command that is being executed.
   * @return The name of this command.
   */
  String getCommandType();
}