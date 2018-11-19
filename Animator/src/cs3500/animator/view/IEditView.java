package cs3500.animator.view;

import cs3500.animator.model.IShape;
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

  SimpleShape getCreatedShape();

  void setNameType(Map<String, String> nameType);

  String getSelectedShape();

  void showError(String message);
}
