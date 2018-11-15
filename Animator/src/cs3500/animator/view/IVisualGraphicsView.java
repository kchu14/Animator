package cs3500.animator.view;


import cs3500.animator.model.AnimatorModel;
import javax.swing.JPanel;

/**
 * This interface represents the operations that can be performed on a visual graphics view.
 * These actions include playing the animation and showing error messages.
 */
public interface IVisualGraphicsView extends AnimatorView{



  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   */
  void showErrorMessage(String error);

  JPanel getAnimation();

  void initiateTimer(AnimatorModel model, AnimatorPanel animatorPanel);

}
