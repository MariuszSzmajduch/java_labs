package laboratory06.Submission6_1;

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
		int worstFitIndex = -1;
		int worstUsedCapacity = SIZE;
		
		for(FillableContainer crate : this.containers) {
			if(crate.getUsedCapacity() >= worstUsedCapacity) continue;
			try { 
				crate.store(amount);
				if(worstFitIndex >= 0) this.containers.get(worstFitIndex).store(-amount);
				worstFitIndex = this.containers.indexOf(crate);
				worstUsedCapacity = crate.getUsedCapacity() - amount; // compares values before store()
			} catch (InsufficientCapacityException ice) {}
		}
		if(worstFitIndex < 0) {
			this.containers.add(new Crate(SIZE));
			this.addAmount(amount);
		}
	}
}
