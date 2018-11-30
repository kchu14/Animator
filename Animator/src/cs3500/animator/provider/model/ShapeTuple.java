package cs3500.animator.provider.model;



public class ShapeTuple implements Tuple<String, Shape> {

  /**
   * Maps a string to the reference of a Shape.
   *
   * Constructs an animation tuple given an animation and start tick.
   *
   * @param name string to be mapped to the reference of the shape
   * @param shape shape being named
   */

  private String name;
  private Shape shape;

  /**
   * Constructs an animation tuple given an animation and start tick.
   *
   * @param name string to be mapped to the reference of the shape
   * @param shape shape being named
   */
  public ShapeTuple(String name, Shape shape) {
    this.name = name;
    this.shape = shape;
  }

  @Override
  public String getKey() {
    return this.name;
  }


  @Override
  public Shape getValue() {
    return this.shape;
  }



}