package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class represents an animator model and implements all of its associated operations.
 */
public final class AnimatorModelImpl implements AnimatorModel {

  Map<String, IShape> shapes;
  Map<String, String> nameType;
  Map<Integer, List<ICommand>> commands;
  // have model be able to have commands added to it like shapes. Model would then execute
  // the commands
  // it would check the tick and call shape.move or something
  int tick;
  int tickRate;
  boolean isOver;
  int leftMostX;
  int topMostY;
  int animationWidth;
  int animationHeight;

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


  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    Map<String, IShape> shapes;
    Map<String, String> nameType;
    Map<Integer, List<ICommand>> commands;
    // have model be able to have commands added to it like shapes. Model would then execute
    // the commands
    // it would check the tick and call shape.move or something
    int tick;
    int tickRate;
    boolean isOver;
    int leftMostX;
    int topMostY;
    int animationWidth;
    int animationHeight;


    @Override
    public AnimatorModel build() {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.leftMostX = x;
      this.topMostY = y;
      this.animationWidth = width;
      this.animationHeight = height;
      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      if (this.nameType == null) {
        this.nameType = new HashMap<>();
      }
      if (this.nameType.containsKey(name)) {
        throw new IllegalArgumentException("Shape names have to be unique");
      }
      else {
        this.nameType.put(name, type);
        return this;
      }
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {

      if (this.shapes == null) {
        this.shapes = new HashMap<>();
      }

      try {
        this.shapes.put(name,
            new SimpleShape(name, nameType.get(name), t1, x1, y1, w1, h1, new Color(r1, g1, b1), t2,
                x2, y2, w2, h2, new Color(r2, g2, b2)));
      } catch (NullPointerException e) {
        throw new IllegalArgumentException("Trying to perform motion on non-existing shape");
      }

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
        int h, int r, int g, int b) {
      return null;
    }
    // FILL IN HERE
  }


}


