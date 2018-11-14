package cs3500.animator.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReadOnlyModel implements AnimatorModel {
AnimatorModel model;

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
