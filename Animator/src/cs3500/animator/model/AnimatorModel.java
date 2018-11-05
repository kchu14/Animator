package cs3500.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface specifies the operations of a model. The model is what controls the functionality
 * of the animation.
 */
public interface AnimatorModel {


   String produceTextView();

   void checkForValidMotions();
}
