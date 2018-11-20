package cs3500.animator.view;


/**
 * This interface represents the operations that can be performed on a SvgView. These actions
 * include playing the animation and showing error messages.
 */
public interface ISvgView extends AnimatorView {


  /**
   * Gives the user a copy of the file output as a string.
   *
   * @return file output as a string
   */
  String getOutPutText();
}
