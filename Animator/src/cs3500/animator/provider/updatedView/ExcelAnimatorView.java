package cs3500.animator.provider.updatedView;

import java.io.IOException;

/**
 * Represents the view interface for an animator. The only common feature across all views is to
 * play an animation corresponding to the data stored in its model field. Implementations can add
 * additional behavior.
 */
public interface ExcelAnimatorView {

  /**
   * Draws each frame of animation in the model stored in this view.
   *
   * @throws IOException if output couldn't be appended to
   */
  public void play() throws IOException;


}
