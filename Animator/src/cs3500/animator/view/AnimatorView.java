package cs3500.animator.view;

import java.awt.event.ActionListener;
import java.util.List;

public interface AnimatorView {

  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   */
  void makeVisible();


  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly
   *
   * @param error
   */
  void showErrorMessage(String error);

  /**
   * Signal the view to draw itself
   */
  void refresh();

}
