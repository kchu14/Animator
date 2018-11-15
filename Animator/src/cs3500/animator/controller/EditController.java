package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.EditableView;
import cs3500.animator.view.EditorPanel;
import cs3500.animator.view.IEditView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EditController implements IController, ActionListener {

  private AnimatorModel model;
  private IEditView view;
  private boolean isAnimationOver;

  public EditController(AnimatorModel model, IEditView view) {
    this.model = model;
    this.view = view;
    this.isAnimationOver = false;
    view.setButtonListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "add keyframe":
        break;
      case "remove keyframe":
        break;

      case "modify keyframe":
        break;

      case "add shape":
//        String shapeName = this.shapeName.getText();
//        boolean isRect = false;
//        if (rectangleButton.isSelected()) {
//          isRect = true;
//        }
//        for (String names : keyFrames.keySet()) {
//          if (shapeName.equals(names)) {
//            JOptionPane.showMessageDialog(this, "Cannot add a duplicate name",
//                "Error!", JOptionPane.ERROR_MESSAGE);
//          }
//        }
        break;

      case "remove shape":
        break;

      case "Restart":
        view.restart();

        break;
      case "rewind":
        break;

      case "pause":
        break;

      case "forwards":
        break;

      case "Toggle Loop":
        view.setIsAnimationOver(isAnimationOver);
        System.out.println(isAnimationOver);
        this.isAnimationOver = !isAnimationOver;
        break;
    }
  }
}
