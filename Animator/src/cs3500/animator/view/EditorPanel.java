package cs3500.animator.view;


import cs3500.animator.controller.EditController;
import cs3500.animator.model.IMotion;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.SimpleShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class represents the editor panel that is contained inside of the editor view. This panel
 * contains all the controls and functionality that the user is allowed to use to modify the
 * animation.
 */
public class EditorPanel extends JPanel implements ItemListener,
    ListSelectionListener {


  private JPanel motionPanel;
  private JPanel shapesAndMotions;
  private Map<String, List<IMotion>> keyFrames;
  private JList<String> listOfShapes;
  private JList<String> listOfMotions;
  private JTextField redText;
  private JTextField greenText;
  private JTextField blueText;
  private JTextField widthText;
  private JTextField heightText;
  private JTextField xText;
  private JTextField yText;
  private JRadioButton rectangleButton;
  private JRadioButton ellipseButton;
  private JTextField time;
  private JTextField shapeName;
  private List<JButton> listOfButtons;
  private IMotion selectedMotion;
  private JPanel shapeNamesPanel;
  private Map<String, String> nameType;
  private JScrollPane losScroll;
  private JSlider scrubber;


  /**
   * Constructs an editor panel to be used in the editable view.
   *
   * @param keyFrames the keyframes to be displayed in the panel.
   * @param nameType the name and type of the shape to be displayed in the panel.
   */

  protected EditorPanel(Map<String, List<IMotion>> keyFrames, Map<String, String> nameType,
      int firstTick, int lastTick) {
    super();
    // Sets layout
    this.nameType = nameType;
    this.setLayout(new BorderLayout());
    this.keyFrames = keyFrames;

    // Sets up shapes and motions JLists
    this.listOfButtons = new ArrayList<>();
    shapesAndMotions = new JPanel();
    shapesAndMotions.setPreferredSize(new Dimension(640, 350));
    shapesAndMotions.setLayout(new BorderLayout());
    shapeNamesPanel = new JPanel();
    shapeNamesPanel.setBorder(BorderFactory.createTitledBorder("Shape Name"));
    shapeNamesPanel.setLayout(new BorderLayout());
    this.displayShapes();
    motionPanel = new JPanel();
    motionPanel.setLayout(new BorderLayout());
    motionPanel.setBorder(BorderFactory.createTitledBorder("Keyframes"));
    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);

    // Buttons for changing the animation
    JButton addMotionButton = new JButton("Add keyframe");
    listOfButtons.add(addMotionButton);
    JButton removeMotionButton = new JButton("Remove keyframe");
    listOfButtons.add(removeMotionButton);
    JButton addShapeButton = new JButton("Add shape");
    listOfButtons.add(addShapeButton);
    JButton removeShapeButton = new JButton("Remove shape");
    listOfButtons.add(removeShapeButton);
    JButton modifyMotionButton = new JButton("Modify keyframe");
    listOfButtons.add(modifyMotionButton);
    JPanel removeButtons = new JPanel();
    removeButtons.setLayout(new BoxLayout(removeButtons, BoxLayout.Y_AXIS));

    removeButtons.add(removeShapeButton);
    removeButtons.add(removeMotionButton);
    shapeNamesPanel.add(removeButtons, BorderLayout.SOUTH);
    this.add(shapesAndMotions, BorderLayout.NORTH);

    // Sets up shape attributes fields
    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel shapeAttributes = new JPanel();
    shapeAttributes.setLayout(new FlowLayout());
    time = new JTextField(4);
    shapeAttributes.add(time);
    JTextArea timeButton = new JTextArea("Time");
    shapeAttributes.add(timeButton);

    // Colors of the shape
    JPanel colors = new JPanel();
    colors.setLayout(new BoxLayout(colors, BoxLayout.Y_AXIS));
    JPanel redTextButton = new JPanel(new FlowLayout());
    redText = new JTextField(2);
    redTextButton.add(redText);
    JTextArea redButton = new JTextArea("Red");
    redTextButton.add(redButton);
    colors.add(redTextButton);
    JPanel greenTextButton = new JPanel(new FlowLayout());
    greenText = new JTextField(2);
    greenTextButton.add(greenText);
    JTextArea greenButton = new JTextArea("Green");
    greenTextButton.add(greenButton);
    colors.add(greenTextButton);
    JPanel blueTextButton = new JPanel(new FlowLayout());
    blueText = new JTextField(2);
    blueTextButton.add(blueText);
    JTextArea blueButton = new JTextArea("Blue");
    blueTextButton.add(blueButton);
    colors.add(blueTextButton);
    shapeAttributes.add(colors);

    // Size of the shape
    JPanel sizePanel = new JPanel();
    sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.Y_AXIS));
    JPanel widthTextButton = new JPanel(new FlowLayout());
    widthText = new JTextField(2);
    widthTextButton.add(widthText);
    JTextArea widthButton = new JTextArea("Width");
    widthTextButton.add(widthButton);
    sizePanel.add(widthTextButton);
    JPanel heightTextButton = new JPanel(new FlowLayout());
    heightText = new JTextField(2);
    heightTextButton.add(heightText);
    JTextArea heightButton = new JTextArea("Height");
    heightTextButton.add(heightButton);
    sizePanel.add(heightTextButton);
    shapeAttributes.add(sizePanel);

    // Position of the shape
    JPanel positionPanel = new JPanel();
    positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.Y_AXIS));
    JPanel xTextButton = new JPanel(new FlowLayout());
    xText = new JTextField(3);
    xTextButton.add(xText);
    JTextArea xButton = new JTextArea("X");
    xTextButton.add(xButton);
    positionPanel.add(xTextButton);
    JPanel yTextButton = new JPanel(new FlowLayout());
    yText = new JTextField(3);
    yTextButton.add(yText);
    JTextArea yButton = new JTextArea("Y");
    yTextButton.add(yButton);
    positionPanel.add(yTextButton);
    shapeAttributes.add(positionPanel);

    shapeAttributes.add(modifyMotionButton);
    shapeAttributes.add(addMotionButton);

    tabbedPane.addTab("Modify / Add Keyframe", shapeAttributes);

    // The shape type buttons
    JPanel declareShapePanel = new JPanel();
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Shape Type"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    ButtonGroup rGroup1 = new ButtonGroup();
    rectangleButton = new JRadioButton("Rectangle");
    ellipseButton = new JRadioButton("Ellipse");
    rGroup1.add(rectangleButton);
    rGroup1.add(ellipseButton);
    radioPanel.add(rectangleButton);
    radioPanel.add(ellipseButton);
    declareShapePanel.add(radioPanel);

    // Shape name text field
    shapeName = new JTextField(4);
    declareShapePanel.add(shapeName);
    JTextArea shapeNameButton = new JTextArea("Shape Name");
    declareShapePanel.add(shapeNameButton);
    declareShapePanel.add(addShapeButton);
    tabbedPane.addTab("Add Shape", declareShapePanel);

    JPanel middleButtonsPanel = new JPanel(new BorderLayout());
    middleButtonsPanel.add(tabbedPane, BorderLayout.NORTH);

    // Animation modifying buttons
    JPanel newMotionShapeButtonsPanel = new JPanel(new FlowLayout());
    middleButtonsPanel.add(newMotionShapeButtonsPanel, BorderLayout.SOUTH);
    this.add(middleButtonsPanel, BorderLayout.CENTER);

    JPanel playBackCommandsandSlider = new JPanel();
    playBackCommandsandSlider.setLayout(new BoxLayout(playBackCommandsandSlider, BoxLayout.Y_AXIS));

    // Video control buttons
    JPanel playBackCommands = new JPanel();
    playBackCommands.setLayout(new FlowLayout());
    JButton restart = new JButton("Restart");
    listOfButtons.add(restart);
    JButton rewind = new JButton("Reverse");
    listOfButtons.add(rewind);
    JButton pause = new JButton("||");
    listOfButtons.add(pause);
    JButton forwards = new JButton("Fastforward");
    listOfButtons.add(forwards);
    JButton slowDown = new JButton("Slow down");
    listOfButtons.add(slowDown);
    JButton loop = new JButton("Toggle Loop");
    listOfButtons.add(loop);
    playBackCommands.add(rewind);
    playBackCommands.add(pause);
    playBackCommands.add(forwards);
    playBackCommands.add(slowDown);
    playBackCommands.add(restart);
    playBackCommands.add(loop);
    scrubber = new JSlider(JSlider.HORIZONTAL,
        firstTick, lastTick, firstTick);
    scrubber.setPreferredSize(new Dimension(600, 100));
    JPanel scrubberPanel = new JPanel();
    scrubberPanel.add(scrubber);
    playBackCommandsandSlider.add(scrubberPanel);
    playBackCommandsandSlider.add(playBackCommands);

    this.add(playBackCommandsandSlider, BorderLayout.SOUTH);


  }

  /**
   * Displays the given shapes available for modification on the panel.
   */
  private void displayShapes() {
    if (shapeNamesPanel.getComponents().length >= 2) {
      shapeNamesPanel.remove(losScroll);
    } else {
      shapeNamesPanel.removeAll();
    }
    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    for (Entry<String, String> shape : nameType.entrySet()) {
      dataForListOfStrings.addElement(shape.getKey());
    }

    listOfShapes = new JList<>(dataForListOfStrings);
    listOfShapes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfShapes.addListSelectionListener(this);
    losScroll = new JScrollPane(listOfShapes);
    losScroll.setPreferredSize(new Dimension(100, 200));
    shapeNamesPanel.add(losScroll, BorderLayout.NORTH);
    shapesAndMotions.add(shapeNamesPanel, BorderLayout.WEST);

    this.updateUI();
  }

  /**
   * Displays the motions able to be modified for a given shape on the panel.
   *
   * @param shapeName the given shape for which the desired motions are displayed.
   */
  private void displayMotions(String shapeName) {
    motionPanel.removeAll();
    JPanel empty = new JPanel();
    empty.setPreferredSize(new Dimension(300, 350));
    DefaultListModel<String> dataForListOfMotions = new DefaultListModel<>();
    if (keyFrames.get(shapeName) == null) {
      motionPanel.add(new JScrollPane(empty));
      shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
      return;
    }
    List<IMotion> lom = keyFrames.get(shapeName);
    Collections.sort(lom);
    for (IMotion m : lom) {
      dataForListOfMotions.addElement("" + m.getStartTime());
    }

    listOfMotions = new JList<>(dataForListOfMotions);
    listOfMotions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfMotions.addListSelectionListener(new MotionListener());
    listOfMotions.setPreferredSize(new Dimension(300, 350));
    motionPanel.add(new JScrollPane(listOfMotions));
    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
    selectedMotion = null;
    this.updateUI();

  }

  /**
   * Sets the name type map to the given map.
   *
   * @param nameType the given name and type map.
   */
  protected void setNameType(Map<String, String> nameType) {
    this.nameType = nameType;
    displayShapes();
  }


  /**
   * Sets all of our buttons to have action listeners.
   *
   * @param e the given action listener.
   */
  protected void setButtonListeners(ActionListener e) {
    for (JButton b : listOfButtons) {
      b.addActionListener(e);
      b.setActionCommand(b.getText());
    }
  }

  /**
   * Returns the new keyframe that the user inputted using the gui and text fields.
   *
   * @return the new keyframe that the user inputted.
   */
  protected IMotion newMotion() {
    Color c = new Color(Math.max(0, Math.min(Integer.parseInt(redText.getText()), 255)),
        Math.max(0, Math.min(Integer.parseInt(greenText.getText()), 255)),
        Math.max(0, Math.min(Integer.parseInt(blueText.getText()), 255)));
    return new Motion(listOfShapes.getSelectedValue(),
        nameType.get(listOfShapes.getSelectedValue()),
        Integer.parseInt(this.time.getText()),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c, Integer.parseInt(this.time.getText()),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c);
  }

  /**
   * Returns the modified keyframe that the user inputted. Differs from new keyframe in that the
   * user cannot change the time of the keyframe.
   *
   * @return the modified keyframe.
   */
  protected IMotion modifiedMotion() {
    if (selectedMotion.getStartTime() != Integer.parseInt(time.getText())) {
      throw new IllegalArgumentException("times must be equal");
    }
    Color c = new Color(Math.max(0, Math.min(Integer.parseInt(redText.getText()), 255)),
        Math.max(0, Math.min(Integer.parseInt(greenText.getText()), 255)),
        Math.max(0, Math.min(Integer.parseInt(blueText.getText()), 255)));
    return new Motion(listOfShapes.getSelectedValue(),
        keyFrames.get(listOfShapes.getSelectedValue()).get(0).getType(),
        selectedMotion.getStartTime(),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c, selectedMotion.getEndTime(),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c);
  }

  /**
   * Returns the keyframe that the user currently has selected in the gui.
   *
   * @return the keyframe that the user currently has selected.
   */
  protected IMotion getSelectedMotion() {
    return selectedMotion;
  }

  /**
   * Returns the new shape that the user has created.
   *
   * @return the shape that the user inputted into the gui.
   */
  protected IShape getCreatedShape() {
    String selectedType = "ellipse";
    if (rectangleButton.isSelected()) {
      selectedType = "rectangle";
    }
    if (shapeName.getText().equals("")) {
      throw new IllegalArgumentException("must have name");
    }
    return new SimpleShape(shapeName.getText(), selectedType);
  }

  /**
   * Returns the name of the shape that the user has selected in the gui.
   *
   * @return the shape that the user has selected.
   */
  protected String getSelectedShape() {
    if (listOfShapes.getSelectedValue() == null) {
      throw new IllegalArgumentException("need to select shape");
    }

    return listOfShapes.getSelectedValue();
  }

  /**
   * Sets the first and last tick for this panel.
   *
   * @param firstTick the first tick of the animation.
   * @param lastTick the last tick of the animation.
   */
  protected void setTicks(int firstTick, int lastTick) {
    scrubber.setMaximum(lastTick);
    scrubber.setMinimum(firstTick);

  }

  /**
   * Sets the current tick for this panel.
   *
   * @param tick the current tick of the animation.
   */
  protected void setCurrentTick(int tick) {
    scrubber.setValue(tick);
  }

  /**
   * Sets the changeListener for our panel's slider.
   *
   * @param editController the panel's changeListener.
   */
  protected void setSliderListener(EditController editController) {
    scrubber.addChangeListener(editController);
  }

  /**
   * This class represents implementation of a list selection listener. It is used to identify the
   * clicks and values changed on our Jlist panels.
   */
  private class MotionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {

      List<IMotion> lom = keyFrames.get(listOfShapes.getSelectedValue());
      for (IMotion m : lom) {
        if (m.getStartTime() == Integer.parseInt(listOfMotions.getSelectedValue())) {
          String[] strArr = m.getTextResult().split(" ");
          selectedMotion = m;
          redText.setText(strArr[7]);
          greenText.setText(strArr[8]);
          blueText.setText(strArr[9]);
          widthText.setText(strArr[5]);
          heightText.setText(strArr[6]);
          xText.setText(strArr[3]);
          yText.setText(strArr[4]);
          time.setText(strArr[2]);
          shapeName.setText(strArr[1]);

          if (m.getType().equals("rectangle")) {
            rectangleButton.setSelected(true);
          } else {
            ellipseButton.setSelected(true);
          }

        }
      }
    }
  }


  @Override
  public void itemStateChanged(ItemEvent e) {
    return;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    displayMotions(listOfShapes.getSelectedValue());
  }

  /**
   * Sets the keyframes to be displayed and refreshes the gui menu.
   *
   * @param keyFrames the given keyframes to be displayed.
   */
  protected void setKeyFrames(Map<String, List<IMotion>> keyFrames) {
    this.keyFrames = keyFrames;
    this.displayMotions(listOfShapes.getSelectedValue());
  }

  /**
   * Sets the text fields of the editor panel to the given string.
   *
   * @param name the given shape name to be set.
   * @param s the given string to be set to.
   */

  protected void setTextFields(String name, String s) {
    redText.setText(s);
    greenText.setText(s);
    blueText.setText(s);
    widthText.setText(s);
    heightText.setText(s);
    xText.setText(s);
    yText.setText(s);
    rectangleButton.setSelected(true);
    time.setText(s);
    shapeName.setText(name);
  }
}



