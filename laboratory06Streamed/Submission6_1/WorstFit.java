package laboratory06Streamed.Submission6_1;

import java.util.Comparator;

/**
 * Tested works as expected. No bugs found.
 * 
 * @author user
 *
 */
public class WorstFit extends AbstractFit {
	public WorstFit() { super("worst-fit"); }

	@Override
	public void addAmount(int amount) {
		Crate crate = (Crate) this.containers.stream()
							.filter(c -> c.getAvailableCapacity() >= amount)
							.max(Comparator.comparing(c -> c.getAvailableCapacity() - amount))
							.orElseGet(() -> new Crate(SIZE));
		
		try {
			crate.store(amount);
			if(!this.containers.contains(crate)) this.containers.add(crate);
		} catch (InsufficientCapacityException ice) {
			System.out.println(ice.getMessage());
		}
	}
}
