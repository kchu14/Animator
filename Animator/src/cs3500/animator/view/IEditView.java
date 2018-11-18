package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.SimpleShape;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public interface IEditView extends AnimatorView {
  void setButtonListeners(ActionListener e);
  void setIsAnimationOver(Boolean b);

  void restart();

  void rewind();

  void fastforward();

  void slowDown();

  void pause();

  Motion newMotion();

  void setKeyFrames(Map<String, List<Motion>> keyFrames);

  Motion getSelectedMotion();


  Motion modifiedMotion();

  SimpleShape getSelectedShape();

  void setNameType(Map<String, String> nameType);
}
