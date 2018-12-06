package cs3500.animator.controller;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.view.IEditView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * CHANGE LOG Added the controller class and interface to allow the view to pass inputs to the
 * model. The controller then updates the view with the new model information.
 */

/**
 * This class represents the implementation of a IController. This controller takes in the mouse and
 * keyboard commands given from the view and delegates them to the model to be handled.
 */
public class EditController implements IController, ActionListener {

  private AnimatorModel model;
  private IEditView view;
  private boolean isAnimationOver;
  protected String errmsg;

  /**
   * Constructs a controller for our animator.
   *
   * @param model the given model for the animator
   * @param view the given view for the animator. (View has to be an edit view)
   */
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
        } catch (Exception err) {
          errmsg = "Be sure that you have all of the fields"
              + " filled out and a shape selected in the top left corner, duplicate keyframes not allowed";
          view.showError(errmsg);
        }
        break;
      case "Remove keyframe":
        try {
          model.removeMotion(view.getSelectedMotion());
          view.setKeyFrames(model.getKeyFrames());
        } catch (Exception err) {
          errmsg = "Be sure have selected a keyframe.";
          view.showError(errmsg);
        }
        break;

      case "Modify keyframe":
        try {
          model.editMotion(view.modifiedMotion());
          view.setKeyFrames(model.getKeyFrames());
        } catch (Exception err) {
          errmsg = "Be sure you have a keyframe selected and all of the fields are filled "
              + "out. Also the time of the modified frame must equal the selected frame.";
          view.showError(errmsg);
        }
        break;

      case "Add shape":
        try {
          model.declareNewShape(view.getCreatedShape());
          view.setNameType(model.getNameType());
        } catch (Exception err) {
          errmsg = "Be sure that all of the shape fields are filled out and shape "
              + "names are unique.";
          view.showError(errmsg);
        }
        break;

      case "Remove shape":
        try {
          model.removeShape(view.getSelectedShape());
          view.setNameType(model.getNameType());
          view.setKeyFrames(model.getKeyFrames());
        } catch (Exception err) {
          errmsg = "Be sure you have a shape selected in the top left box.";
          view.showError(errmsg);
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
      default:
        errmsg = "Be sure everything is filled out";
        view.showError(errmsg);
        break;
    }
  }
}
