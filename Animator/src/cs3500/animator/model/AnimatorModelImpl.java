package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents an animator model and implements all of its associated operations.
 */
public class AnimatorModelImpl implements AnimatorModel{

  Map<String, IShape> shapes;
  int tick;
  int tickRate;

  /**
   * Constructs an animator model implementation.
   * @param tick  The current tick of the animation
   * @param tickRate  The rate of ticks per second on the animation.
   */
  AnimatorModelImpl(int tick, int tickRate) {
    this.tick = tick;
    this.tickRate = tickRate;
    this.shapes = new HashMap<>();
  }

  @Override
  public List<IShape> draw() {
    List<IShape> result = new ArrayList<>();
    for (IShape s : shapes.values()) {
      result.add(s);
    }
    return result;
  }

  @Override
  public boolean isAnimationOver() {
    return false;
  }

  @Override
  public void addShape(AShape s) {
    shapes.put(s.name, s);
  }
}
