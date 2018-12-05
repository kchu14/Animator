package cs3500.animator.provider.updatedView;
import cs3500.model.shape.ShapeTuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import cs3500.model.shape.Shape;


import java.awt.Color;

//CHANGES FROM HOMEWORK 5:
// Removed the List of shapes representing each step of the animation, as it was "storing transient
// information rather than the data worth saving," instead storing a "final shape" object and
// interpolating on the fly rather than at construction-time.
// Additionally, refactored to use ShapeTuples instead of Shapes as a way to replace our use of
// reference checks with a check of the name field of the ShapeTuples

/**
 * Represents the top-level implementation of the animation interface, using an ArrayList of Shapes
 * to represent the animation at each individual tick. Stores the original shape being animated upon
 * to prevent overlapping motions.
 */
public abstract class AbstractAnimation implements Animation {

  protected ArrayList<Keyframe> keyframes;

  /**
   * Constructor to create a static shape n frames long.
   *
   * @param originalShape original shape of animation
   * @param length length of animation
   */
  protected AbstractAnimation(ShapeTuple originalShape, int length, ShapeTuple finalShape) {
    this.keyframes = new ArrayList<>();

    if (originalShape == null || finalShape == null) {
      throw new IllegalArgumentException("One of the given Shapes was null");
    } else if (length < 0) {
      throw new IllegalArgumentException("Given Length was non-positive");
    }
    //System.out.println(this.originalShape);
    this.keyframes.add(new Keyframe(originalShape, 0));
    this.keyframes.add(new Keyframe(finalShape, length));
  }

  protected AbstractAnimation(ShapeTuple shape, int tick) {
    this.keyframes = new ArrayList<>();
    if (shape == null) {
      throw new IllegalArgumentException("One of the given Shapes was null");
    }
    this.keyframes.add(new Keyframe(shape, tick));
    this.keyframes.add(new Keyframe(shape, tick + 1));
  }

  /**
   * Gets a visual frame from this animation at frame n.
   *
   * @param n the tick at which the frame is being pulled
   * @param width the width of the canvas on which the frame would be drawn
   * @param height the height of the canvas on which the frame would be drawn
   * @return 2-D array of colors
   */
  @Override
  public Color[][] getVisualFrame(int n, int width, int height) {
    return this.getState(n, 0).getVisualRepresentation(width, height);
  }

  /**
   * Gets a textual representation of this animation at frame n.
   *
   * @param n the tick at which the frame is being pulled
   * @return string representation of frame at n
   */
  @Override
  public String getTextFrame(int n) {
    return this.getState(n, 0).getTextRepresentation();
  }

  /**
   * Get copy of shape at frame n through interpolation.
   *
   * @param n the tick at which the frame is being pulled
   * @return Shape at frame n
   */

  public Shape getState(int n, int startN) {
    //if (n < 0 || n >= this.length) {
    //throw new IllegalArgumentException("Given index was outside of the range of this animation");
    //}
    Keyframe startKeyframe = this.keyframes.get(0);
    Keyframe endKeyframe = this.keyframes.get(0);
    //System.out.println("frame length: " + this.getFrames().size());
    for (int i = 0; i < this.getFrames().size() - 1; i++) {
      //System.out.println("i: " + i);
      if (this.keyframes.get(i).getValue() <= n - startN && this.keyframes.get(i + 1).getValue() >= n - startN) {
        endKeyframe = this.keyframes.get(i + 1);
        startKeyframe = this.keyframes.get(i);
        //System.out.println("Lower Bound Keyframe: " + startKeyframe.getValue());
        //System.out.println("Upper Bound Keyframe: " + endKeyframe.getValue());
        //System.out.println("tick: " + n);
      }
    }
    Shape start = startKeyframe.getKey().getValue();
    Shape end = endKeyframe.getKey().getValue();

    int absoluteKeyFrameN = startN + startKeyframe.getValue();

    if (n < this.keyframes.get(0).getValue() ) {
      //System.out.println("below");
      return this.keyframes.get(0).getKey().getValue();
    }else if (n > this.keyframes.get(this.keyframes.size() - 1).getValue() + startN) {
      //System.out.println("above");
      return this.keyframes.get(this.keyframes.size() - 1).getKey().getValue();
    }



//    for (int i = 0; i < this.keyframes.size(); i++) {
//      if (this.keyframes.get(i).getValue() > n) {
//        start = this.keyframes.get(i - 1).getKey().getValue();
//        end = this.keyframes.get(i).getKey().getValue();
//        absoluteKeyFrameN = startN + this.keyframes.get(i - 1).getValue();
//      }
//    }

    double multiplier = (n - absoluteKeyFrameN);

    int length = endKeyframe.getValue() - startKeyframe.getValue();

    //System.out.println("multiplier: " + multiplier+ " absolutekeyframen: " + absoluteKeyFrameN +
    //       " startN: " + startN + " length: " + length);
    //Generates the value each field would be tweened each tick
    Double deltaWidth =
        ((end.getWidth() - start.getWidth()) / (length + 0.0)) * multiplier;
    Double deltaHeight =
        ((end.getHeight() - start.getHeight()) / (length + 0.0)) * multiplier;
    Double deltaX =
        ((end.getLocation().getX() - start.getLocation().getX()) / (length + 0.0))
            * multiplier;
    Double deltaY =
        ((end.getLocation().getY() - start.getLocation().getY()) / (length + 0.0))
            * multiplier;
    Double deltaRed =
        ((end.getColor().getRed() - start.getColor().getRed()) / (length + 0.0))
            * multiplier;
    Double deltaGreen =
        ((end.getColor().getGreen() - start.getColor().getGreen()) / (length + 0.0))
            * multiplier;
    Double deltaBlue =
        ((end.getColor().getBlue() - start.getColor().getBlue()) / (length + 0.0))
            * multiplier;
    //System.out.println(start.getColor().getRed());
    //System.out.println(end.getColor().getRed());
    //System.out.println(deltaRed);
    //System.out.println("getState n: " + n);
    //System.out.println("abstract animation delta width " + (deltaWidth.intValue()));
    //System.out.println("mutliplier: " + multiplier );

    return start
        .adjust(deltaWidth.intValue(), deltaHeight.intValue(),
            deltaX.intValue(),
            deltaY.intValue(), deltaRed.intValue(),
            deltaGreen.intValue(),
            deltaBlue.intValue());
  }

  @Override
  public void deleteFrame(Keyframe keyframe) {
    //System.out.println(this.keyframes);
    this.keyframes.remove(keyframe);
    //System.out.println(this.keyframes);

  }


  @Override
  public Keyframe addFrame(int tick, int index, int startN, ShapeTuple name) {
    //System.out.println(name);
    if (index >= 0) {
      try {
        this.keyframes.add(index,
                new Keyframe(new ShapeTuple(name.getKey(), this.getState(tick, startN)), tick));
      } catch (IllegalArgumentException ile) {
        this.keyframes.add(index,
                new Keyframe(new ShapeTuple(name.getKey(), this.getState(tick, startN)), tick));
      }
      return this.keyframes.get(index);
    }else if (this.keyframes.size() == 0) {
      //System.out.println(name.getValue().getTextRepresentation());
      this.keyframes.add(0, new Keyframe(name, tick));
      return this.keyframes.get(0);
    }else if (this.keyframes.size() == 1) {
      if (tick < this.keyframes.get(0).getValue()){
        this.keyframes.add(0,(new Keyframe(name, tick)));
        return this.keyframes.get(0);
      }else {
        this.keyframes.add(1, new Keyframe(name, tick));
        return this.keyframes.get(1);
      }
    }
    return this.keyframes.get(index + 1);
  }

  /**
   * Get length of animation.
   *
   * @return int length of animation
   */
  @Override
  public int getLength() {
    if (this.keyframes.isEmpty()) {
      return 0;
    } else {
      return this.keyframes.get(this.keyframes.size() - 1).getValue() - this.keyframes.get(0)
          .getValue();
    }
  }

  //Only must override .equals here since distinct animations (eg move vs scale) cannot be the same

  /**
   * Check if this animation is equivalent to another object.
   *
   * @param o object checking equality with
   * @return boolean true if objects are equivalent
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    } else if (!(o instanceof AbstractAnimation)) {
      return false;
    }
    AbstractAnimation that = (AbstractAnimation) o;
    return this.getLength() == that.getLength() && this.keyframes.get(0).getKey()
        .equals(that.getOriginalShape().getKey())
        && this.keyframes.get(this.keyframes.size() - 1).getKey()
        .equals(that.getFinalShape().getKey());
  }

  /**
   * Get hashcode of object.
   *
   * @return int hashcode of object
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getLength(), this.keyframes.get(0).getKey(),
        this.keyframes.get(this.keyframes.size() - 1).getKey());
  }

  /**
   * Get the original shape. DO WE USE THIS?
   *
   * @return Shape originally used to construct animation
   */
  @Override
  public ShapeTuple getOriginalShape() {
    return this.keyframes.get(0).getKey();
  }

  /**
   * Get the final shape. DO WE USE THIS?
   *
   * @return Shape generated as a result of this animation
   */
  @Override
  public ShapeTuple getFinalShape() {
    return this.keyframes.get(this.keyframes.size() - 1).getKey();
  }

  @Override
  public List<Keyframe> getFrames() {
    return this.keyframes;
  }

  @Override
  public void updateKeyframe(ShapeTuple shape, int tick) {
    int indexOfFrameToModify = -1;
    for (int i = 0; i < this.getFrames().size(); i += 1) {
      Keyframe frame = this.getFrames().get(i);
      //System.out.println("-------------------------------------------------------");
      //System.out.println("keyframe value: " + frame.getValue());
      //System.out.println("tick: " + tick);
      //System.out.println("i: " + i);
      if (frame.getValue() == tick) {
        indexOfFrameToModify = i;
      }
    }
    if (indexOfFrameToModify == -1) {
      throw new IllegalArgumentException("No Keyframe at given tick");
    }
    this.keyframes.set(indexOfFrameToModify, new Keyframe(shape, tick));
  }

}