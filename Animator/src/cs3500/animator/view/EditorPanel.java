package cs3500.animator.view;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

import cs3500.animator.model.Motion;
import cs3500.animator.model.SimpleShape;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditorPanel extends JPanel implements ItemListener,
    ListSelectionListener {


  private JLabel colorChooserDisplay;
  private JPanel motionPanel;
  private JPanel shapesAndMotions;
  private Map<String, List<Motion>> keyFrames;
  private JList<String> listOfStrings;
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
  private String selectedShape;
  private String selectedType;
  private Motion selectedMotion;
  private JPanel shapeNamesPanel;
  private Map<String, String> nameType;

  /**
   * Constructs the animation panel and sets the shapes list to an array list.
   */

  public EditorPanel(Map<String, List<Motion>> keyFrames, Map<String, String> nameType) {
    super();
    this.nameType = nameType;
    this.setLayout(new BorderLayout());
    //this.setPreferredSize(new Dimension(500, 500));
    this.setBackground(Color.BLACK);
    this.listOfButtons = new ArrayList<>();
    shapesAndMotions = new JPanel();
    shapesAndMotions.setPreferredSize(new Dimension(640, 350));
    shapesAndMotions.setLayout(new BorderLayout());

    this.keyFrames = keyFrames;
    shapeNamesPanel = new JPanel();
    shapeNamesPanel.setBorder(BorderFactory.createTitledBorder("Shape Name"));
    this.displayShapes();

    motionPanel = new JPanel();
    motionPanel.setLayout(new BorderLayout());
    motionPanel.setBorder(BorderFactory.createTitledBorder("Keyframes"));

    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
    JPanel keyFrameButtons = new JPanel(new FlowLayout());
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
//    addMotionButton.addActionListener(this);
//    addMotionButton.setActionCommand("add keyframe");
//    removeMotionButton.addActionListener(this);
//    removeMotionButton.setActionCommand("remove keyframe");
//    addShapeButton.addActionListener(this);
//    addShapeButton.setActionCommand("add shape");
//    removeShapeButton.addActionListener(this);
//    removeShapeButton.setActionCommand("remove shape");
//    modifyMotionButton.addActionListener(this);
//    modifyMotionButton.setActionCommand("modify keyframe");

    keyFrameButtons.add(removeShapeButton);
    keyFrameButtons.add(removeMotionButton);

    shapesAndMotions.add(keyFrameButtons, BorderLayout.SOUTH);
    this.add(shapesAndMotions, BorderLayout.NORTH);

    JPanel shapeAttributes = new JPanel();
    //shapeAttributes.setBackground(Color.gray);
    shapeAttributes.setLayout(new FlowLayout());
    shapeAttributes.setBorder(BorderFactory.createTitledBorder("Shape/Keyframe Attributes"));

    time = new JTextField(4);
    shapeAttributes.add(time);
    JTextArea timeButton = new JTextArea("Time");
    shapeAttributes.add(timeButton);

    JPanel colors = new JPanel();
    colors.setLayout(new BoxLayout(colors, BoxLayout.Y_AXIS));

    //input textfield
    JPanel redTextButton = new JPanel(new FlowLayout());
    redText = new JTextField(2);
    redTextButton.add(redText);
    JTextArea redButton = new JTextArea("Red");
    redTextButton.add(redButton);
    colors.add(redTextButton);

    //input textfield
    JPanel greenTextButton = new JPanel(new FlowLayout());
    greenText = new JTextField(2);
    greenTextButton.add(greenText);
    JTextArea greenButton = new JTextArea("Green");
    greenTextButton.add(greenButton);
    colors.add(greenTextButton);

    //input textfield
    JPanel blueTextButton = new JPanel(new FlowLayout());
    blueText = new JTextField(2);
    blueTextButton.add(blueText);
    JTextArea blueButton = new JTextArea("Blue");
    blueTextButton.add(blueButton);
    colors.add(blueTextButton);

    shapeAttributes.add(colors);

    JPanel sizePanel = new JPanel();
    sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.Y_AXIS));
    //input textfield
    JPanel widthTextButton = new JPanel(new FlowLayout());
    widthText = new JTextField(2);
    widthTextButton.add(widthText);
    JTextArea widthButton = new JTextArea("Width");
    widthTextButton.add(widthButton);
    sizePanel.add(widthTextButton);

    //input textfield
    JPanel heightTextButton = new JPanel(new FlowLayout());
    heightText = new JTextField(2);
    heightTextButton.add(heightText);
    JTextArea heightButton = new JTextArea("Height");
    heightTextButton.add(heightButton);
    sizePanel.add(heightTextButton);

    shapeAttributes.add(sizePanel);

    JPanel positionPanel = new JPanel();
    positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.Y_AXIS));
    //input textfield
    JPanel xTextButton = new JPanel(new FlowLayout());
    xText = new JTextField(3);
    xTextButton.add(xText);
    JTextArea xButton = new JTextArea("X");
    xTextButton.add(xButton);
    positionPanel.add(xTextButton);

    //input textfield
    JPanel yTextButton = new JPanel(new FlowLayout());
    yText = new JTextField(3);
    yTextButton.add(yText);
    JTextArea yButton = new JTextArea("Y");
    yTextButton.add(yButton);
    positionPanel.add(yTextButton);
    shapeAttributes.add(positionPanel);

    //radio buttons
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
    shapeAttributes.add(radioPanel);

    shapeName = new JTextField(4);
    shapeAttributes.add(shapeName);
    JTextArea shapeNameButton = new JTextArea("Shape Name");
    shapeAttributes.add(shapeNameButton);

    JPanel middleButtonsPanel = new JPanel(new BorderLayout());
    middleButtonsPanel.add(shapeAttributes, BorderLayout.NORTH);

    JPanel newMotionShapeButtonsPanel = new JPanel(new FlowLayout());

    newMotionShapeButtonsPanel.add(addShapeButton);
    newMotionShapeButtonsPanel.add(addMotionButton);
    newMotionShapeButtonsPanel.add(modifyMotionButton);
    middleButtonsPanel.add(newMotionShapeButtonsPanel, BorderLayout.SOUTH);

    this.add(middleButtonsPanel, BorderLayout.CENTER);

    JPanel playBackCommands = new JPanel();
    playBackCommands.setLayout(new FlowLayout());
    JButton restart = new JButton("Restart");
    listOfButtons.add(restart);
//    restart.addActionListener(this);
//    restart.setActionCommand("restart");
    JButton rewind = new JButton("Reverse");
    listOfButtons.add(rewind);
//    rewind.addActionListener(this);
//    rewind.setActionCommand("rewind");
    JButton pause = new JButton("||");
    listOfButtons.add(pause);
//    pause.addActionListener(this);
//    pause.setActionCommand("pause");
    JButton forwards = new JButton("Fastforward");
    listOfButtons.add(forwards);

    JButton slowDown = new JButton("Slow down");
    listOfButtons.add(slowDown);
//    forwards.addActionListener(this);
//    forwards.setActionCommand("forwards");
    JButton loop = new JButton("Toggle Loop");
    listOfButtons.add(loop);
//    loop.addActionListener(this);
//    loop.setActionCommand("loop");

    playBackCommands.add(rewind);
    playBackCommands.add(pause);
    playBackCommands.add(forwards);
    playBackCommands.add(slowDown);
    playBackCommands.add(restart);
    playBackCommands.add(loop);

    this.add(playBackCommands, BorderLayout.SOUTH);

    /*JPanel colorChooserPanel = new JPanel();
    colorChooserPanel.setLayout(new FlowLayout());
    this.add(colorChooserPanel);
    JButton colorChooserButton = new JButton("Choose a color");
    colorChooserButton.setActionCommand("Color chooser");
    colorChooserButton.addActionListener(this);
    colorChooserPanel.add(colorChooserButton);
    colorChooserDisplay = new JLabel("      ");
    colorChooserDisplay.setOpaque(true); //so that background color shows up
    colorChooserDisplay.setBackground(Color.WHITE);
    colorChooserPanel.add(colorChooserDisplay);
    this.add(colorChooserPanel);*/

  }

  private void displayShapes() {
    shapeNamesPanel.removeAll();
    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    for (Entry<String, List<Motion>> shape : keyFrames.entrySet()) {
      dataForListOfStrings.addElement(shape.getKey());
    }
    if (selectedShape != null) {
      dataForListOfStrings.addElement(selectedShape);

    }
    listOfStrings = new JList<>(dataForListOfStrings);
    listOfStrings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfStrings.addListSelectionListener(this);
    listOfStrings.setPreferredSize(new Dimension(100, 350));
    shapeNamesPanel.add(new JScrollPane(listOfStrings));
    shapesAndMotions.add(shapeNamesPanel, BorderLayout.WEST);

    this.updateUI();
  }

  private void displayMotions(String shapeName) {
    motionPanel.removeAll();
    DefaultListModel<String> dataForListOfMotions = new DefaultListModel<>();
    if (keyFrames.get(shapeName) == null) {
      return;
    }
    List<Motion> lom = keyFrames.get(shapeName);
    Collections.sort(lom);
    for (Motion m : lom) {
      dataForListOfMotions.addElement("" + m.getStartTime());
    }

    listOfMotions = new JList<>(dataForListOfMotions);
    listOfMotions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfMotions.addListSelectionListener(new MotionListener());
    listOfMotions.setPreferredSize(new Dimension(300, 350));
    motionPanel.add(new JScrollPane(listOfMotions));
    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
    //this.add(shapesAndMotions, "North");
    this.updateUI();

  }

  public void setNameType(Map<String, String> nameType) {
    this.nameType = nameType;
  }


  public void setButtonListeners(ActionListener e) {
    for (JButton b : listOfButtons) {
      b.addActionListener(e);
      b.setActionCommand(b.getText());
    }
  }

  public Motion newMotion() {

    Color c = new Color(Math.min(0, Math.max(Integer.parseInt(redText.getText()), 255)),
        Math.min(0, Math.max(Integer.parseInt(greenText.getText()), 255)),
        Math.min(0, Math.max(Integer.parseInt(blueText.getText()), 255)));
    return new Motion(selectedShape, nameType.get(selectedShape),
        Integer.parseInt(this.time.getText()),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c, Integer.parseInt(this.time.getText()),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c);
  }

  public Motion modifiedMotion() {
    Color c = new Color(Math.min(0, Math.max(Integer.parseInt(redText.getText()), 255)),
        Math.min(0, Math.max(Integer.parseInt(greenText.getText()), 255)),
        Math.min(0, Math.max(Integer.parseInt(blueText.getText()), 255)));
    return new Motion(selectedShape, keyFrames.get(selectedShape).get(0).getType(),
        selectedMotion.getStartTime(),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c, selectedMotion.getEndTime(),
        Integer.parseInt(this.xText.getText()), Integer.parseInt(this.yText.getText()),
        Integer.parseInt(this.widthText.getText()),
        Integer.parseInt(this.heightText.getText()), c);
  }

  public Motion getSelectedMotion() {
    return selectedMotion;
  }

  public SimpleShape getSelectedShape() {
    selectedShape = shapeName.getText();
    selectedType = "ellipse";
    if (rectangleButton.isSelected()) {
      selectedType = "rectangle";
    }
    displayShapes();
    return new SimpleShape(selectedShape, selectedType);
  }


  private class MotionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {

      List<Motion> lom = keyFrames.get(listOfStrings.getSelectedValue());
      for (Motion m : lom) {
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

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    selectedShape = listOfStrings.getSelectedValue();
    displayMotions(selectedShape);

  }

  public void setKeyFrames(Map<String, List<Motion>> keyFrames) {
    this.keyFrames = keyFrames;
    this.displayMotions(selectedShape);
  }
}



