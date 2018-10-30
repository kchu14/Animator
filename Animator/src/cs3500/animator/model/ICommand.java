package cs3500.animator.model;

public interface ICommand {

  /**
   * Applies the command function object to the animator model and the desired shape (based on its
   * name).
   * @param a The animator model that is being manipulated by this command.
   */
  void apply(AnimatorModel a);

}