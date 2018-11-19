package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.view.IEditView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        try {
          model.addShape(view.newMotion());
          model.addNewMotion(view.newMotion());
          view.setKeyFrames(model.getKeyFrames());
        }
        catch (Exception err) {
          view.showError("Be sure that you have all of the fields"
              + " filled out and a shape selected in the top left corner");
        }
        break;
      case "Remove keyframe":
        try {
          model.removeMotion(view.getSelectedMotion());
          view.setKeyFrames(model.getKeyFrames());
        }
        catch (Exception err) {
          view.showError("Be sure have selected a keyframe.");
        }
        break;

      case "Modify keyframe":
        try {
          model.editMotion(view.modifiedMotion());
          view.setKeyFrames(model.getKeyFrames());
        }
        catch (Exception err) {
            view.showError("Be sure you have a keyframe selected and all of the fields are filled "
                + "out.");
          }
        break;

      case "Add shape":
        try {
          model.declareNewShape(view.getCreatedShape());
          view.setNameType(model.getNameType());
        }
        catch (Exception err){
          view.showError("Be sure that all of the shape fields are filled out and shape "
              + "names are unique.");
      }
        break;

      case "Remove shape":
        try {
          model.removeShape(view.getSelectedShape());
          view.setNameType(model.getNameType());
          view.setKeyFrames(model.getKeyFrames());
        }
        catch (Exception err) {
          view.showError("Be sure you have a shape selected in the top left box.");
        }
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
