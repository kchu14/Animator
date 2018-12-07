package cs3500.animator.provider.view;

import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.Ellipse;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.Rectangle;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.ShapeTuple;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;


/**
 * Represents a Visual view with the added capability of animation playback controls and editing
 * individual shapes and keyframes.
 */
public class EditorView extends JFrame implements ExcelAnimatorView {

  private ExcelAnimatorModel model;
  private ExcelAnimatorController controller;
  private VisualView.VisualPanel panel;
  public InteractivePanel interactPanel;



  /**
   * Constructs an EditorView Instance.
   * @param controller controller for this view instance
   */
  public EditorView(ExcelAnimatorController controller) {
    super("excelAnimatorEditor");
    this.controller = controller;
    JScrollPane scrollPane;
    this.model = controller.getModel();
    JPanel container = new JPanel();
    this.setPreferredSize(new Dimension(1000, 800));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.panel = new VisualView.VisualPanel();
    int[] bounds = this.model.getBounds();
    this.panel.setPreferredSize(new Dimension(bounds[0] + bounds[2], bounds[1] + bounds[3]));
    scrollPane = new JScrollPane(this.panel);
    this.interactPanel = new InteractivePanel();
    JScrollPane ipScrollPane = new JScrollPane(this.interactPanel);
    ipScrollPane.setPreferredSize(new Dimension(1000,200 ));
    ipScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    container.add(ipScrollPane);
    container.add(this.panel);
    this.add(container, BorderLayout.CENTER);

    this.pack();
    this.setVisible(true);


  }

  public void setPlayPauseText(String text) {
    this.interactPanel.playPause.setText(text);
  }

  public void setSpeedText(Double speed) {
    this.interactPanel.speedController.setText("" + speed);
  }


  public void setShapes(List<Shape> shapes) {
    this.panel.setShapes(shapes);
  }

  public void play() {
    this.controller.play();
  }

  private void updateModel() {
    this.model = this.controller.getModel();
  }

  /**
   * Represents the panel containing all UI elements.
   */
  private class InteractivePanel extends JPanel implements ActionListener {

    private JButton playPause;
    private JCheckBox togglePlayDirection;
    private JCheckBox toggleLoop;
    private JButton restart;
    private JButton stepForward;
    private JButton stepBackward;
    private JTextField speedController;

    private JButton setSpeed;
    private JScrollPane shapeContainer;
    private JList<ShapeTuple> shapesFromModel;
    private DefaultListModel<ShapeTuple> shapesFromModelModel;
    private JScrollPane keyFrameContainer;
    private JList<Keyframe> shapeFrames;
    private DefaultListModel<Keyframe> shapesFromContainerModel;

    private JButton delete;
    private JButton addKeyframe;
    private JTextField keyframeController;


    private JTextField newShapeController;
    private JButton newRect;
    private JButton newEllipse;
    private JButton removeSelected;

    private JTextField shapeX;
    private JTextField shapeY;
    private JTextField shapeW;
    private JTextField shapeH;
    private JTextField shapeR;
    private JTextField shapeG;
    private JTextField shapeB;
    private JLabel labelX;
    private JLabel labelY;
    private JLabel labelW;
    private JLabel labelH;
    private JLabel labelR;
    private JLabel labelG;
    private JLabel labelB;
    private JButton updateKeyframe;




    private InteractivePanel() {
      playPause = new JButton("Pause");
      togglePlayDirection = new JCheckBox("Forwards", true);
      toggleLoop = new JCheckBox("Loop Animation", true);
      restart = new JButton("Restart Animation");
      stepForward = new JButton("Step Forward");
      stepBackward = new JButton("Step Back");
      speedController = new JTextField("" + controller.getSpeed(), 10);
      keyframeController = new JTextField("0", 3);
      newShapeController = new JTextField("", 10);
      setSpeed = new JButton("Set Speed");
      delete = new JButton("Delete keyframe");
      addKeyframe = new JButton("Add Keyframe at tick");
      newRect = new JButton("Add Rectangle");
      newEllipse = new JButton("Add Ellipse");
      removeSelected = new JButton("Remove Selected Shape");

      shapeX = new JTextField("", 3);
      shapeY = new JTextField("", 3);
      shapeW = new JTextField("", 3);
      shapeH = new JTextField("", 3);
      shapeR = new JTextField("", 3);
      shapeG = new JTextField("", 3);
      shapeB = new JTextField("", 3);
      labelX = new JLabel("X:");
      labelY = new JLabel("Y:");
      labelW = new JLabel("W:");
      labelH = new JLabel("H:");
      labelR = new JLabel("R:");
      labelG = new JLabel("G:");
      labelB = new JLabel("B:");
      updateKeyframe = new JButton("Update Keyframe");

      populateShapes();
      shapesFromModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      shapeContainer = new JScrollPane(shapesFromModel);
      //shapeFrames = new JList<Keyframe>();
      this.shapeFrames = new JList<Keyframe>(shapesFromContainerModel);
      shapeFrames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      keyFrameContainer = new JScrollPane(shapeFrames);

      speedController.setToolTipText("Enter a new value for speed here.");
      setSpeed.setToolTipText(
          "Once you've entered a valid speed value, hit this button to update the speed.");
      shapeContainer.setToolTipText("This is a list of the shapes contained in the animation.");
      keyFrameContainer.setToolTipText("This is a list of the keyframes of the selected shape.");
      newShapeController.setToolTipText("Enter a name for new a new shape here");
      setSpeed.setToolTipText(
          "Once you've selected and entered valid values for a keyframe, "
              + "hit this button to update it.");

      playPause.setActionCommand("togglePlay");
      togglePlayDirection.setActionCommand("toggleBackward");
      toggleLoop.setActionCommand("toggleLooping");
      restart.setActionCommand("restart");
      stepForward.setActionCommand("step+");
      stepBackward.setActionCommand("step-");
      setSpeed.setActionCommand("updateSpeed");
      delete.setActionCommand("delete");
      addKeyframe.setActionCommand("addKeyframe");
      newRect.setActionCommand("addRect");
      newEllipse.setActionCommand("addEllipse");
      updateKeyframe.setActionCommand("updateKeyframe");
      removeSelected.setActionCommand("deleteShape");

      playPause.addActionListener(this);
      togglePlayDirection.addActionListener(this);
      toggleLoop.addActionListener(this);
      restart.addActionListener(this);
      stepForward.addActionListener(this);
      stepBackward.addActionListener(this);
      setSpeed.addActionListener(this);
      shapesFromModel.addListSelectionListener(new ShapeListener());
      shapeFrames.addListSelectionListener(new KeyFrameListener());
      delete.addActionListener(this);
      addKeyframe.addActionListener(this);
      newRect.addActionListener(this);
      newEllipse.addActionListener(this);
      updateKeyframe.addActionListener(this);
      removeSelected.addActionListener(this);

      this.add(playPause);
      this.add(stepForward);
      this.add(stepBackward);
      this.add(restart);
      this.add(togglePlayDirection);
      this.add(toggleLoop);
      this.add(speedController);
      this.add(setSpeed);
      this.add(shapeContainer);
      this.add(keyFrameContainer);
      this.add(delete);
      this.add(addKeyframe);
      this.add(keyframeController);
      this.add(removeSelected);
      this.add(newShapeController);
      this.add(newRect);
      this.add(newEllipse);
      this.add(labelX);
      this.add(shapeX);
      this.add(labelY);
      this.add(shapeY);
      this.add(labelW);
      this.add(shapeW);
      this.add(labelH);
      this.add(shapeH);
      this.add(labelR);
      this.add(shapeR);
      this.add(labelG);
      this.add(shapeG);
      this.add(labelB);
      this.add(shapeB);
      this.add(updateKeyframe);

      this.setAlignmentX(LEFT_ALIGNMENT);

    }


    private void populateShapes() {
      shapesFromModelModel = new DefaultListModel<ShapeTuple>();
      for (ShapeTuple shape : model.getShapes()) {
        shapesFromModelModel.addElement(shape);
      }
      this.shapesFromModel = new JList<ShapeTuple>(shapesFromModelModel);
      this.shapesFromModel.setSelectedIndex(0);
      this.populateFrames(this.shapesFromModel.getSelectedValue());
      this.updateUI();

    }

    private void repopulateShapes() {
      shapesFromModelModel.clear();
      for (ShapeTuple shape : model.getShapes()) {
        shapesFromModelModel.addElement(shape);
        this.shapesFromModel.setSelectedValue(shape, true);
      }
      this.repopulateFrames(this.shapesFromModel.getSelectedValue());
      this.updateUI();
    }

    private void populateFrames(ShapeTuple shape) {
      shapesFromContainerModel = new DefaultListModel<Keyframe>();
      List<AnimationTuple> motions = model.getMotionsOfShape(shape.getKey());

      //Traverse the list of motions then add the keyframes of each motion in model time
      for (AnimationTuple motion : motions) {
        for (Keyframe frame : motion.getKey().getFrames()) {
          shapesFromContainerModel
              .addElement(new Keyframe(frame.getKey(), frame.getValue() + motion.getValue()));
        }
      }

    }

    private void repopulateFrames(ShapeTuple shape) {
      shapesFromContainerModel.clear();
      List<AnimationTuple> motions = model.getMotionsOfShape(shape.getKey());

      //Traverse the list of motions then add the keyframes of each motion in model time
      for (AnimationTuple motion : motions) {
        for (Keyframe frame : motion.getKey().getFrames()) {
          shapesFromContainerModel
              .addElement(new Keyframe(frame.getKey(), frame.getValue() + motion.getValue()));
        }
      }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      Integer newX;
      Integer newY;
      Integer newW;
      Integer newH;
      Integer newR;
      Integer newG;
      Integer newB;
      try {
        newX = Integer.parseInt(this.shapeX.getText());
      } catch (NumberFormatException nfe) {
        newX = 0;
      }
      try {
        newY = Integer.parseInt(this.shapeY.getText());
      } catch (NumberFormatException nfe) {
        newY = 0;
      }
      try {
        newW = Integer.parseInt(this.shapeW.getText());
      } catch (NumberFormatException nfe) {
        newW = 10;
      }
      try {
        newH = Integer.parseInt(this.shapeH.getText());
      } catch (NumberFormatException nfe) {
        newH = 10;
      }
      try {
        newR = Integer.parseInt(this.shapeR.getText());
      } catch (NumberFormatException nfe) {
        newR = 100;
      }
      try {
        newG = Integer.parseInt(this.shapeG.getText());
      } catch (NumberFormatException nfe) {
        newG = 100;
      }
      try {
        newB = Integer.parseInt(this.shapeB.getText());
      } catch (NumberFormatException nfe) {
        newB = 100;
      }
      String action = e.getActionCommand();
      switch (action) {
        case "togglePlay":
          controller.playPause();
          updateModel();
          break;
        case "toggleBackward":
          controller.toggleDirection();
          updateModel();
          break;
        case "toggleLooping":
          controller.toggleLooping();
          updateModel();
          break;
        case "restart":
          controller.restart();
          updateModel();
          break;
        case "step+":
          controller.stepForward();
          updateModel();
          break;
        case "step-":
          controller.stepBack();
          updateModel();
          break;
        case "updateSpeed":
          controller.setSpeed(Double.parseDouble(this.speedController.getText()));
          updateModel();
          break;
        case "delete":
          controller.deleteKeyFrame(this.shapesFromModel.getSelectedValue(),
              this.shapeFrames.getSelectedValue());
          updateModel();
          this.repopulateFrames(this.shapeFrames.getSelectedValue().getKey());
          this.updateUI();
          break;
        case "addRect":
          try {
            controller.addShape(
                new ShapeTuple(newShapeController.getText(), new Rectangle(newW, newH,
                    new Point(newX,
                        newY),
                    new Color(newR, newG, newB))));
          } catch (IllegalArgumentException except) {
            //nothing
          }
          updateModel();
          this.repopulateShapes();
          newShapeController.setText("");
          break;
        case "addEllipse":
          try {
            controller.addShape(
                new ShapeTuple(newShapeController.getText(), new Ellipse(newW, newH, new Point(newX,
                    newY),
                    new Color(newR, newG, newB))));
          } catch (IllegalArgumentException except) {
            //Do nothing
          }
          updateModel();
          newShapeController.setText("");
          this.repopulateShapes();
          break;
        case "deleteShape":
          try {
            controller.removeShape(this.shapesFromModel.getSelectedValue().getKey());
          } catch (IllegalArgumentException except) {
            //Do nothing no such shape
          }
          updateModel();
          this.repopulateShapes();
          break;
        case "addKeyframe":
          int keyframe_n = Integer.parseInt(this.keyframeController.getText());
          if (keyframe_n > 0) {
            Keyframe keyframe = controller.addKeyFrame(this.shapesFromModel.getSelectedValue(),
                    keyframe_n);
          }


          updateModel();
          this.repopulateFrames(this.shapesFromModel.getSelectedValue());
          this.updateUI();
          break;
        case "updateKeyframe":
          ShapeTuple shapeToBeModified = this.shapesFromModel.getSelectedValue();
          if (! this.shapeFrames.isSelectionEmpty()) {
            int tickOfFrameToBeModified = this.shapeFrames.getSelectedValue().getValue();
            Double newX_d = Double.parseDouble(this.shapeX.getText());
            Double newY_d = Double.parseDouble(this.shapeY.getText());
            Double newW_d = Double.parseDouble(this.shapeW.getText());
            Double newH_d = Double.parseDouble(this.shapeH.getText());
            Double newR_d = Double.parseDouble(this.shapeR.getText());
            Double newG_d = Double.parseDouble(this.shapeG.getText());
            Double newB_d = Double.parseDouble(this.shapeB.getText());
            ShapeTuple updatedShape = new ShapeTuple(shapeToBeModified.getKey(),
                    shapeToBeModified.getValue()
                            .remake(newW_d.intValue(), newH_d.intValue(), newX_d.intValue(),
                                    newY_d.intValue(),
                                    newR_d.intValue(), newG_d.intValue(), newB_d.intValue()));
            try {
              //System.out.println("tick of frame modified: " + tickOfFrameToBeModified);
              controller.updateKeyframeOfAnimation(updatedShape, tickOfFrameToBeModified);
            } catch (IllegalArgumentException e2) {
              //Do Nothing no such keyframe
            }
            updateModel();
            this.updateUI();
            
          }
          break;
        default:
          //Do nothing
      }

    }
  }

  /**
   * The ListSelectionListener used to detect changes in selection in the list of shapes in the
   * animation.
   */
  private class ShapeListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {

      try {
        interactPanel.shapesFromContainerModel.clear();
        List<AnimationTuple> motions = model.getMotionsOfShape(interactPanel.shapesFromModel
            .getSelectedValue().getKey());
        for (AnimationTuple motion : motions) {
          for (Keyframe frame : motion.getKey().getFrames()) {
            interactPanel.shapesFromContainerModel.addElement(new Keyframe(frame.getKey(),
                frame.getValue() + motion.getValue()));
          }
        }

        interactPanel.updateUI();
      }
      catch (NullPointerException npe) {
        //No such shape
      }

    }
  }

  /**
   * The ListSelectionListener used to detect changes in selection in the list of keyframes of the
   * selected shape in the animation.
   */
  private class KeyFrameListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (! interactPanel.shapeFrames.isSelectionEmpty()) {
        Keyframe frame = interactPanel.shapeFrames.getSelectedValue();
        interactPanel.shapeX.setText(frame.getKey().getValue().getLocation().getX() + "");
        interactPanel.shapeY.setText(frame.getKey().getValue().getLocation().getY() + "");
        interactPanel.shapeW.setText(frame.getKey().getValue().getWidth() + "");
        interactPanel.shapeH.setText(frame.getKey().getValue().getHeight() + "");
        interactPanel.shapeR.setText(frame.getKey().getValue().getColor().getRed() + "");
        interactPanel.shapeG.setText(frame.getKey().getValue().getColor().getGreen() + "");
        interactPanel.shapeB.setText(frame.getKey().getValue().getColor().getBlue() + "");
        interactPanel.updateUI();
      }

    }
  }

}
