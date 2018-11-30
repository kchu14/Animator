package cs3500.animator.provider.model;


/**
 * Maps an object of one type to another. Written to remove dependency on javafx.util.pair.
 *
 * @param <U> the "key" object
 * @param <V> the "value" object
 */
public interface Tuple<U, V> {

  /**
   * Gets the first object of this Tuple.
   *
   * @return a reference to the "key" object
   */
  public U getKey();

  /**
   * Gets the second object of this Tuple.
   *
   * @return a reference to the "value" object
   */
  public V getValue();
}
