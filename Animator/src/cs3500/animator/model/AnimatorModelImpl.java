package cs3500.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents an animator model and implements all of its associated operations.
 */
public class AnimatorModelImpl implements AnimatorModel {

  Map<String, IShape> shapes;
  Map<Integer, List<ICommand>> commands;
  // have model be able to have commands added to it like shapes. Model would then execute
  // the commands
  // it would check the tick and call shape.move or something
  int tick;
  int tickRate;
  boolean isOver;

  /**
   * Constructs an animator model implementation.
   *
   * @param tickRate The rate of ticks per second on the animation.
   */
  public AnimatorModelImpl(int tickRate) {
    this.tick = 1;
    this.tickRate = tickRate;
    this.shapes = new HashMap<>();
    this.commands = new TreeMap<>();
    this.isOver = false;
  }

  @Override
  public List<IShape> tickResult() {
    List<IShape> result = new ArrayList<>();
    execute(tick);
    for (IShape s : shapes.values()) {
      result.add(s);
    }

    this.tick += tickRate;
    return result;
  }

  @Override
  public boolean isAnimationOver() {
    // unsure of when to conclude animation model (when they close the window?)
    return this.isOver;
  }

  @Override
  public void addShape(IShape s) throws IllegalArgumentException {
    if (shapes.containsKey(s.getName())) {
      throw new IllegalArgumentException("Shape names have to be unique.");
    }
    shapes.put(s.getName(), s);
  }

  @Override
  public void addCommand(List<ICommand> c, int start, int end) throws IllegalArgumentException {
    for (int i = start; i < end; i++) {
      if (commands.containsKey(i)) {
        for (ICommand com : c) {
          for (ICommand com2 : commands.get(i)) {
            if (com.getShapeName().equals(com2.getShapeName()) && com.getCommandType()
                .equals(com2.getCommandType())) {
              throw new IllegalArgumentException(
                  "Can't perform multiple motions of the same action on same shape at same time.");
            }
          }

        }
        List<ICommand> oldAndNewComs = commands.get(i);
        oldAndNewComs.addAll(c);
        commands.put(i, oldAndNewComs);
      } else {
        commands.put(i, c);
      }
    }
  }

  /**
   * This method executes all of the commands given on this tick.
   */
  private void execute(int tick) {
    if (commands.containsKey(tick)) {
      for (ICommand c : commands.get(tick)) {
        c.apply(this);
      }
    }
  }

  @Override
  public Map<String, IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public Map<Integer, List<ICommand>> getCommands() {
    return this.commands;
  }

  @Override
  public String getModelState() {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, IShape> shape : shapes.entrySet()) {
      String key = shape.getKey();
      IShape value = shape.getValue();
      String shapeType = "";
      if (key.startsWith("r")) {
        shapeType = "rectangle";
      }
      if (key.startsWith("o")) {
        shapeType = "oval";
      }
      if (key.startsWith("p")) {
        shapeType = "polygon";
      }
      result.append(
          "shape " + value.getName().substring(1, value.getName().length()) + " " + shapeType
              + "\n");
      for (Map.Entry<Integer, List<ICommand>> command : commands.entrySet()) {
        int tick = command.getKey();
        List<ICommand> lCommands = command.getValue();
        for (ICommand c : lCommands) {
          if (key.equals(c.getShapeName()) && shapes.get(key) != null) {
            result.append(shapes.get(key).getShapeState(tick));
            execute(tick);
          }
        }
      }
    }

    return result.toString();
  }

}


