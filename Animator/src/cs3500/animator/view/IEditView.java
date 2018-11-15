package cs3500.animator.view;

import java.awt.event.ActionListener;

public interface IEditView extends AnimatorView {
  void setButtonListeners(ActionListener e);
  void setIsAnimationOver(Boolean b);

  void restart();

  void rewind();

  void fastforward();
}
