package laboratory09a.fillingcrates;

 

/**
 * This class represents a single crate with given capacity. 
 * It conforms to {@link FillableContainer} in order to:
 * <ul>
 * <li>store items properly;
 * <li>provide information about storing properties of the crate.
 * </ul>
 * Class tested, works as expected. No bugs found.
 * <p>
 * 
 * @version 1.0 
 * @author Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>28 Oct 2014</center>
 *         
 * @see FillableContainer
 */
public class Crate implements FillableContainer {
	
	private int totalCapacity;
	private int usedCapacity;
	
	/**
	 * default constructor.
	 */
	public Crate() { this(100); }
	
	/**
	 * <code>Crate (int size)</code>
	 * <p>
	 * This constructor creates new {@link Crate} with
	 * a capacity of given <tt>size</tt>.
	 * 
	 * @param size defines total capacity of the crate.
	 * @pre size > 0
	 */
	public Crate (int size) { this.totalCapacity = size; }

	// getters required by implemented interface.
	@Override
	public int getTotalCapacity() { return this.totalCapacity; }

	
	@Override
	public int getAvailableCapacity() {
		return this.totalCapacity - this.usedCapacity;
	}

	@Override
	public int getUsedCapacity() { return this.usedCapacity; }

	@Override
	public boolean isEmpty() { return this.usedCapacity == 0; }

	@Override
	public void store(int size) throws InsufficientCapacityException {
		if (size > this.getAvailableCapacity())
			throw new InsufficientCapacityException(size, this.getAvailableCapacity());
		this.usedCapacity += size;
	}
}
