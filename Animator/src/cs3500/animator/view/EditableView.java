package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Motion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class EditableView extends JFrame implements AnimatorView {

  IVisualGraphicsView graphicsView;
  private Map<String, List<Motion>> keyFrames;
  private JList<String> listOfStrings;
  private JList<String> listOfMotions;
  private EditorPanel ePane;

  public EditableView(IVisualGraphicsView graphicsView) {
    this.graphicsView = graphicsView;
  }

  @Override
  public void playAnimation(AnimatorModel model) {
    this.setKeyFrames(model.getKeyFrames());
    this.setTitle("Editor");
    this.setSize(1280, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new GridLayout(1,2,3,3)); //3,3 are gaps
    ePane = new EditorPanel(keyFrames);
    ePane.setPreferredSize(new Dimension(500, 500));
    ePane.setBackground(Color.BLACK);
    ePane.setLayout(new FlowLayout(FlowLayout.LEFT,3,3)); //Components aligned to left
    this.add(ePane);

    //this.pack();

    graphicsView.playAnimation(model);
    this.add(graphicsView.getAnimation());


    this.setVisible(true);

  }

  public void setKeyFrames(Map<String, List<Motion>> keyFrames) {
    this.keyFrames = keyFrames;
  }
}
