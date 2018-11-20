package cs3500.animator.model;

import cs3500.animator.util.AnimationBuilder;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class represents an animator model and implements all of its associated operations.
 * Operations include, producing the text view ouput, building the model and checking for valid
 * inputs (motions are correct and not overlapping on the same shape).
 */
public class AnimatorModelImpl implements AnimatorModel {

  // shape name, shape object
  private Map<String, IShape> shapes;
  // shape name, list of motions for the shape
  private Map<String, List<Motion>> nameMotion;
  // shape name, shape type
  private Map<String, String> nameType;
  private int leftMostX;
  private int topMostY;
  private int animationWidth;
  private int animationHeight;
  private List<Integer> tickList;
  private Map<Integer, List<IShape>> tickListShapes;


  /**
   * Constructs an animator model using the builder pattern.
   *
   * @param shapes the map of shapes (name, shape)
   * @param nameMotion the map of motions (name, list of motions)
   * @param nameType the map of shape name and types (name, type)
   * @param leftMostX the left most x coordinate of the window
   * @param topMostY the top most y coordinate of the window
   * @param animationWidth the window width
   * @param animationHeight the window height
   * @param tickList the list of ticks (the time of each animation)
   */
  private AnimatorModelImpl(Map<String, IShape> shapes,
      Map<String, List<Motion>> nameMotion,
      Map<String, String> nameType, int leftMostX,
      int topMostY, int animationWidth, int animationHeight, ArrayList<Integer> tickList,
      Map<Integer, List<IShape>> tickListShapes) {
    this.shapes = shapes;
    this.nameMotion = nameMotion;
    this.nameType = nameType;
    this.leftMostX = leftMostX;
    this.topMostY = topMostY;
    this.animationWidth = animationWidth;
    this.animationHeight = animationHeight;
    this.tickList = tickList;
    this.tickListShapes = tickListShapes;
    this.checkForValidMotions();
    this.setTicks();
  }

  // todo testing (fix old tests),
  // todo documentation of changes, readme, jar file, java docs,


  @Override
  public int getLastTick() {
    Collections.sort(tickList);
    return tickList.get(tickList.size() - 1);
  }

  @Override
  public Map<Integer, List<IShape>> getTickListShapes() {

    return new LinkedHashMap<>(this.tickListShapes);
  }

  @Override
  public Map<String, List<Motion>> getKeyFrames() {
    Map<String, List<Motion>> result = new LinkedHashMap<>();
    for (Entry<String, List<Motion>> set : nameMotion.entrySet()) {
      List<Motion> result2 = new ArrayList<>();
      int n = set.getValue().size() - 1;
      int i = 0;
      for (Motion m : set.getValue()) {
        result2.add(
            new Motion(m.name, m.type, m.startTime, m.startX, m.startY, m.startWidth,
                m.startHeight,
                m.startColor,
                m.startTime, m.startX, m.startY, m.startWidth, m.startHeight, m.startColor));
        if (i == n && (i != 0 || m.getStartTime() != m.getEndTime())) {
          result2.add(
              new Motion(m.name, m.type, m.endTime, m.endX, m.endY, m.endWidth, m.endHeight,
                  m.endColor,
                  m.endTime, m.endX, m.endY, m.endWidth, m.endHeight, m.endColor));
        }

        i++;
      }
      for (int j = 1; j < result2.size(); j++) {
        if (result2.get(j).compareTo(result2.get(j - 1)) == 0) {
          result2.remove(j);
        }
      }

      result.put(set.getKey(), result2);
    }
    return result;
  }

  /**
   * Checks if any of the motions input into this animation are invalid. This means if they overlap
   * or have gaps in between motions.
   */
  private void checkForValidMotions() {
    try {
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
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("did not add motion or shape correctly");
    }
  }


  /**
   * Updates the model by executing all animations on all the shapes per a given tick.
   * @param tick the given tick (time of the animation)
   */
  private void update(int tick) {
    List<IShape> newShapes = new ArrayList<>();
    if (nameMotion == null) {
      throw new IllegalArgumentException("no motions created");
    }
    for (List<Motion> lom : nameMotion.values()) {
      for (Motion m : lom) {
        if (tick >= m.startTime && tick <= m.endTime) {
          newShapes.add(m.executeMotion(tick));
        }
      }
    }
    tickListShapes.put(tick, newShapes);
  }

  /**
   * Loops through the animation from start to finish to produce a map of ticks and all the
   * shapes drawn on each tick.
   */
  private void setTicks() {
    Collections.sort(tickList);
//    int firstTick = tickList.get(0);
    int lastTick = tickList.get(tickList.size() - 1);

    for (int i = 0; i <= lastTick; i++) {
      this.update(i);
    }
  }

  @Override
  public int getHeight() {
    return animationHeight;
  }

  @Override
  public int getWidth() {
    return animationWidth;
  }

  @Override
  public int getWindowX() {
    return leftMostX;
  }

  @Override
  public int getWindowY() {
    return topMostY;
  }


  @Override
  public void addShape(Motion m) {
    String key = m.name;
    if (!shapes.containsKey(key)) {
      shapes.put(key,
          new SimpleShape(key, nameType.get(m.name), m.startX, m.startY, m.startWidth,
              m.startHeight, m.startColor));
    }
  }

  @Override
  public void declareNewShape(SimpleShape shape) {
    if (this.nameType == null) {
      this.nameType = new LinkedHashMap<>();
    }
    if (this.nameType.containsKey(shape.name)) {
      throw new IllegalArgumentException("Shape names have to be unique");
    } else {
      this.nameType.put(shape.name, shape.type);
    }
  }

  @Override
  public void removeShape(String name) {
    this.shapes.remove(name);
    this.nameType.remove(name);
    this.nameMotion.remove(name);
    setTicks();
    System.out.println("removed shape " + name);
  }
  @Override
  public void addNewMotion(Motion newMotion) {
    List<Motion> lom = nameMotion.get(newMotion.name);
    if (lom == null) {
      lom = new ArrayList<>();
      lom.add(newMotion);
      nameMotion.put(newMotion.name, lom);
      return;
    }
    Collections.sort(lom);
    for (int i = 0; i < lom.size(); i++) {
      System.out.println(lom.get(i).getStartTime());
      Motion curMotion = lom.get(i);
      if (i == 0 && newMotion.getStartTime() < curMotion.getStartTime()) {
        newMotion.fixEndings(lom.get(0));
      } else if (i == lom.size() - 1 && curMotion.getEndTime() < newMotion.getStartTime()) {
        newMotion.fixBeginning(curMotion);
      } else if (curMotion.startTime < newMotion.startTime
          && curMotion.endTime > newMotion.startTime && i == lom.size() - 1) {
        newMotion.fixBeginning(lom.get(i - 1));
        curMotion.fixBeginning(newMotion);
      } else if (curMotion.startTime < newMotion.startTime
          && curMotion.endTime > newMotion.startTime && i < lom.size() - 1) {
        Motion nextMotion = lom.get(i + 1);
        curMotion.fixEndings(newMotion);
        newMotion.fixEndings(nextMotion);
      }
    }
    lom.add(newMotion);
    nameMotion.put(newMotion.name, lom);
    checkForValidMotions();
    tickList.add(newMotion.endTime);
    setTicks();
  }
  @Override
  public void editMotion(Motion newMotion) {
    for (int i = 0; i < nameMotion.get(newMotion.name).size(); i++) {
      Motion m = nameMotion.get(newMotion.name).get(i);
      if (m.getStartTime() == newMotion.getStartTime()) {
        m.changeTo(newMotion);
        if (i != 0) {
          nameMotion.get(newMotion.name).get(i - 1).fixEndings(m);
        }
      } else if (m.getEndTime() == newMotion.getStartTime()
          && i == nameMotion.get(newMotion.name).size() - 1) {
        m.fixEndings(newMotion);
      }
    }
    checkForValidMotions();
    tickList.add(newMotion.endTime);
    setTicks();
  }
  @Override
  public void removeMotion(Motion keyframe) {
    String name = keyframe.name;
    if (this.nameMotion.containsKey(name)) {
      List<Motion> lom = nameMotion.get(name);
      if(lom.size() == 1) {
        if(keyframe.startTime == lom.get(0).startTime) {
          lom.get(0).becomesKeyframe(true);
        }
        else {
          lom.get(0).becomesKeyframe(false);
        }
        checkForValidMotions();
        setTicks();
        return;
      }
      for (int i = 0; i < lom.size(); i++) {
        Motion motion = lom.get(i);
        if (i == 0 && motion.compareTo(keyframe) == 0) {
          nameMotion.get(name).remove(motion);
        } else if (motion.startTime == keyframe.startTime) {
          if (i < lom.size() - 1) {
            lom.get(i - 1).fixEndings(lom.get(i + 1));
          } else {
            lom.get(i - 1).fixLastEndings(motion);

          }
          nameMotion.get(name).remove(motion);
        } else if (motion.endTime == keyframe.startTime && i == lom.size() - 1) {
          nameMotion.get(name).remove(motion);
        }
      }
      checkForValidMotions();
      setTicks();
    }

  }

  @Override
  public Map<String, List<Motion>> getMotions() {
    return new LinkedHashMap<>(this.nameMotion);
  }

  @Override
  public Map<String, IShape> getShapes() {
    return new LinkedHashMap<>(this.shapes);
  }

  @Override
  public Map<String, String> getNameType() {
    return new LinkedHashMap<>(this.nameType);
  }

  /**
   * This class represents the builder pattern for building a animator model. The operations include
   * setting the bounds, adding motions, and adding shapes etc.
   */
  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    private Map<String, IShape> shapes;
    private Map<String, List<Motion>> nameMotion;
    private Map<String, String> nameType;
    private int leftMostX;
    private int topMostY;
    private int animationWidth;
    private int animationHeight;
    private ArrayList<Integer> tickList;
    private Map<Integer, List<IShape>> tickListShapes;


    @Override
    public AnimatorModel build() {
      AnimatorModel m = new AnimatorModelImpl(shapes, nameMotion, nameType,
          leftMostX, topMostY, animationWidth, animationHeight, tickList, tickListShapes);
      return m;
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
    /**
     * Additionally, this method checks if the shape already exists.
     */
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      if (this.nameType == null) {
        this.nameType = new LinkedHashMap<>();
      }
      if (this.nameType.containsKey(name)) {
        throw new IllegalArgumentException("Shape names have to be unique");
      } else {
        this.nameType.put(name, type);
        return this;
      }
    }

    @Override
    /**
     * Additionally, this method checks if the motion being added is a valid motion (no overlaps).
     */
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
        int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2,
        int b2) {
      if (tickList == null) {
        tickList = new ArrayList<>();
      }
      tickList.add(t1);
      tickList.add(t2);

      if (this.nameMotion == null || this.shapes == null || this.tickListShapes == null) {
        this.nameMotion = new LinkedHashMap<>();
        this.shapes = new LinkedHashMap<>();
        this.tickListShapes = new LinkedHashMap<>();
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
      this.addMotion(name, t, x, y, w, h, r, g, b, t, x, y, w, h, r, g, b);
      return this;
    }
  }
}


