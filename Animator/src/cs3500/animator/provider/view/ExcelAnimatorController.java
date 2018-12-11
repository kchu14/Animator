package cs3500.animator.provider.view;


import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.ShapeTuple;


/**
 * Represents an Animation Controller.
 */

public interface ExcelAnimatorController {


  /**
   * Sets the view for this controller.
   * @param view view to interact with
   */
  public void setView(EditorView view);


  /**
   * Plays the animation.
   */
  public void play();


  /**
   * Mutates this view's "currently playing" field to indicate whether the preview should be paused
   * or playing.
   */
  public void playPause();


  /**
   * Get the speed of the animation.
   * @return speed
   */
  public double getSpeed();


  /**
   * Mutates this view's "rewind/forward" field to the opposite of its current value to indicate
   * whether the preview should be playing backwards.
   */
  public void toggleDirection();

  /**
   * Mutates this view's "loop" field to indicate the opposite of its current value: Either that the
   * preview should reset to the initial tick and continue playing from there once it reaches its
   * end or to stop once it reaches its end.
   */
  public void toggleLooping();

  /**
   * Mutates this view's "current tick" field to the initial frame depending on which direction the
   * animation is playing.
   */
  public void restart();

  /**
   * Increments this view's "current tick" field by 1 if it was playing forwards, decrements
   * otherwise. Pauses the animation if it was playing.
   */
  public void stepForward();

  /**
   * Decrements this view's "current tick" field by 1 if it was playing forwards, increments
   * otherwise. Pauses the animation if it was playing.
   */
  public void stepBack();

  /**
   * Mutates this view's speed field to reflect what the user enters, does nothing if the entered
   * speed is <= 0. (Throwing an exception here would be non-ideal)
   *
   * @param newSpeed the new ticks/second the animation should play at
   */
  public void setSpeed(double newSpeed);


  /**
   * Adds a keyframe to given Shape.
   * @param shapeTuple given shapeTuple
   * @param tick tick to add keyframe
   * @return added keyframe
   */
  public Keyframe addKeyFrame(ShapeTuple shapeTuple, int tick);

  /**
   * Delete keyframe from given shapeTuple.
   * @param shapeTuple given shapeTuple to delete keyframe from
   * @param keyframe keyframe to delete
   */
  public void deleteKeyFrame(ShapeTuple shapeTuple, Keyframe keyframe);

  /**
   * Add shape to animation.
   * @param shapeTuple shape to add to animation
   */
  public void addShape(ShapeTuple shapeTuple);

  /**
   * Remove a shape from the animation given string key.
   * @param key string representation of shape (shapeTuple.getKey())
   */
  public void removeShape(String key);

  /**
   * Updates keyframe at tick given updated shapeTuple and keyframe tick.
   * @param updatedShape links to getShapes ShapeTuple by key, also represents updated drawing
   * @param tickOfFrameToBeModified tick of keyframe to be updated
   */
  public void updateKeyframeOfAnimation(ShapeTuple updatedShape, int tickOfFrameToBeModified);

  /**
   * Returns read only model.
   * @return read only model.
   */
  public ExcelAnimatorModel getModel();


}
