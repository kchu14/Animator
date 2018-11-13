package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

public class EditableView implements AnimatorView{
  IVisualGraphicsView graphicsView;

  public EditableView(IVisualGraphicsView graphicsView) {
    this.graphicsView = graphicsView;

  }

  @Override
  public void playAnimation(AnimatorModel model) {

  }
}
