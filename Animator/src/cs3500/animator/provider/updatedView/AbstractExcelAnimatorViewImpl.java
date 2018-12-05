package cs3500.animator.provider.updatedView;

import cs3500.model.ExcelAnimatorModel;

public abstract class AbstractExcelAnimatorViewImpl implements ExcelAnimatorView {

  protected ExcelAnimatorModel model;
  protected Appendable output;

  //I can't quite yet figure out what behavior needs to be supported across all views, so I'm
  // starting with the text view and seeing what I could potentially abstract
  public AbstractExcelAnimatorViewImpl(ExcelAnimatorModel model, Appendable output) {
    this.model = model;
    this.output = output;
  }
}
