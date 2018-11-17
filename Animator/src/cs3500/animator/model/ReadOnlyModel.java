package cs3500.animator.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ReadOnlyModel {
AnimatorModel model;

  public ReadOnlyModel(AnimatorModel model) {
    this.model = model;
  }

  public int getLastTick() {
    return model.getLastTick();
  }

  public Map<Integer, List<IShape>> getTickListShapes() {
    return Collections.unmodifiableMap(model.getTickListShapes());
  }


  public int getHeight() {
    return model.getHeight();
  }

  public int getWidth() {
    return model.getWidth();
  }

  public int getWindowX() {
    return model.getWindowX();
  }

  public int getWindowY() {
    return model.getWindowY();
  }

  public Map<String, List<Motion>> getMotions() {
    return Collections.unmodifiableMap(model.getMotions());
  }

  public Map<String, IShape> getShapes() {
    return Collections.unmodifiableMap(model.getShapes());
  }

  public Map<String, String> getNameType() {
    return Collections.unmodifiableMap(model.getNameType());
  }

  public Map<String, List<Motion>> getKeyFrames() {
    return Collections.unmodifiableMap(model.getKeyFrames());
  }

}
