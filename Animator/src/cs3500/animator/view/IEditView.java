package cs3500.animator.view;

import cs3500.animator.model.Motion;
import java.awt.event.ActionListener;

public interface IEditView extends AnimatorView {
  void setButtonListeners(ActionListener e);
  void setIsAnimationOver(Boolean b);

  void restart();

  void rewind();

  void fastforward();

  void slowDown();

  void pause();

  Motion newMotion();
}
