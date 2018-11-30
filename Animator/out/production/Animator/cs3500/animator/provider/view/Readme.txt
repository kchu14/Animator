These are the features we have working:

	All the basic animation controls: Pause/Play/Speed/Looping/Restart
	Selecting Shapes and Keyframes

These sort of work:

	Modifying Keyframes (Updates references but animation doesn't always reflect the changes made)
	Adding Shapes (Updates the model but doesn't show up on the JList)
	Adding Shapes (Same issue)
	Adding Keyframes (Doesn't work if adding to a tick after end of animation)
	Deleting keyframes (Updates references but doesn't show how animaiton is displayed, also throws a lot of errors and I don't know why)

We'll work to fix the problems ASAP


	ShapeTuple implements Tuple<String, Shape>:
  /**
   * Maps a string to the reference of a Shape.
   */

  /**
     * Constructs an animation tuple given an animation and start tick.
     *
     * @param name string to be mapped to the reference of the shape
     * @param shape shape being named
     */
    public ShapeTuple(String name, Shape shape)

  public String getKey()
  public Shape getValue()
  public boolean equals(Object o)

  /**
   * Represents an animation and its location in time relative to the model's scale.
   */
  AnimationTuple implements Tuple<Animation, Integer>:

  /**
     * Constructs an animation tuple given an animation and start tick.
     *
     * @param animation animation for tuple
     * @param startTick start tick for animation
     */
    public AnimationTuple(Animation animation, Integer startTick)

  public Animation getKey()
  public Integer getValue()
  public boolean equals(Object o)




  KeyFrame implements Tuple<ShapeTuple, Integer>:
  /**
     * Constructs an animation tuple given an animation and start tick.
     *
     * @param shape shapeTuple for tuple
     * @param tick tick at which the shape exists in this form
     */
    public Keyframe(ShapeTuple shape, Integer tick)



  //// getframes

  getFrames returns a list keyframes. A keyframe represents a defining frame in an animation. IE show animation as list of (tick, shape) and then when you get the animation state it interpolates between the shapes in the keyfames.

  ///// getTextRepresentation

  returns a string representing the animation at tick n (remember the hw with the text view)



  SHAPE INTERFACE + Classes

  represents a shape. we implemented a couple of shape classes:

  /**
     * Constructs an Ellipse given both radii, center point, and color.
     *
     * @param horizontalRadius horizontal radius
     * @param verticalRadius vertical radius
     * @param anchor center point
     * @param color color of shape
     */
    public Ellipse(int horizontalRadius, int verticalRadius, Point anchor, Color color)

  /**
     * Constructs a Circle given radius, anchor point, and color.
     *
     * @param radius int radius
     * @param anchor center point
     * @param color color of shape
     */
    public Circle(int radius, Point anchor, Color color)

  /**
     * Constructs a rectangle given anchor point, width, height, and color.
     *
     * @param width int width
     * @param height int height
     * @param anchor anchor point
     * @param color color of shape
     */
    public Rectangle(int width, int height, Point anchor, Color color)

  /**
     * Construct a square given side length, anchor point, and color.
     *
     * @param size side length
     * @param anchor anchor point
     * @param color color of shape
     */
    public Square(int size, Point anchor, Color color)
