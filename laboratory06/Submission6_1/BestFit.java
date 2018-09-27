package laboratory06.Submission6_1;

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
		int bestFitIndex = -1;
		int bestFit = SIZE;
		
		for(FillableContainer crate : this.containers) {
			if(crate.getAvailableCapacity() - amount >= bestFit) continue;
			try { 
				crate.store(amount);
				if(bestFitIndex >= 0) this.containers.get(bestFitIndex).store(-amount);
				if((bestFit = crate.getAvailableCapacity()) == 0) return; // early exit
				bestFitIndex = this.containers.indexOf(crate);
			} catch (InsufficientCapacityException ice) {}
		}
		// it was not possible to store
		if(bestFitIndex < 0) {
			this.containers.add(new Crate(SIZE));
			this.addAmount(amount);
		}
	}

}
