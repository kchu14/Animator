package cs3500.animator.provider.view;

import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.ShapeTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.view.ExcelAnimatorView;
import cs3500.animator.view.VisualView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.awt.BorderLayout;

public class EditorView extends JFrame implements ExcelAnimatorView {

  private Double speed;
  private ExcelAnimatorModel model;
  private VisualView.VisualPanel panel;
  private InteractivePanel interactPanel;
  private int currentTick = 0;
  private boolean isPlaying = true;
  private boolean playForward = true;
  private boolean isLooping = true;
  private boolean isOn = true;

  /**
   * Consutrctor for an editor view.
   *
   * @param model model for view
   * @param speed speed to run preview at
   */
  public EditorView(ExcelAnimatorModel model, double speed) {
    super("excelAnimatorEditor");
    JScrollPane scrollPane;
    this.model = model;
    this.speed = speed;
    this.isOn = true;
    JPanel container = new JPanel();
    this.setPreferredSize(new Dimension(1000, 800));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.panel = new VisualView.VisualPanel();
    int[] bounds = this.model.getBounds();
    this.panel.setPreferredSize(new Dimension(bounds[0] + bounds[2], bounds[1] + bounds[3]));
    scrollPane = new JScrollPane(this.panel);
    //scrollPane.setBounds(0, 0, 3000, 800);
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

  @Override
  public void play() {
    List<Shape> shapes;
    while (this.isOn) {
      shapes = this.model.getAnimationState(currentTick);
      this.panel.setShapes(shapes);
      shapes = this.model.getAnimationState(currentTick);
      this.panel.setShapes(shapes);
      this.repaint();
      try {
        TimeUnit.MILLISECONDS.sleep(Math.round((1.0 / this.speed) * 1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (this.isPlaying) {
        if (this.playForward) {
          currentTick++;
        } else {
          currentTick--;
        }
      }

      if (currentTick == 0 || currentTick == this.model.getLastTick()) {
        if (!this.isLooping) {
          this.isPlaying = false;
        }
      }
      if (currentTick < 0 || currentTick > this.model.getLastTick()) {
        this.restart();
      }
    }
  }

  /**
   * Mutates this view's "currently playing" field to indicate whether the preview should be paused
   * or playing.
   */
  private void playPause() {
    boolean current = this.isPlaying;
    this.isPlaying = !current;
    if (!current) {
      this.interactPanel.playPause.setText("Pause");
    } else {
      this.interactPanel.playPause.setText("Play");
    }
  }

  /**
   * Mutates this view's "rewind/forward" field to the opposite of its current value to indicate
   * whether the preview should be playing backwards.
   */
  private void toggleDirection() {
    boolean current = this.playForward;
    this.playForward = !current;
  }

  /**
   * Mutates this view's "loop" field to indicate the opposite of its current value: Either that the
   * preview should reset to the initial tick and continue playing from there once it reaches its
   * end or to stop once it reaches its end.
   */
  private void toggleLooping() {
    boolean current = this.isLooping;
    this.isLooping = !current;
  }

  /**
   * Mutates this view's "current tick" field to the initial frame depending on which direction the
   * animation is playing.
   */
  private void restart() {
    if (playForward) {
      this.currentTick = 0;
    } else {
      this.currentTick = model.getLastTick();
    }
  }

  /**
   * Increments this view's "current tick" field by 1 if it was playing forwards, decrements
   * otherwise. Pauses the animation if it was playing.
   */
  private void stepForward() {
    this.isPlaying = false;
    this.interactPanel.playPause.setText("Play");
    if (playForward) {
      this.currentTick++;
    } else {
      this.currentTick--;
    }
  }

  /**
   * Decrements this view's "current tick" field by 1 if it was playing forwards, increments
   * otherwise. Pauses the animation if it was playing.
   */
  private void stepBack() {
    this.isPlaying = false;
    this.interactPanel.playPause.setText("Play");
    if (playForward) {
      this.currentTick--;
    } else {
      this.currentTick++;
    }
  }

  /**
   * Mutates this view's speed field to reflect what the user enters, does nothing if the entered
   * speed is <= 0. (Throwing an exception here would be non-ideal)
   *
   * @param newSpeed the new ticks/second the animation should play at
   */
  private void setSpeed(double newSpeed) {
    if (newSpeed > 0.0) {
      this.speed = newSpeed;
    }
    this.interactPanel.speedController.setText("" + this.speed);
  }


  private Keyframe addKeyFrame(ShapeTuple shapeTuple, int tick) {
    for (AnimationTuple animationTuple : this.model.getMotionsOfShape(shapeTuple.getKey())) {
      //System.out.println(animationTuple.getValue());
      if (tick > animationTuple.getValue() && tick < animationTuple.getKey().getLength()) {

        for (int i = 0; i < animationTuple.getKey().getFrames().size(); i++) {
          if (tick != animationTuple.getKey().getFrames().get(i).getValue()) {
            System.out.println(tick);
            return animationTuple.getKey().addFrame(tick - animationTuple.getValue(), i,
                    animationTuple.getValue(),
                shapeTuple);
          }
        }

        break;
      }

    }

    AnimationTuple temp_tuple = new AnimationTuple(new AnimationImp(shapeTuple), tick);
    this.model.addAnimation(temp_tuple);
    System.out.println("addKeyFrame tick: " + tick);
    return temp_tuple.getKey().addFrame(tick, -1, temp_tuple.getValue(), shapeTuple);
  }

  private void deleteKeyFrame(ShapeTuple shapeTuple, Keyframe keyframe) {

    //System.out.println(this.model.getMotionsOfShape(shapeTuple.getKey()));
    //System.out.println(keyframe);
    for (AnimationTuple animationTuple : this.model.getMotionsOfShape(shapeTuple.getKey())) {
      //System.out.println(animationTuple.getValue());
      if (keyframe.getValue() - animationTuple.getValue() >= 0) {
        animationTuple.getKey().deleteFrame(new Keyframe(keyframe.getKey(),
            keyframe.getValue() - animationTuple.getValue()));
        break;
      } else {
        //System.out.println("nodelete");
        //System.out.println(keyframe.getValue());
      }

    }

    //System.out.println(this.model.getMotionsOfShape(shapeTuple.getKey()).get(0).getKey()
    // .getFrames());

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
      speedController = new JTextField("" + speed, 10);
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
      //System.out.println(motions);

      //Traverse the list of motions then add the keyframes of each motion in model time
      for (AnimationTuple motion : motions) {
        for (Keyframe frame : motion.getKey().getFrames()) {
          //System.out.println(frame);
          shapesFromContainerModel
              .addElement(new Keyframe(frame.getKey(), frame.getValue() + motion.getValue()));
        }
      }
      //System.out.println(shapesFromContainerModel);

    }

    private void repopulateFrames(ShapeTuple shape) {
      shapesFromContainerModel.clear();
      List<AnimationTuple> motions = model.getMotionsOfShape(shape.getKey());
      //System.out.println(motions);

      //Traverse the list of motions then add the keyframes of each motion in model time
      for (AnimationTuple motion : motions) {
        for (Keyframe frame : motion.getKey().getFrames()) {
          //System.out.println(frame);
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
      }catch (NumberFormatException nfe) {
        newX = 0;
      }
      try {
        newY = Integer.parseInt(this.shapeY.getText());
      }catch (NumberFormatException nfe) {
        newY = 0;
      }
      try {
        newW = Integer.parseInt(this.shapeW.getText());
      }catch (NumberFormatException nfe) {
        newW = 10;
      }
      try {
        newH = Integer.parseInt(this.shapeH.getText());
      }catch (NumberFormatException nfe) {
        newH = 10;
      }
      try {
        newR = Integer.parseInt(this.shapeR.getText());
      }catch (NumberFormatException nfe) {
        newR = 100;
      }
      try {
        newG = Integer.parseInt(this.shapeG.getText());
      }catch (NumberFormatException nfe) {
        newG = 100;
      }
      try {
        newB = Integer.parseInt(this.shapeB.getText());
      }catch (NumberFormatException nfe) {
        newB = 100;
      }
      String action = e.getActionCommand();
      switch (action) {
        case "togglePlay":
          playPause();
          break;
        case "toggleBackward":
          toggleDirection();
          break;
        case "toggleLooping":
          toggleLooping();
          break;
        case "restart":
          restart();
          break;
        case "step+":
          stepForward();
          break;
        case "step-":
          stepBack();
          break;
        case "updateSpeed":
          setSpeed(Double.parseDouble(this.speedController.getText()));
          break;
        case "delete":
          deleteKeyFrame(this.shapesFromModel.getSelectedValue(),
              this.shapeFrames.getSelectedValue());
          this.shapesFromContainerModel.removeElement(this.shapeFrames.getSelectedValue());
          this.updateUI();
          break;
        case "addRect":
          try {
            model.addShape(
                new ShapeTuple(newShapeController.getText(), new Rectangle(newW, newH,
                        new Point(newX,
                        newY),
                    new Color(newR, newG, newB))));
          } catch (IllegalArgumentException except) {
            //nothing
          }
          this.repopulateShapes();
//          this.populateShapes();
//          shapesFromModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//          shapeContainer = new JScrollPane(shapesFromModel);
          newShapeController.setText("");
          //this.updateUI();
          break;
        case "addEllipse":
          try {
            model.addShape(
                new ShapeTuple(newShapeController.getText(), new Ellipse(newW, newH, new Point(newX,
                        newY),
                    new Color(newR, newG, newB))));
          } catch (IllegalArgumentException except) {
            //Do nothing
          }
          this.repopulateShapes();
//          shapesFromModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//          shapeContainer = new JScrollPane(shapesFromModel);
          newShapeController.setText("");
          //this.updateUI();
          break;
        case "deleteShape":
          try {
            model.removeShape(this.shapesFromModel.getSelectedValue().getKey());
          } catch (IllegalArgumentException except) {
            //Do Nothing
          }

          this.repopulateShapes();
          //this.updateUI();
          break;
        case "addKeyframe":
          Keyframe keyframe = addKeyFrame(this.shapesFromModel.getSelectedValue(),
              Integer.parseInt(this.keyframeController.getText()));
          System.out.println("Keyframe before list index: " + keyframe.getValue());

          int addIndex = 0;
          for (int i = 0; i < this.shapesFromContainerModel.getSize(); i++) {
            if (keyframe.getValue() < this.shapesFromContainerModel.get(i).getValue()) {
              addIndex = i;
              break;
            }
          }
          if (this.shapesFromContainerModel.getSize() > 0) {
            if (keyframe.getValue() > this.shapesFromContainerModel.get(this.shapesFromContainerModel.getSize() - 1).getValue()) {
              addIndex = this.shapesFromContainerModel.getSize();
            }
            if (addIndex == 0) {
              if (keyframe.getValue() > this.shapesFromContainerModel.get(0).getValue()) {
                addIndex = 1;
                System.out.println("set index to 1");
              }
            }
          }

          //this.repopulateShapes();
          this.shapesFromContainerModel.add(addIndex, keyframe);
          this.updateUI();
          break;
        case "updateKeyframe":
          ShapeTuple shapeToBeModified = this.shapesFromModel.getSelectedValue();
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
            System.out.println("tick of frame modified: " + tickOfFrameToBeModified);
            model.updateKeyframeOfAnimation(updatedShape, tickOfFrameToBeModified);
          } catch (IllegalArgumentException e2) {
          }
          //this.repopulateShapes();
          this.updateUI();
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
        List<AnimationTuple> motions = model.getMotionsOfShape(interactPanel.shapesFromModel.getSelectedValue()
                .getKey());
        //System.out.println(motions);
        for (AnimationTuple motion : motions) {
          for (Keyframe frame : motion.getKey().getFrames()) {
            //System.out.println(frame);
            interactPanel.shapesFromContainerModel.addElement(new Keyframe(frame.getKey(),
                    frame.getValue() + motion.getValue()));
          }
        }
        //interactPanel.keyFrameContainer = new JScrollPane(interactPanel.shapeFrames);
        //Remove the default keyframe list and replace it with the new one, happens to be at index 9
        //interactPanel.remove(9);
        //interactPanel.add(interactPanel.keyFrameContainer, 9);
        interactPanel.updateUI();
      }
      catch (NullPointerException npe) {

      }
      //Traverse the list of motions then add the keyframes of each motion in model time

    }
  }

  /**
   * The ListSelectionListener used to detect changes in selection in the list of keyframes of the
   * selected shape in the animation.
   */
  private class KeyFrameListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      Keyframe frame = interactPanel.shapeFrames.getSelectedValue();
      System.out.println(frame);
      System.out.println("Update Keyframe Fields");
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
