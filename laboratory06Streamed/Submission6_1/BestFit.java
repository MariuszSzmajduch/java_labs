package laboratory06Streamed.Submission6_1;

import java.util.Comparator;

/**
 * Tested works as expected. No bugs found.
 * 
 * @author user
 *
 */
public class BestFit extends AbstractFit {
	public BestFit() { super("best-fit"); }

	@Override
	public void addAmount(int amount) {
		Crate crate = (Crate) this.containers.stream()
				.filter(c -> c.getAvailableCapacity() >= amount)
				.min(Comparator.comparing(c -> c.getAvailableCapacity() - amount))
				.orElseGet(() -> new Crate(SIZE));
		if(!this.containers.contains(crate)) this.containers.add(crate);
	
		try { crate.store(amount); } 
		catch (InsufficientCapacityException e) { e.printStackTrace(); }
	}
}
