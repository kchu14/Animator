package cs3500.animator.view;

import java.io.IOException;


public interface ExcelAnimatorView {

  /**
   * Draws each frame of animation in the model stored in this view.
   *
   * @throws IOException if output couldn't be appended to
   */
  public void play() throws IOException;


}
