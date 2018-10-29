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
  Map<String, IShape> commands;
  // have model be able to have commands added to it like shapes. Model would then execute the commands
  // it would check the tick and call shape.move or something
  int tick;
  int tickRate;
  boolean isRunning;

  /**
   * Constructs an animator model implementation.
   * @param tick  The current tick of the animation
   * @param tickRate  The rate of ticks per second on the animation.
   */
  AnimatorModelImpl(int tick, int tickRate) {
    this.tick = tick;
    this.tickRate = tickRate;
    this.shapes = new HashMap<>();
    this.isRunning = true;

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
    return this.isRunning;
  }

  @Override
  public void addShape(SimpleShape s) {
    shapes.put(s.name, s);
  }
}
