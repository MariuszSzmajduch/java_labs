package laboratory09a.fillingcrates;

 

/**
 * This class is a specialisation of {@link AbstractFit} and it manages placing
 * items into <em>containers</em> using FirstFit algorithm - an item is placed
 * in the first non-empty crate that will accomodate it, or in a new crate if it
 * is too heavy for any existing crate.<br>
 * Class tested, works as expected. No bugs found.
 * 
 * @version 1.0
 * @author Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>28 Oct 2014</center>
 * @see AbstractFit
 * @see AbstractFit#containers
 */
public class FirstFit extends AbstractFit {

  /**
   * <code>FirstFit()</code>
   * <p>
   * This constructor creates FirstFit specialisation of {@link AbstractFit}
   * object.
   */
  public FirstFit() {
    super("first-fit");
  }

  /**
   * <code>addAmount(int amount)</code>
   * <p>
   * This method adds <code>amount</code> to a {@link FillableContainer} using
   * <em>FirstFit</em> algorithm.<br>
   * It instantiates new <code>Crate</code> if <em>containers</em> list is empty
   * or no existing <code>Crate</code> can store <code>amount</code>
   *
   * @param amount to add;
   * 
   * @see FirstFit
   * @see AbstractFit#containers
   * @see AbstractFit#SIZE
   * @pre <code>amount</code> must be in the range of [0 - SIZE].
   */
  @Override
  public void addAmount(int amount) {
    // Amount must be in the range
    if (amount < 0 || SIZE < amount) return;

    // Try to store() into existing crate.
    // Exception thrown by the callee controls the flow.
    // Loop terminates when first crate able to accommodate is found,
    // or...
    for (int i = 0; i <= this.numContainers();) {
      try {
        // ...new crate is created if no crate can store
        if (i == this.numContainers() || this.containers.isEmpty())
          this.containers.add(new Crate(SIZE));
        this.containers.get(i).store(amount);
        return;
      } catch (InsufficientCapacityException e) {
        i++;
      }
    }
  }
}
