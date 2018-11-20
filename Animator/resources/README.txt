This animator model represents a collection of animations. These animations are described by the shape that is being
acted on as well as the motion that the shape is doing.

Shapes have an interface called IShape that declares all of the possible methods available for each type of shape.
Currently there is only one class that extends this interface, SimpleShape. Simple Shape only supports rectangles and
ovals. However, it will be easy to make more complex shapes in the future because of this interface.

Motions are an object that contains the information denoting the keyframes of a shape. Additionally, it has methods
that mutate the shapes that the motion is acting upon.

Our main method constructs the view based on the command arguments. The main method then executes the motions and 
begins outputting to the view. 

We have three views that consist of a text view, svg view, and visual graphics view. They all implement the AnimatorView 
interface and contain the method playanimation which executes the animation. 

We implemented our text view by moving the method that output the "debug string" from the previous assignment into a new
"TextView" class. For SVG, we worked off of this text output and manipulated the string to fit the format given by mozilla.
We also used dynamic dispatch and allowed motions and shapes to output formatted text based off of their fields.
In regards to the visual view, we updated our list of shapes every tick, and utilized Java's Shape class to draw these
images onto a canvas.

CHANGE LOG assignment 5-6
- Removed function objects (commands) which were being used to manipulate shapes
- Removed complex shape class which was unnecessary for assignments 5 and 6
- Added removeMotion and removeShape methods as per request of the grader
- Added checkOverlaps to the build() method to enforce class invariants.
- Added a motion class to represent changes in our shapes as opposed to commands.
- Modified our SimpleShape and Motion classes to output themselves in a formatted string based on SVG

/**
 * CHANGE LOG Added the controller class and interface to allow the view to pass inputs to the
 * model. The controller then updates the view with the new model information.
 */

/**
 * CHANGE LOG Added implementation for the add / remove shape and motion methods. Also added a
 * modify motion method. These methods were added to support editable view functionality.
 */

/**
 * CHANGE LOG Added several methods that mutate the motion so that's endings /  beginnings
 * match the given motion. We did this because we wanted the flexibility of keyframes while
 * continuing to use our motion implementation.
 */

/**
 * CHANGE LOG Created a read only model to be passed into the view. This allows the view to gather 
 * the information from the model without mutation being allowed.
 */
s
/**
 * CHANGE LOG Changed the timer to support the different editable view functions such as pause and
 * fastforwards.
 */

/**
 * CHANGE LOG Added methods that allowed the panel to support functionality from the editable view.
 * Functionality included fast forwarding and pause.
 */
