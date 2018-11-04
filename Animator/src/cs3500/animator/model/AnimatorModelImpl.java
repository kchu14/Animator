package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents an animator model and implements all of its associated operations.
 */
public final class AnimatorModelImpl implements AnimatorModel {

  // shape name, shape object
  Map<String, IShape> shapes;
  // shape name, list of motions for the shape
  Map<String, List<Motion>> nameMotion;
  // shape name, shape type
  Map<String, String> nameType;
  Map<Integer, List<ICommand>> commands;
  int tick;
  int tickRate;
  boolean isOver;
  int leftMostX;
  int topMostY;
  int animationWidth;
  int animationHeight;

  private AnimatorModelImpl(Map<String, IShape> shapes,
      Map<String, List<Motion>> nameMotion,
      Map<String, String> nameType,
      Map<Integer, List<ICommand>> commands, int tick, int tickRate, boolean isOver, int leftMostX,
      int topMostY, int animationWidth, int animationHeight) {
    this.shapes = shapes;
    this.nameMotion = nameMotion;
    this.nameType = nameType;
    this.commands = commands;
    this.tick = tick;
    this.tickRate = tickRate;
    this.isOver = isOver;
    this.leftMostX = leftMostX;
    this.topMostY = topMostY;
    this.animationWidth = animationWidth;
    this.animationHeight = animationHeight;
  }

  //  @Override
//  public List<IShape> tickResult() {
//    List<IShape> result = new ArrayList<>();
//    execute(tick);
//    for (IShape s : shapes.values()) {
//      result.add(s);
//    }
//
//    this.tick += tickRate;
//    return result;
//  }

  // @Override
  public boolean isAnimationOver() {
    // unsure of when to conclude animation model (when they close the window?)
    return this.isOver;
  }

//  @Override
//  public void addShape(IShape s) throws IllegalArgumentException {
//    if (shapes.containsKey(s.getName())) {
//      throw new IllegalArgumentException("Shape names have to be unique.");
//    }
//    shapes.put(s.getName(), s);
//  }

//  @Override
//  public void addCommand(List<ICommand> c, int start, int end) throws IllegalArgumentException {
//    for (int i = start; i < end; i++) {
//      if (commands.containsKey(i)) {
//        for (ICommand com : c) {
//          for (ICommand com2 : commands.get(i)) {
//            if (com.getShapeName().equals(com2.getShapeName()) && com.getCommandType()
//                .equals(com2.getCommandType())) {
//              throw new IllegalArgumentException(
//                  "Can't perform multiple motions of the same action on same shape at same time.");
//            }
//          }
//
//        }
//        List<ICommand> oldAndNewComs = commands.get(i);
//        oldAndNewComs.addAll(c);
//        commands.put(i, oldAndNewComs);
//      } else {
//        commands.put(i, c);
//      }
//    }
//  }
//
//  /**
//   * This method executes all of the commands given on this tick.
//   */
//  private void execute(int tick) {
//    if (commands.containsKey(tick)) {
//      for (ICommand c : commands.get(tick)) {
//        c.apply(this);
//      }
//    }
//  }

//  @Override
//  public String getModelState() {
//    StringBuilder result = new StringBuilder();
//    for (Map.Entry<String, IShape> shape : shapes.entrySet()) {
//      String key = shape.getKey();
//      IShape value = shape.getValue();
//      String shapeType = "";
//      if (key.startsWith("r")) {
//        shapeType = "rectangle";
//      }
//      if (key.startsWith("o")) {
//        shapeType = "oval";
//      }
//      if (key.startsWith("p")) {
//        shapeType = "polygon";
//      }
//      result.append(
//          "shape " + value.getName().substring(1, value.getName().length()) + " " + shapeType
//              + "\n");
//      for (Map.Entry<Integer, List<ICommand>> command : commands.entrySet()) {
//        int tick = command.getKey();
//        List<ICommand> lCommands = command.getValue();
//        for (ICommand c : lCommands) {
//          if (key.equals(c.getShapeName()) && shapes.get(key) != null) {
//            result.append(shapes.get(key).getShapeState(tick));
//            execute(tick);
//          }
//        }
//      }
//    }
//
//    return result.toString();
//  }

  public void checkOverlaps() {
    Set<Integer> tickSet = new HashSet<>();
    for (List<Motion> listOfMotion : nameMotion.values()) {
      for (Motion m : listOfMotion) {
        for (int i = m.startTime; i < m.endTime; i++) {
          if (!tickSet.add(i)) {
            throw new IllegalArgumentException("added an overlapping motion");
          }
        }
      }

      // converts set into array
      int n = tickSet.size();
      int tickArr[] = new int[n];
      int i = 0;
      for (int x : tickSet) {
        tickArr[i++] = x;
      }

      // checks if there's more than a difference of one between ticks
      Arrays.sort(tickArr);
      int prev = 0;
      for (int y : tickArr) {
        if (Math.abs(tickArr[y] - prev) > 1) {
          throw new IllegalArgumentException("can't have gaps in motion");
        }
        prev = y;
      }
    }
  }

  @Override
  public String produceTextView() {
    StringBuilder result = new StringBuilder();
    for (Entry<String, List<Motion>> set : nameMotion.entrySet()) {
      String key = set.getKey();
      List<Motion> value = set.getValue();
      result.append("Shape " + key + " " + nameType.get(key) + "\n");
      for (Motion m : value) {
        result.append(m.getTextResult());
      }
    }
    return result.toString();
  }

  // todo
  // motion overlap check start and end positions
  // view

  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    Map<String, IShape> shapes;
    Map<String, List<Motion>> nameMotion;
    Map<String, String> nameType;
    Map<Integer, List<ICommand>> commands;
    int tick;
    int tickRate;
    boolean isOver;
    int leftMostX;
    int topMostY;
    int animationWidth;
    int animationHeight;


    @Override
    public AnimatorModel build() {
      return new AnimatorModelImpl(shapes, nameMotion, nameType, commands, tick, tickRate, false,
          leftMostX, topMostY, animationWidth, animationHeight);
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
      } else {
        this.nameType.put(name, type);
        return this;
      }
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {

      if (this.nameMotion == null) {
        this.nameMotion = new LinkedHashMap<>();
      }

      try {
        if (nameMotion.containsKey(name)) {
          this.nameMotion.get(name).add(
              new Motion(name, nameType.get(name), t1, x1, y1, w1, h1, new Color(r1, g1, b1), t2,
                  x2, y2, w2, h2, new Color(r2, g2, b2)));
        } else {
          List<Motion> listOfMotion = new ArrayList<>();
          listOfMotion.add(
              new Motion(name, nameType.get(name), t1, x1, y1, w1, h1, new Color(r1, g1, b1), t2,
                  x2, y2, w2, h2, new Color(r2, g2, b2)));
          this.nameMotion.put(name, listOfMotion);
        }
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


