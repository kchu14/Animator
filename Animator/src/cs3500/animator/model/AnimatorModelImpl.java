package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents an animator model and implements all of its associated operations.
 */
public class AnimatorModelImpl implements AnimatorModel{

  Map<String, IShape> shapes;
  Map<Integer, List<ICommand>> commands;
  // have model be able to have commands added to it like shapes. Model would then execute the commands
  // it would check the tick and call shape.move or something
  int tick;
  int tickRate;
  boolean isRunning;

  /**
   * Constructs an animator model implementation.
   * @param tickRate  The rate of ticks per second on the animation.
   */
  AnimatorModelImpl(int tickRate) {
    this.tick = 0;
    this.tickRate = tickRate;
    this.shapes = new HashMap<>();
    this.commands = new TreeMap<>();
    this.isRunning = true;
  }

  @Override
  public List<IShape> tickResult() {
    List<IShape> result = new ArrayList<>();
    for (IShape s : shapes.values()) {
      result.add(s);
    }
    this.tick += tickRate;
    return result;
  }

  @Override
  public boolean isAnimationOver() {
    return this.isRunning;
  }

  @Override
  public void addShape(IShape s) {
    shapes.put(s.getName(), s);
  }

  @Override
  public void addCommand(List<ICommand> c, int start, int end){
    for (int i = start; i < end; i++) {
      if(commands.containsKey(i)) {
        List<ICommand> oldAndNewComs = commands.get(i);
        oldAndNewComs.addAll(c);
        commands.put(i, oldAndNewComs);
      }
      else {
        commands.put(i, c);
      }
    }
  }

  @Override
  public void go() {
    for(ICommand c : commands.get(tick)) {
      c.apply(this);
    }
  }

  @Override
  public Map<String, IShape> getShapes() {
    return this.shapes;
  }
}


