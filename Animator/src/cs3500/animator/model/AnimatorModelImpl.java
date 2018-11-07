package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.Shape;
import java.lang.reflect.Array;
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
  Map<Integer, List<IShape>> tickShapesList;
  int leftMostX;
  int topMostY;
  int animationWidth;
  int animationHeight;

  private AnimatorModelImpl(Map<String, IShape> shapes,
      Map<String, List<Motion>> nameMotion,
      Map<String, String> nameType, int leftMostX,
      int topMostY, int animationWidth, int animationHeight,  Map<Integer, List<IShape>> tickShapesList) {
    this.shapes = shapes;
    this.nameMotion = nameMotion;
    this.nameType = nameType;
    this.leftMostX = leftMostX;
    this.topMostY = topMostY;
    this.animationWidth = animationWidth;
    this.animationHeight = animationHeight;
    this.tickShapesList = tickShapesList;
  }


  public void checkForValidMotions() {
    for (List<Motion> listOfMotion : nameMotion.values()) {
      Collections.sort(listOfMotion);
      this.addShape(listOfMotion.get(0));
      int prevEndTime = listOfMotion.get(0).startTime;
      int prevX = listOfMotion.get(0).startX;
      int prevY = listOfMotion.get(0).startY;
      int prevWidth = listOfMotion.get(0).startWidth;
      int prevHeight = listOfMotion.get(0).startHeight;
      Color prevColor = listOfMotion.get(0).startColor;
      for (Motion m : listOfMotion) {
        if (m.startTime != prevEndTime) {
          throw new IllegalArgumentException("added an overlapping or gaping motion");
        }
        if (m.startX != prevX || m.startY != prevY || m.startWidth != prevWidth ||
            m.startHeight != prevHeight || !m.startColor.equals(prevColor)) {
          throw new IllegalArgumentException("shape motion must be continuous");
        }
        prevEndTime = m.endTime;
        prevX = m.endX;
        prevY = m.endY;
        prevWidth = m.endWidth;
        prevHeight = m.endHeight;
        prevColor = m.endColor;
      }
    }
  }

  public List<IShape> update(int tick) {
    List<IShape> newShapes = new ArrayList<>();
    for(List<Motion> lom : nameMotion.values()) {
      for(Motion m : lom) {
        if (tick >= m.startTime && tick <= m.endTime) {
          newShapes.add(m.executeMotion());
        }
      }
    }
    return newShapes;
  }

  private void addShape(Motion m) {
    String key = m.name;
    if (!shapes.containsKey(key)) {
      shapes.put(key,
          new SimpleShape(key, nameType.get(m.type), m.startX, m.startY, m.startWidth,
              m.startHeight, m.startColor));
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
  // view
  // key frame
  // check window bounds

  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    Map<String, IShape> shapes;
    Map<String, List<Motion>> nameMotion;
    Map<String, String> nameType;
    Map<Integer, List<IShape>> tickShapeList;
    int leftMostX;
    int topMostY;
    int animationWidth;
    int animationHeight;


    @Override
    public AnimatorModel build() {
      return new AnimatorModelImpl(shapes, nameMotion, nameType,
          leftMostX, topMostY, animationWidth, animationHeight, tickShapeList);
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

      if (this.nameMotion == null || this.shapes == null ||this.tickShapeList == null) {
        this.nameMotion = new LinkedHashMap<>();
        this.shapes = new LinkedHashMap<>();
        this.tickShapeList = new LinkedHashMap<>();
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


