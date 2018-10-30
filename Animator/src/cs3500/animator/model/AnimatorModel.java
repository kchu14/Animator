package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface specifies the operations of a model. The model is what controls the functionality
 * of the animation.
 */
public interface AnimatorModel {

  /**
   * This method enumerates all of the shapes in the model to one list to be visualized.
   *
   * @return a list of all of the shapes that exist inside of this model
   */
  List<IShape> tickResult();

  /**
   * Checks to see if the animation has been completed.
   *
   * @return true if the animation is over, and false if it is not.
   */
  boolean isAnimationOver();

  /**
   * Adds a shape to the map of shapes inside of the animator model.
   *
   * @param s The shape that is to be added
   */
  void addShape(IShape s);

  /**
   * Adds a command to the map of commands inside of the animator model.
   *
   * @param c The commands that will be executed between the given ticks.
   * @param start The beginning tick that the command will execute.
   * @param end The tick after this command has finished.
   */
  void addCommand(List<ICommand> c, int start, int end);


  /**
   * This method gets the hashmap of shapes in the model.
   *
   * @return The hashmap of shapes in this animator model.
   */
  Map<String, IShape> getShapes();

  /**
   * This method gets the treemap of commands in the model.
   *
   * @return The treemap of commands in this animator model.
   */
  Map<Integer, List<ICommand>> getCommands();

  /**
   * This method prints out the state of the model. (shape, name, type of shape) (motion, name, x,
   * y, w, h, r, g, b) for start and end
   *
   * @return the string representation of the animation
   */
  String getModelState();
}
