package cs3500.animator.provider.view;

import cs3500.animator.provider.model.AnimationImp;
import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.ShapeTuple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProviderController implements ExcelAnimatorController {

  private Double speed;
  private ExcelAnimatorModel model;
  private EditorView view;
  private int currentTick = 0;
  private boolean isPlaying = true;
  private boolean playForward = true;
  private boolean isLooping = true;
  private boolean isOn = true;

  public ProviderController(ExcelAnimatorModel model, Double speed) {
    this.model = model;
    //this.view = view;
    this.speed = speed;
  }

  public void setView(EditorView view) {
    this.view = view;
  }


  public void play() {
    List<Shape> shapes;
    while (this.isOn) {
      shapes = this.model.getAnimationState(currentTick);
      view.setShapes(shapes);
      shapes = this.model.getAnimationState(currentTick);
      view.setShapes(shapes);
      view.repaint();
      try {
        TimeUnit.MILLISECONDS.sleep(Math.round((1.0 / this.speed) * 1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (this.isPlaying) {
        if (this.playForward) {
          currentTick++;
        } else {
          currentTick--;
        }
      }

      if (currentTick == 0 || currentTick == this.model.getLastTick()) {
        if (!this.isLooping) {
          this.isPlaying = false;
        }
      }
      if (currentTick < 0 || currentTick > this.model.getLastTick()) {
        this.restart();
      }
    }
  }


  /**
   * Mutates this view's "currently playing" field to indicate whether the preview should be paused
   * or playing.
   */
  public void playPause() {
    boolean current = this.isPlaying;
    this.isPlaying = !current;
    if (!current) {
      view.setPlayPauseText("Pause");
    } else {
      view.setPlayPauseText("Play");
    }
  }

  public double getSpeed() {
    return this.speed;
  }


  /**
   * Mutates this view's "rewind/forward" field to the opposite of its current value to indicate
   * whether the preview should be playing backwards.
   */
  public void toggleDirection() {
    boolean current = this.playForward;
    this.playForward = !current;
  }

  /**
   * Mutates this view's "loop" field to indicate the opposite of its current value: Either that the
   * preview should reset to the initial tick and continue playing from there once it reaches its
   * end or to stop once it reaches its end.
   */
  public void toggleLooping() {
    boolean current = this.isLooping;
    this.isLooping = !current;
  }

  /**
   * Mutates this view's "current tick" field to the initial frame depending on which direction the
   * animation is playing.
   */
  public void restart() {
    if (playForward) {
      this.currentTick = 0;
    } else {
      this.currentTick = model.getLastTick();
    }
  }

  /**
   * Increments this view's "current tick" field by 1 if it was playing forwards, decrements
   * otherwise. Pauses the animation if it was playing.
   */
  public void stepForward() {
    this.isPlaying = false;
    view.setPlayPauseText("Play");
    if (playForward) {
      this.currentTick++;
    } else {
      this.currentTick--;
    }
  }

  /**
   * Decrements this view's "current tick" field by 1 if it was playing forwards, increments
   * otherwise. Pauses the animation if it was playing.
   */
  public void stepBack() {
    this.isPlaying = false;
    view.setPlayPauseText("Play");
    if (playForward) {
      this.currentTick--;
    } else {
      this.currentTick++;
    }
  }

  /**
   * Mutates this view's speed field to reflect what the user enters, does nothing if the entered
   * speed is <= 0. (Throwing an exception here would be non-ideal)
   *
   * @param newSpeed the new ticks/second the animation should play at
   */
  public void setSpeed(double newSpeed) {
    if (newSpeed > 0.0) {
      this.speed = newSpeed;
    }
    view.setSpeedText(newSpeed);
  }


  public Keyframe addKeyFrame(ShapeTuple shapeTuple, int tick) {
    model.addAnimation(new AnimationTuple(new AnimationImp(shapeTuple), tick));
    return new Keyframe(shapeTuple, tick);
  }

  public void deleteKeyFrame(ShapeTuple shapeTuple, Keyframe keyframe) {
    model.removeAnimation(new AnimationTuple(new AnimationImp(shapeTuple), keyframe.getValue()));
  }

  public void addShape(ShapeTuple shapeTuple) {
    this.model.addShape(shapeTuple);
  }

  public void removeShape(String key) {
    this.model.removeShape(key);
  }

  public void updateKeyframeOfAnimation(ShapeTuple updatedShape, int tickOfFrameToBeModified) {
    this.model.updateKeyframeOfAnimation(updatedShape, tickOfFrameToBeModified);
  }

  public ExcelAnimatorModel getModel() {
//    List<AnimationTuple> animations = new ArrayList<>();
//    for (ShapeTuple shapeTuple : this.model.getShapes()) {
//      for (AnimationTuple animationTuple : this.model.getMotionsOfShape(shapeTuple.getKey())) {
//        animations.add(animationTuple);
//      }
//    }
    return model;
  }
}
