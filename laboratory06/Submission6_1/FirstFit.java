package laboratory06.Submission6_1;

/**
 * Tested works as expected. No bugs found.
 * The class is not optimised - redundant addAmount() and for-loop possible. Can be fixed by adding
 * new crates to the head of the list.
 * 
 * @author user
 *
 */
public class FirstFit extends AbstractFit{

	public FirstFit() { super("first-fit"); }

	@Override
	public void addAmount(int amount) {

		for(FillableContainer crate : this.containers) {
			try { crate.store(amount); return; } // we're done
			catch (InsufficientCapacityException ice) {}
		}
		this.containers.add(new Crate(SIZE)); // works also when container is empty
		this.addAmount(amount);
	}
}
