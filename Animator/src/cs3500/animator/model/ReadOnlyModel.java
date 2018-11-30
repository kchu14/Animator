package cs3500.animator.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * CHANGE LOG Created a read only model to be passed into the view. This allows the view to gather
 * the information from the model without mutation being allowed.
 */

/**
 * This class represents a read only model of the given AnimatorModelImpl. This class only contains
 * methods that are immutable to the given animator model. This read only model is passed to the
 * view to construct a visual representation.
 */
public class ReadOnlyModel implements IReadOnlyModel {

  private AnimatorModel model;

  /**
   * Constructs a read only model.
   *
   * @param model the given Animator model that becomes read only.
   */
  public ReadOnlyModel(AnimatorModel model) {
    this.model = model;
  }

  @Override
  public int getLastTick() {
    return model.getLastTick();
  }

  @Override
  public Map<Integer, List<IShape>> getTickListShapes() {
    return Collections.unmodifiableMap(model.getTickListShapes());
  }

  @Override
  public int getHeight() {
    return model.getHeight();
  }

  @Override
  public int getWidth() {
    return model.getWidth();
  }

  @Override
  public int getWindowX() {
    return model.getWindowX();
  }

  @Override
  public int getWindowY() {
    return model.getWindowY();
  }

  @Override
  public Map<String, List<Motion>> getMotions() {
    return Collections.unmodifiableMap(model.getMotions());
  }

  @Override
  public Map<String, IShape> getShapes() {
    return Collections.unmodifiableMap(model.getShapes());
  }

  @Override
  public Map<String, String> getNameType() {
    return Collections.unmodifiableMap(model.getNameType());
  }

  @Override
  public Map<String, List<Motion>> getKeyFrames() {
    return Collections.unmodifiableMap(model.getKeyFrames());
  }

}
