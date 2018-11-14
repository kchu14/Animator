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
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
    motionPanel.setLayout(new FlowLayout());
    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
    this.add(shapesAndMotions, BorderLayout.NORTH);

    JPanel shapeAttributes = new JPanel();
    shapeAttributes.setBackground(Color.gray);
    shapeAttributes.setLayout(new FlowLayout());

    //input textfield
    JTextField redText = new JTextField(2);
    shapeAttributes.add(redText);

    //buttons
    JButton redButton = new JButton("Red");
    shapeAttributes.add(redButton);

    //input textfield
    JTextField greenText = new JTextField(2);
    shapeAttributes.add(greenText);

    //buttons
    JButton greenButton = new JButton("Green");
    shapeAttributes.add(greenButton);

    //input textfield
    JTextField blueText = new JTextField(2);
    shapeAttributes.add(blueText);

    //buttons
    JButton blueButton = new JButton("Blue");
    shapeAttributes.add(blueButton);

    //input textfield
    JTextField widthText = new JTextField(2);
    shapeAttributes.add(widthText);

    //buttons
    JButton widthButton = new JButton("Width");
    shapeAttributes.add(widthButton);

//input textfield
    JTextField heightText = new JTextField(2);
    shapeAttributes.add(heightText);

    //buttons
    JButton heightButton = new JButton("Height");
    shapeAttributes.add(heightButton);

    this.add(shapeAttributes, BorderLayout.SOUTH);




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
      dataForListOfMotions.addElement(m.getTextResult());
    }

    listOfMotions = new JList<>(dataForListOfMotions);
    listOfMotions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listOfMotions.addListSelectionListener(this);
    listOfMotions.setPreferredSize(new Dimension(300, 350));
    motionPanel.add(new JScrollPane(listOfMotions));
    shapesAndMotions.add(motionPanel, BorderLayout.CENTER);
    //this.add(shapesAndMotions, "North");
    this.updateUI();
    System.out.println("displaying");

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
