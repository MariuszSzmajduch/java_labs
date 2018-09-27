package laboratory09a.fillingcrates;

 

/**
 * This class is a specialisation of {@link AbstractFit} and it manages placing
 * items into <em>containers</em> using BestFit algorithm - an item is placed in
 * a crate whose spare capacity is nearest to the weight of the item, or in a
 * new crate if it is too heavy for any existing crate.<br>
 * Class tested, works as expected. No bugs found.
 * 
 * @version 1.0
 * @author Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>28 Oct 2014</center>
 * @see AbstractFit
 * @see AbstractFit#containers
 */
public class BestFit extends AbstractFit {
    /**
     * <code>BestFit()</code>
     * <p>
     * This constructor creates BestFit specialisation of {@link AbstractFit}
     * object.
     */
    public BestFit() {
        super("best-fit");
    }

    /**
     * <code>addAmount(int amount)</code>
     * <p>
     * This method adds <tt>amount</tt> to a {@link Crate}. It instantiates new
     * <tt>Crate</tt> if <tt>container</tt> is empty or <em>best-fit</em>
     * <tt>Crate</tt> cannot store <tt>amount</tt>.
     * 
     * @param amount
     *            to add;
     *
     * @see AbstractFit#containers
     * @see AbstractFit#SIZE
     * @pre 0 =< amount && amount <= SIZE
     */
    @Override
    public void addAmount(int amount) {
        // Amount must be in the range:
        if (amount < 0 ||  SIZE < amount ) return;
        if (this.containers.isEmpty())
            this.containers.add(new Crate(SIZE));

        try {
            findBestFit(amount).store(amount);
        } catch (InsufficientCapacityException e) {
            // if storing in current best-fit crate failed:
            this.containers.add(new Crate(SIZE));
            this.addAmount(amount);
        }
    }

    /**
     * <code>{@link FillableContainer} findBeststFit()</code>
     * <p>
     * This is helper method. It searches for a crate using <em>BestFit</em>
     * algorithm.
     * 
     * @return best-fit crate in <em>container</em>.
     * @see BestFit
     * @see AbstractFit#containers
     */
    private FillableContainer findBestFit(int amount) {
        FillableContainer bestFit = 
                this.containers.get(this.numContainers() - 1);
        if (bestFit.isEmpty()) return bestFit; // see (1)

        int fit = SIZE;
        for (FillableContainer crate : this.containers) {
            int currFit = crate.getAvailableCapacity() - amount;

            if (0 <= currFit && currFit < fit) { // see (2)
                bestFit = crate;
                if ((fit = currFit) == 0) return bestFit; // Early exit
            }
        }
        return bestFit;
        /*
         (1) It is tricky: 
              If bestFit is empty, then:
               - either it is the only crate in containers;
               - or it is the last crate created by catch block
                 and other crates cannot store.
              In both cases best-fit crate is found.
         
          (2) - updates if lower +ve fit found */
    }
}
