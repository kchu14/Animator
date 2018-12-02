package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.IShape;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * This interface represents one of the different kinds of views an animator can have. This
 * interface contains the methods that an editable view implements.
 */
public interface IEditView extends AnimatorView {

  /**
   * Sets the action listeners for the buttons.
   *
   * @param e the given action listener.
   */
  void setButtonListeners(ActionListener e);

  /**
   * Sets whether or not the animation is over.
   *
   * @param b true if animation is over.
   */
  void setIsAnimationOver(Boolean b);

  /**
   * Restarts the animation.
   */
  void restart();

  /**
   * Rewinds the animation.
   */
  void rewind();

  /**
   * Speeds up the rate of the animation.
   */
  void fastforward();

  /**
   * Slows down the rate of the animation.
   */
  void slowDown();

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Returns the new keyframe that the user inputted using the gui and text fields.
   *
   * @return the new keyframe that the user inputted.
   */
  IMotion newMotion();

  /**
   * Sets the keyframes to be displayed and refreshes the gui menu.
   *
   * @param keyFrames the given keyframes to be displayed.
   */
  void setKeyFrames(Map<String, List<IMotion>> keyFrames);


  /**
   * Returns the keyframe that the user currently has selected in the gui.
   *
   * @return the keyframe that the user currently has selected.
   */
  IMotion getSelectedMotion();


  /**
   * Returns the modified keyframe that the user inputted. Differs from new keyframe in that the
   * user cannot change the time of the keyframe.
   *
   * @return the modified keyframe.
   */
  IMotion modifiedMotion();

  /**
   * Returns the new shape that the user has created.
   *
   * @return the shape that the user inputted into the gui.
   */
  IShape getCreatedShape();

  /**
   * Sets the name type map to the given map.
   *
   * @param nameType the given name and type map.
   */
  void setNameType(Map<String, String> nameType);

  /**
   * Returns the name of the shape that the user has selected in the gui.
   *
   * @return the shape that the user has selected.
   */
  String getSelectedShape();

  /**
   * Displays an error message on the editor view.
   *
   * @param message the given message to be displayed.
   */
  void showError(String message);

  /**
   * Sets the editor's panel based on the information inside of the read only model.
   *
   * @param model The read only version of a animator model implementation.
   */
  void setEPane(IReadOnlyModel model);

  /**
   * Sets the text fields of the editor panel to the given string.
   *
   * @param name the given shape name to be set.
   * @param value the given string to be set to.
   */
  void setTextFields(String name, String value);
}
