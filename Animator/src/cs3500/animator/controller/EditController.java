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
      case "Add keyframe":
        model.addNewMotion(view.newMotion());
        break;
      case "Remove keyframe":
        break;

      case "Modify keyframe":
        break;

      case "Add shape":

        break;

      case "Remove shape":
        break;

      case "Restart":
        view.restart();

        break;
      case "Reverse":
        view.rewind();
        break;

      case "||":
        view.pause();
        break;

      case "Fastforward":
        view.fastforward();
        break;

      case "Slow down":
        view.slowDown();
        break;

      case "Toggle Loop":
        this.isAnimationOver = !isAnimationOver;
        view.setIsAnimationOver(isAnimationOver);
        System.out.println(isAnimationOver);
        break;
    }
  }
}
