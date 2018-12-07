package cs3500.animator.model.adapters;

import cs3500.animator.provider.model.AnimationImp;
import cs3500.animator.provider.model.AnimationTuple;
import cs3500.animator.provider.model.ExcelAnimatorModel;
import cs3500.animator.provider.model.Keyframe;
import cs3500.animator.provider.model.Shape;
import cs3500.animator.provider.model.ShapeTuple;
import cs3500.animator.provider.view.EditorView;
import cs3500.animator.provider.view.ExcelAnimatorController;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Adapts the provider's controller class to work with our model.
 */
public class ProviderController implements ExcelAnimatorController {

  private Double speed;
  private ExcelAnimatorModel model;
  private EditorView view;
  private int currentTick = 0;
  private boolean isPlaying = true;
  private boolean playForward = true;
  private boolean isLooping = true;
  private boolean isOn = true;

  /**
   * Constructs a controller for this animator.
   * @param model The model being used for this animation.
   * @param speed The speed of this animation passed in from the command line arguments.
   */
  public ProviderController(ExcelAnimatorModel model, Double speed) {
    this.model = model;
    this.speed = speed;
  }

  @Override
  public void setView(EditorView view) {
    this.view = view;
  }

  @Override
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


  @Override
  public void playPause() {
    boolean current = this.isPlaying;
    this.isPlaying = !current;
    if (!current) {
      view.setPlayPauseText("Pause");
    } else {
      view.setPlayPauseText("Play");
    }
  }

  @Override
  public double getSpeed() {
    return this.speed;
  }


  @Override
  public void toggleDirection() {
    boolean current = this.playForward;
    this.playForward = !current;
  }

  @Override
  public void toggleLooping() {
    boolean current = this.isLooping;
    this.isLooping = !current;
  }

  @Override
  public void restart() {
    if (playForward) {
      this.currentTick = 0;
    } else {
      this.currentTick = model.getLastTick();
    }
  }

  @Override
  public void stepForward() {
    this.isPlaying = false;
    view.setPlayPauseText("Play");
    if (playForward) {
      this.currentTick++;
    } else {
      this.currentTick--;
    }
  }

  @Override
  public void stepBack() {
    this.isPlaying = false;
    view.setPlayPauseText("Play");
    if (playForward) {
      this.currentTick--;
    } else {
      this.currentTick++;
    }
  }

  @Override
  public void setSpeed(double newSpeed) {
    if (newSpeed > 0.0) {
      this.speed = newSpeed;
    }
    view.setSpeedText(newSpeed);
  }

  @Override
  public Keyframe addKeyFrame(ShapeTuple shapeTuple, int tick) {
    model.addAnimation(new AnimationTuple(new AnimationImp(shapeTuple), tick));
    return new Keyframe(shapeTuple, tick);
  }

  @Override
  public void deleteKeyFrame(ShapeTuple shapeTuple, Keyframe keyframe) {
    model.removeAnimation(new AnimationTuple(new AnimationImp(shapeTuple), keyframe.getValue()));
  }

  @Override
  public void addShape(ShapeTuple shapeTuple) {
    this.model.addShape(shapeTuple);
  }

  @Override
  public void removeShape(String key) {
    this.model.removeShape(key);
  }

  @Override
  public void updateKeyframeOfAnimation(ShapeTuple updatedShape, int tickOfFrameToBeModified) {
    this.model.updateKeyframeOfAnimation(updatedShape, tickOfFrameToBeModified);
  }

  @Override
  public ExcelAnimatorModel getModel() {
    return model;
  }
}
