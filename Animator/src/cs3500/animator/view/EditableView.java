package cs3500.animator.view;

import cs3500.animator.model.IMotion;
import cs3500.animator.model.IReadOnlyModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.SimpleShape;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * This class represents implementations of an IEditView. This kind of view allows for the user to
 * make changes to the animation through the use of JButtons and JTextFields.
 */
public class EditableView extends JFrame implements IEditView {

  private IVisualGraphicsView graphicsView;
  private Map<String, List<IMotion>> keyFrames;
  private EditorPanel ePane;
  private IReadOnlyModel model;
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
  public void playAnimation(IReadOnlyModel model) {
    this.setTitle("Editor");
    this.setSize(1600, 900);
    // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout(1, 2, 3, 3));
    this.model = model;
    this.keyFrames = model.getKeyFrames();
    setEPane(model);
    this.animatorPanel = new AnimatorPanel();
    graphicsView.initiateTimer(model, animatorPanel);
    animatorPanel.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
    JScrollPane animatorScrollPane = new JScrollPane(animatorPanel);
    animatorScrollPane.setVerticalScrollBarPolicy(
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    animatorScrollPane
        .setHorizontalScrollBarPolicy(
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.add(animatorScrollPane);
    this.setVisible(true);
  }

  @Override
  public void setEPane(IReadOnlyModel model) {
    ePane = new EditorPanel(keyFrames, model.getNameType());
    this.add(ePane);
  }

  @Override
  public void setKeyFrames(Map<String, List<IMotion>> keyFrames) {
    this.keyFrames = keyFrames;
    ePane.setKeyFrames(keyFrames);
  }

  @Override
  public IMotion getSelectedMotion() {
    return ePane.getSelectedMotion();
  }

  @Override
  public IMotion modifiedMotion() {
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
  public IMotion newMotion() {
    return ePane.newMotion();
  }

  @Override
  public void setTextFields(String name, String s) {
    ePane.setTextFields(name, s);
  }
}
