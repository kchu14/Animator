package cs3500.animator.view;

import cs3500.animator.model.Motion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditorPanel extends JPanel implements ActionListener, ItemListener,
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

  /**
   * Constructs the animation panel and sets the shapes list to an array list.
   */

  public EditorPanel(Map<String, List<Motion>> keyFrames) {
    super();
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(500, 500));
    this.setBackground(Color.BLACK);

    shapesAndMotions = new JPanel();
    shapesAndMotions.setBackground(Color.BLUE);
    shapesAndMotions.setPreferredSize(new Dimension(640, 350));
    shapesAndMotions.setLayout(new BorderLayout());

    this.keyFrames = keyFrames;
    JPanel shapeNamesPanel = new JPanel();
    shapeNamesPanel.setBackground(Color.red);
    shapeNamesPanel.setBorder(BorderFactory.createTitledBorder("Shape Name"));

    DefaultListModel<String> dataForListOfStrings = new DefaultListModel<>();
    for (Entry<String, List<Motion>> shape : keyFrames.entrySet()) {
      dataForListOfStrings.addElement(shape.getKey());
    }

    listOfStrings = new JList<>(dataForListOfStrings);
    listOfStrings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfStrings.addListSelectionListener(this);
    listOfStrings.setPreferredSize(new Dimension(100, 350));
    shapeNamesPanel.add(new JScrollPane(listOfStrings));
    shapesAndMotions.add(shapeNamesPanel, BorderLayout.WEST);
    motionPanel = new JPanel();
    motionPanel.setBackground(Color.green);
    motionPanel.setLayout(new BorderLayout());
    motionPanel.setBorder(BorderFactory.createTitledBorder("Keyframes"));

    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
    JPanel keyFrameButtons = new JPanel(new FlowLayout());
    JButton addMotionButton = new JButton("add keyframe");
    JButton removeMotionButton = new JButton("remove keyframe");
    JButton addShapeButton = new JButton("add shape");
    JButton removeShapeButton = new JButton("remove shape");
    JButton modifyMotionButton = new JButton("modify keyframe");

    keyFrameButtons.add(addShapeButton);
    keyFrameButtons.add(removeShapeButton);
    keyFrameButtons.add(addMotionButton);
    keyFrameButtons.add(removeMotionButton);
    keyFrameButtons.add(modifyMotionButton);

    shapesAndMotions.add(keyFrameButtons, BorderLayout.SOUTH);
    this.add(shapesAndMotions, BorderLayout.NORTH);

    JPanel shapeAttributes = new JPanel();
    shapeAttributes.setBackground(Color.gray);
    shapeAttributes.setLayout(new FlowLayout());
    shapeAttributes.setBorder(BorderFactory.createTitledBorder("Shape Attributes"));

    time = new JTextField(2);
    shapeAttributes.add(time);
    JButton timeButton = new JButton("time");
    shapeAttributes.add(timeButton);

    JPanel colors = new JPanel();
    colors.setLayout(new BoxLayout(colors, BoxLayout.Y_AXIS));

    //input textfield
    JPanel redTextButton = new JPanel(new FlowLayout());
    redText = new JTextField(2);
    redTextButton.add(redText);
    JButton redButton = new JButton("Red");
    redTextButton.add(redButton);
    colors.add(redTextButton);

    //input textfield
    JPanel greenTextButton = new JPanel(new FlowLayout());
    greenText = new JTextField(2);
    greenTextButton.add(greenText);
    JButton greenButton = new JButton("Green");
    greenTextButton.add(greenButton);
    colors.add(greenTextButton);

    //input textfield
    JPanel blueTextButton = new JPanel(new FlowLayout());
    blueText = new JTextField(2);
    blueTextButton.add(blueText);
    JButton blueButton = new JButton("Blue");
    blueTextButton.add(blueButton);
    colors.add(blueTextButton);

    shapeAttributes.add(colors);

    JPanel sizePanel = new JPanel();
    sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.Y_AXIS));
    //input textfield
    JPanel widthTextButton = new JPanel(new FlowLayout());
    widthText = new JTextField(2);
    widthTextButton.add(widthText);
    JButton widthButton = new JButton("Width");
    widthTextButton.add(widthButton);
    sizePanel.add(widthTextButton);

    //input textfield
    JPanel heightTextButton = new JPanel(new FlowLayout());
    heightText = new JTextField(2);
    heightTextButton.add(heightText);
    JButton heightButton = new JButton("Height");
    heightTextButton.add(heightButton);
    sizePanel.add(heightTextButton);

    shapeAttributes.add(sizePanel);

    JPanel positionPanel = new JPanel();
    positionPanel.setLayout(new BoxLayout(positionPanel, BoxLayout.Y_AXIS));
    //input textfield
    JPanel xTextButton = new JPanel(new FlowLayout());
    xText = new JTextField(2);
    xTextButton.add(xText);
    JButton xButton = new JButton("X");
    xTextButton.add(xButton);
    positionPanel.add(xTextButton);

    //input textfield
    JPanel yTextButton = new JPanel(new FlowLayout());
    yText = new JTextField(2);
    yTextButton.add(yText);
    JButton yButton = new JButton("Y");
    yTextButton.add(yButton);
    positionPanel.add(yTextButton);
    shapeAttributes.add(positionPanel);

    //radio buttons
    JPanel radioPanel = new JPanel();
    radioPanel.setBorder(BorderFactory.createTitledBorder("Shape Type"));
    radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
    ButtonGroup rGroup1 = new ButtonGroup();
    rectangleButton = new JRadioButton("rectangle");
    ellipseButton = new JRadioButton("ellipse");
    rGroup1.add(rectangleButton);
    rGroup1.add(ellipseButton);
    radioPanel.add(rectangleButton);
    radioPanel.add(ellipseButton);
    shapeAttributes.add(radioPanel);

    this.add(shapeAttributes, BorderLayout.CENTER);

    JPanel playBackCommands = new JPanel();
    playBackCommands.setLayout(new FlowLayout());
    JButton restart = new JButton("restart");
    JButton rewind = new JButton("<");
    JButton pause = new JButton("pause");
    JButton forwards = new JButton(">");
    JButton start = new JButton("start");
    JCheckBox loop = new JCheckBox("loop");

    playBackCommands.add(rewind);
    playBackCommands.add(pause);
    playBackCommands.add(start);
    playBackCommands.add(forwards);
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

  private void displayMotions(String shapeName) {
    motionPanel.removeAll();
    DefaultListModel<String> dataForListOfMotions = new DefaultListModel<>();
    List<Motion> lom = keyFrames.get(shapeName);
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
    System.out.println("displaying");

  }

  private class MotionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {

      List<Motion> lom = keyFrames.get(listOfStrings.getSelectedValue());
      for (Motion m : lom) {
        if (m.getStartTime() == Integer.parseInt(listOfMotions.getSelectedValue())) {
          String[] strArr = m.getTextResult().split(" ");
          redText.setText(strArr[7]);
          greenText.setText(strArr[8]);
          blueText.setText(strArr[9]);
          widthText.setText(strArr[5]);
          heightText.setText(strArr[6]);
          xText.setText(strArr[3]);
          yText.setText(strArr[4]);
          time.setText(strArr[2]);

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
  public void actionPerformed(ActionEvent e) {
    /*switch (e.getActionCommand()) {
      case "R":
        listOfStrings.setText(new String(pfield.getPassword()));
        pfield.setText("");
        break;
      case "RB1":
        radioDisplay.setText("Radio button 1 was selected");
        break;

      case "RB2":
        radioDisplay.setText("Radio button 2 was selected");
        break;*/
  }

  @Override
  public void itemStateChanged(ItemEvent e) {

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    displayMotions(listOfStrings.getSelectedValue());

  }

  public void setKeyFrames(Map<String, List<Motion>> keyFrames) {
    this.keyFrames = keyFrames;
  }
}



