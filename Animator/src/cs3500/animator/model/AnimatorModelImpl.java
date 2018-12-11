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
 * CHANGE LOG Added implementation for the add / remove shape and motion methods. Also added a
 * modify motion method. These methods were added to support editable view functionality.
 */

/**
 * This class represents an animator model and implements all of its associated operations.
 * Operations include, producing the text view ouput, building the model and checking for valid
 * inputs (motions are correct and not overlapping on the same shape).
 */
public class AnimatorModelImpl implements AnimatorModel {

  // shape name, shape object
  private Map<String, IShape> shapes;
  // shape name, list of motions for the shape
  private Map<String, List<IMotion>> nameMotion;
  // shape name, shape type
  private Map<String, String> nameType;
  private int leftMostX;
  private int topMostY;
  private int animationWidth;
  private int animationHeight;
  private List<Integer> tickList;
  private Map<Integer, List<IShape>> tickListShapes;
  private Map<String, List<IMotion>> keyFrames;


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
      Map<String, List<IMotion>> nameMotion,
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
    Collections.sort(tickList);
    this.setTicks();

  }


  @Override
  public int getLastTick() {
    return tickList.get(tickList.size() - 1);
  }

  @Override
  public int getFirstTick() {
    return tickList.get(0);
  }

  @Override
  public Map<Integer, List<IShape>> getTickListShapes() {
    return new LinkedHashMap<>(this.tickListShapes);
  }

  @Override
  public Map<String, List<IMotion>> getKeyFrames() {
    if (keyFrames == null) {
      keyFrames = new LinkedHashMap<>();
    } else {
      keyFrames.clear();
    }
    for (Entry<String, List<IMotion>> set : nameMotion.entrySet()) {
      List<IMotion> result2 = new ArrayList<>();
      int n = set.getValue().size() - 1;
      int i = 0;
      for (IMotion m : set.getValue()) {
        result2.add(
            new Motion(m.getName(), m.getType(), m.getStartTime(), m.getStartX(), m.getStartY(),
                m.getStartWidth(),
                m.getStartHeight(),
                m.getStartColor(),
                m.getStartTime(), m.getStartX(), m.getStartY(), m.getStartWidth(),
                m.getStartHeight(), m.getStartColor()));

        if (i == n && (i != 0 || m.getStartTime() != m.getEndTime())) {

          result2.add(
              new Motion(m.getName(), m.getType(), m.getEndTime(), m.getEndX(), m.getEndY(),
                  m.getEndWidth(), m.getEndHeight(),
                  m.getEndColor(),
                  m.getEndTime(), m.getEndX(), m.getEndY(), m.getEndWidth(), m.getEndHeight(),
                  m.getEndColor()));
        }

        i++;
      }

      if (result2.size() > 1 && result2.get(0).getStartTime() == result2.get(1).getStartTime()) {
        result2.remove(0);
      }
      keyFrames.put(set.getKey(), result2);
    }
    return keyFrames;
  }

  /**
   * Checks if any of the motions input into this animation are invalid. This means if they overlap
   * or have gaps in between motions.
   */
  private void checkForValidMotions() {
    try {
      for (List<IMotion> listOfMotion : nameMotion.values()) {
        if (!listOfMotion.isEmpty()) {
          Collections.sort(listOfMotion);
          this.addShape(listOfMotion.get(0));
          int prevEndTime = listOfMotion.get(0).getStartTime();
          int prevX = listOfMotion.get(0).getStartX();
          int prevY = listOfMotion.get(0).getStartY();
          int prevWidth = listOfMotion.get(0).getStartWidth();
          int prevHeight = listOfMotion.get(0).getStartHeight();
          Color prevColor = listOfMotion.get(0).getStartColor();

          for (IMotion m : listOfMotion) {
            if (m.getStartTime() != prevEndTime) {
              throw new IllegalArgumentException("added an overlapping or gaping motion");
            }
            if (m.getStartX() != prevX || m.getStartY() != prevY || m.getStartWidth() != prevWidth
                || m.getStartHeight() != prevHeight || !m.getStartColor().equals(prevColor)) {
              throw new IllegalArgumentException("shape motion must be continuous");
            }
            prevEndTime = m.getEndTime();
            prevX = m.getEndX();
            prevY = m.getEndY();
            prevWidth = m.getEndWidth();
            prevHeight = m.getEndHeight();
            prevColor = m.getEndColor();
          }
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("did not add motion or shape correctly");
    }
  }


  /**
   * Updates the model by executing all animations on all the shapes per a given tick.
   *
   * @param tick the given tick (time of the animation)
   */
  private void update(int tick) {
    List<IShape> newShapes = new ArrayList<>();
    if (nameMotion == null) {
      throw new IllegalArgumentException("no motions created");
    }
    for (List<IMotion> lom : nameMotion.values()) {
      for (IMotion m : lom) {
        if (tick >= m.getStartTime() && tick <= m.getEndTime()) {
          newShapes.add(m.executeMotion(tick));
        }
      }
    }
    tickListShapes.put(tick, newShapes);
  }

  /**
   * Loops through the animation from start to finish to produce a map of ticks and all the shapes
   * drawn on each tick.
   */
  private void setTicks() {
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
  public void addShape(IMotion im) {
    Motion m = (Motion) im;
    String key = m.name;
    if (!shapes.containsKey(key)) {
      shapes.put(key,
          new SimpleShape(key, nameType.get(m.name), m.startX, m.startY, m.startWidth,
              m.startHeight, m.startColor));
    }
  }

  @Override
  public void declareNewShape(IShape ishape) {
    SimpleShape shape = (SimpleShape) ishape;
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
  }

  @Override
  public void addNewMotion(IMotion im) {
    List<IMotion> lom = nameMotion.get(im.getName());
    if (lom == null) {
      lom = new ArrayList<>();
      lom.add(im);
      nameMotion.put(im.getName(), lom);
      tickList.add(im.getEndTime());
      Collections.sort(tickList);
      return;
    }
    Collections.sort(lom);
    for (int i = 0; i < lom.size(); i++) {
      System.out.println(lom.get(i).getStartTime());
      IMotion curMotion = lom.get(i);
      if (i == 0 && im.getStartTime() < curMotion.getStartTime()) {
        im.fixEndings(lom.get(0));
      } else if (i == lom.size() - 1 && curMotion.getEndTime() < im.getStartTime()) {
        im.fixBeginning(curMotion);
      } else if (curMotion.getStartTime() < im.getStartTime()
          && curMotion.getEndTime() > im.getStartTime() && i == lom.size() - 1) {
        im.fixBeginning(lom.get(i - 1));
        curMotion.fixBeginning(im);
      } else if (curMotion.getStartTime() < im.getStartTime()
          && curMotion.getEndTime() > im.getStartTime() && i < lom.size() - 1) {
        IMotion nextMotion = lom.get(i + 1);
        curMotion.fixEndings(im);
        im.fixEndings(nextMotion);
      }
    }
    lom.add(im);
    nameMotion.put(im.getName(), lom);
    checkForValidMotions();
    tickList.add(im.getEndTime());
    Collections.sort(tickList);
    setTicks();
  }

  @Override
  public void editMotion(IMotion im) {
    Motion newMotion = (Motion) im;
    for (int i = 0; i < nameMotion.get(newMotion.name).size(); i++) {
      Motion m = (Motion) nameMotion.get(newMotion.name).get(i);
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
    tickList.add(newMotion.endTime);
    Collections.sort(tickList);
    checkForValidMotions();
    setTicks();
  }

  @Override
  public void removeMotion(IMotion im) {
    Motion keyframe = (Motion) im;
    String name = keyframe.name;
    if (this.nameMotion.containsKey(name)) {
      List<IMotion> lom = nameMotion.get(name);
      if (lom.size() == 1) {
        if (lom.get(0).getStartTime() == lom.get(0).getEndTime() && keyframe.startTime == lom
            .get(0).getStartTime()) {
          nameMotion.get(name).remove(lom.get(0));
          //checkForValidMotions();
          tickList.remove((Integer) keyframe.endTime);
          setTicks();
          return;
        }
        if (keyframe.startTime == lom.get(0).getStartTime()) {
          lom.get(0).becomesKeyframe(true);
        } else {
          lom.get(0).becomesKeyframe(false);
        }
        checkForValidMotions();
        tickList.remove((Integer) keyframe.endTime);
        setTicks();
        return;
      }

      for (int i = 0; i < lom.size(); i++) {
        IMotion motion = lom.get(i);
        if (i == 0 && motion.compareTo(keyframe) == 0) {
          nameMotion.get(name).remove(motion);
        } else if (motion.getStartTime() == keyframe.startTime) {
          if (i < lom.size() - 1) {
            lom.get(i - 1).fixEndings(lom.get(i + 1));
          } else {
            lom.get(i - 1).fixLastEndings(motion);

          }
          nameMotion.get(name).remove(motion);
        } else if (motion.getEndTime() == keyframe.startTime && i == lom.size() - 1) {
          nameMotion.get(name).remove(motion);
        }
      }
      tickList.remove((Integer) keyframe.endTime);
      checkForValidMotions();
      setTicks();
    }

  }

  @Override
  public Map<String, List<IMotion>> getMotions() {
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
    private Map<String, List<IMotion>> nameMotion;
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
        tickList.add(t1);
      }
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
          List<IMotion> listOfMotion = new ArrayList<>();
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


