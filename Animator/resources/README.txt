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

We implemented our Editable view by creating a window that contained two panels. One of these panels was our visualGraphicsView output of the model, and the other view was the edit panel. This panel consisted of several buttons, fields, and lists, that each had some action associated with them in our controller.

Our editable view allows for the user to add keyframes before or after the first or last 
Keyframe on a shape. We did this because it seemed to provide the user with the most
funcionality. This allows the user to add a shape and fill out all the keyframes for the
Shape. A user could create a whole animation from a blank animation if wanted.

Our controller for the editable view is an actionListener that has an AnimatorModel and an EditableView. Since this controller
is an action listener, it is able to take the input from the editable view and call methods that recreate our lists of keyframes.
We chose to have our keyframes be constantly reproduced so that our ReadOnlyModel could be able to get those values and keep
them immutable. We valued immutability for our read-only model higher than performance, but If we were to allow the model
to be directly sent to the view and encourage mutation within that, it would be able to produce the larger files much more quickly.

CHANGE LOG assignment 5-6
- Removed function objects (commands) which were being used to manipulate shapes
- Removed complex shape class which was unnecessary for assignments 5 and 6
- Added removeMotion and removeShape methods as per request of the grader
- Added checkOverlaps to the build() method to enforce class invariants.
- Added a motion class to represent changes in our shapes as opposed to commands.
- Modified our SimpleShape and Motion classes to output themselves in a formatted string based on SVG

CHANGE LOG Assignment 6-7
-Added the controller class and interface to allow the view to pass inputs to the
 model. The controller then updates the view with the new model information.
We made this change because the new assignment allows for modification of the model.
The user is allowed to mutate the model in this assignment so we needed a controller 
To pass the necessary modifications to the model to be changed.

-Added implementation for the add / remove shape and motion methods in our modelImpl class. Also added a
 modify motion method. These methods were added to support editable view functionality.
We fully implemented these methods because the assignment finally specified the full
Functionality. We were able to mutate the model according to the user now.

-Added several methods to motion class that mutate the motion so that's endings /  beginnings
 match the given motion. We did this because we wanted the flexibility of keyframes while
 continuing to use our motion implementation.

-Created a read only model to be passed into the view. This allows the view to gather 
 the information from the model without mutation being allowed.

-Changed the timer to support the different editable view functions such as pause and
 fastforwards.
 
 -Added methods that allowed the panel to support functionality from the editable view.
 Functionality included fast forwarding and pause.
 