package cs3500.animator.view;

import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.SimpleShape;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class represents implementations of an IEditView. This kind of view allows for the user to
 * make changes to the animation through the use of JButtons and JTextFields.
 */
public class EditableView extends JFrame implements IEditView {

  private IVisualGraphicsView graphicsView;
  private Map<String, List<Motion>> keyFrames;
  private EditorPanel ePane;
  private ReadOnlyModel model;
  private AnimatorPanel animatorPanel;

  /**
   * Constructs an editable view.
   *
   * @param graphicsView the given graphics view that is edited.
   */
  public EditableView(IVisualGraphicsView graphicsView) {
    this.graphicsView = graphicsView;
  }

  @Override
  public void playAnimation(ReadOnlyModel model) {
    this.model = model;
    this.keyFrames = model.getKeyFrames();
    this.setTitle("Editor");
    this.setSize(1600, 900);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout(1, 2, 3, 3));
    ePane = new EditorPanel(keyFrames, model.getNameType());
    this.add(ePane);
    this.animatorPanel = new AnimatorPanel();
    graphicsView.initiateTimer(model, animatorPanel);
    this.add(animatorPanel);

    this.setVisible(true);

  }

  @Override
  public void setKeyFrames(Map<String, List<Motion>> keyFrames) {
    this.keyFrames = keyFrames;
    ePane.setKeyFrames(keyFrames);
  }

  @Override
  public Motion getSelectedMotion() {
    return ePane.getSelectedMotion();
  }

  @Override
  public Motion modifiedMotion() {
    return ePane.modifiedMotion();
  }

  @Override
  public SimpleShape getCreatedShape() {
    return ePane.getCreatedShape();
  }

  @Override
  public void setNameType(Map<String, String> nameType) {
    ePane.setNameType(nameType);
  }

  @Override
  public String getSelectedShape() {
    return ePane.getSelectedShape();
  }

  @Override
  public void showError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setButtonListeners(ActionListener e) {
    ePane.setButtonListeners(e);
  }

  @Override
  public void setIsAnimationOver(Boolean b) {
    graphicsView.setIsAnimationOver(b);
  }

  @Override
  public void restart() {
    this.remove(animatorPanel);
    this.animatorPanel = new AnimatorPanel();
    graphicsView.initiateTimer(model, animatorPanel);
    this.add(animatorPanel);
  }

  @Override
  public void rewind() {
    graphicsView.rewind(model);
  }

  @Override
  public void fastforward() {
    graphicsView.fastforward();
  }

  @Override
  public void slowDown() {
    graphicsView.slowDown();
  }

  @Override
  public void pause() {
    graphicsView.pause();
  }

  @Override
  public Motion newMotion() {
    return ePane.newMotion();
  }
}
