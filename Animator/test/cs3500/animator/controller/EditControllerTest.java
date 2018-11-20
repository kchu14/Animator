package cs3500.animator.controller;

import static org.junit.Assert.assertEquals;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.ReadOnlyModel;
import cs3500.animator.view.EditableView;
import cs3500.animator.view.VisualGraphicsView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import org.junit.Test;

public class EditControllerTest {

  private AnimatorModel model;
  private EditController controller;
  private EditableView view;
  private JButton addMotionButton;
  private JButton removeMotionButton;
  private JButton addShapeButton;
  private JButton removeShapeButton;
  private JButton modifyMotionButton;

  private void setButtonListeners() {
    List<JButton> listOfButtons = new ArrayList<>();
    addMotionButton = new JButton("Add keyframe");
    listOfButtons.add(addMotionButton);
    removeMotionButton = new JButton("Remove keyframe");
    listOfButtons.add(removeMotionButton);
    addShapeButton = new JButton("Add shape");
    listOfButtons.add(addShapeButton);
    removeShapeButton = new JButton("Remove shape");
    listOfButtons.add(removeShapeButton);
    modifyMotionButton = new JButton("Modify keyframe");
    listOfButtons.add(modifyMotionButton);
    model = new AnimatorModelImpl.Builder().setBounds(0, 0, 500, 500)
        .declareShape("r", "rectangle")
        .addMotion("r", 0, 100, 100, 50, 50, 255, 0, 0,
            10, 100, 100, 50, 50, 0, 0, 255).build();
    view = new EditableView(new VisualGraphicsView(1));
    view.setEPane(new ReadOnlyModel(model));
    controller = new EditController(model, view);
    for (JButton b : listOfButtons) {
      b.addActionListener(controller);
      b.setActionCommand(b.getText());
    }
  }


  @Test
  public void testAddShapeNoFields() {
    setButtonListeners();
    addShapeButton.doClick();
    assertEquals("Be sure that all of the shape fields are filled out and shape "
        + "names are unique.", controller.errmsg);
  }

  @Test
  public void testAddMotionNoFields() {
    setButtonListeners();
    addMotionButton.doClick();
    assertEquals("Be sure that you have all of the fields filled out and a shape selected "
        + "in the top left corner", controller.errmsg);
  }

  @Test
  public void testRemoveShapeNoneSelected() {
    setButtonListeners();
    removeShapeButton.doClick();
    assertEquals("Be sure you have a shape selected in the top left box.", controller.errmsg);
  }

  @Test
  public void testRemoveMotionNoneSelected() {
    setButtonListeners();
    removeMotionButton.doClick();
    assertEquals("Be sure have selected a keyframe.", controller.errmsg);
  }

  @Test
  public void testModifyMotionNoneSelectedShowError() {
    setButtonListeners();
    modifyMotionButton.doClick();
    assertEquals("Be sure you have a keyframe selected and all of the fields are filled out."
        + " Also the time of the modified frame must equal the selected frame.", controller.errmsg);
  }

  @Test
  public void testAddShape() {
    setButtonListeners();
    view.setTextFields("12", "12");
    assertEquals(1, model.getNameType().size());
    addShapeButton.doClick();
    assertEquals(2, model.getNameType().size());
  }

  @Test
  public void testAddMotion() {
    setButtonListeners();
    view.setTextFields("r", "12");
    assertEquals(1, model.getMotions().get("r").size());
    addMotionButton.doClick();
    assertEquals(2, model.getMotions().get("r").size());
  }

  @Test
  public void testRemoveShape() {
    setButtonListeners();
    view.setTextFields("r", "12");
    assertEquals(1, model.getNameType().size());
    removeShapeButton.doClick();
    assertEquals(0, model.getNameType().size());
  }

  @Test
  public void testRemoveMotion() {
    setButtonListeners();
    view.setTextFields("r", "12");
    assertEquals(1, model.getMotions().get("r").size());
    removeMotionButton.doClick();
    assertEquals(0, model.getMotions().get("r").size());
  }

  @Test
  public void testModifyMotion() {
    setButtonListeners();
    view.setTextFields("r", "12");
    assertEquals("motion r 0 100 100 50 50 255 0 0 10 100 100 50 50 0 255 0 \n",
        model.getMotions().get("r").get(0).getTextResult());
    modifyMotionButton.doClick();
    assertEquals("motion r 0 12 12 12 21 21 21 12 10 12 12 12 12 12 12 12 \n",
        model.getMotions().get("r").get(0).getTextResult());
  }
}