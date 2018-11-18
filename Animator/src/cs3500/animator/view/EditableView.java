package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.model.SimpleShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class EditableView extends JFrame implements IEditView {

  IVisualGraphicsView graphicsView;
  private Map<String, List<Motion>> keyFrames;
  private JList<String> listOfStrings;
  private JList<String> listOfMotions;
  private EditorPanel ePane;
  private ReadOnlyModel model;
  private AnimatorPanel animatorPanel;

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
    this.setLayout(new GridLayout(1,2,3,3)); //3,3 are gaps
    ePane = new EditorPanel(keyFrames, model.getNameType());
    this.add(ePane);

    //this.pack();

    this.animatorPanel = new AnimatorPanel();
    graphicsView.initiateTimer(model, animatorPanel);
    this.add(animatorPanel);


    this.setVisible(true);

  }

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
    return  ePane.modifiedMotion();
  }

  @Override
  public SimpleShape getSelectedShape() {
    return ePane.getSelectedShape();
  }

  @Override
  public void setNameType(Map<String, String> nameType) {
    ePane.setNameType(nameType);
  }


  public void setButtonListeners(ActionListener e) {
    ePane.setButtonListeners(e);
  }

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
