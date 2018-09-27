package laboratory09a.fillingcrates;

 

/**
 * This class is a specialisation of {@link AbstractFit}
 * and it manages placing items into <em>containers</em>
 * using WorstFit algorithm - an item is placed in a crate
 * with greatest spare capacity, or in a new crate
 * if it is too heavy for any existing crate.<br>
 * Class tested, works as expected. No bugs found.
 * 
 * @version 1.0
 * @author Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>28 Oct 2014</center>
 * @see AbstractFit
 * @see AbstractFit#containers
 */
public class WorstFit extends AbstractFit {
	/**
	 * <code>WorstFit()</code>
	 * <p>
	 * This constructor creates WorstFit specialisation of
	 * {@link AbstractFit} object.
	 */
	public WorstFit() {	super("worst-fit"); }

	/**
	 * <code>addAmount(int amount)</code>
	 * <p>
	 * This method adds <tt>amount</tt> to a {@link Crate}.
	 * It instantiates new <tt>Crate</tt> if <tt>container</tt>
	 * is empty or <em>worst-fit</em> <tt>Crate</tt> cannot store
	 * <tt>amount</tt>.
	 * 
	 * @param amount to add;
	 *
	 * @see AbstractFit#containers
	 * @see AbstractFit#SIZE
	 * @pre <tt>amount</tt> must be in the range of 0..SIZE.
	 */
	@Override
	public void addAmount(int amount) {
      // Amount must be in the range:
      if (amount < 0 || SIZE < amount ) return;
      if (this.containers.isEmpty())
          this.containers.add(new Crate(SIZE));

      try {
          findWorstFit().store(amount);
      } catch (InsufficientCapacityException e) {
          // if storing in current best-fit crate failed:
          this.containers.add(new Crate(SIZE));
          this.addAmount(amount);
      }
	}

	/**
	 * <code>{@link FillableContainer} findWorstFit()</code>
	 * <p>
	 * This is helper method. 
	 * It searches for a crate using <em>WorstFit</em> algorithm.
	 * 
	 * @return worst-fit crate in a <em>container</em>.
	 * @see WorstFit
	 * @see AbstractFit#containers
	 */
	private FillableContainer findWorstFit() {
		FillableContainer worstFit = 
			this.containers.get(this.numContainers() - 1);
		
    // In the case, that worst-fit crate is found:
		if (worstFit.isEmpty()) return worstFit;
			
		for (FillableContainer crate : this.containers) 
			if (crate.getAvailableCapacity() > worstFit.getAvailableCapacity())
				worstFit = crate;
		
		return worstFit;
	}
}
