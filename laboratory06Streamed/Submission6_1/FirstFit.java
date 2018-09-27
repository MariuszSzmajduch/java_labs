package laboratory06Streamed.Submission6_1;

/**
 * Tested works as expected. No bugs found.
 * 
 * @author user
 *
 */
public class FirstFit extends AbstractFit{	
	public FirstFit() { super("first-fit"); }

	@Override
	public void addAmount(int amount) {
		Crate crate = (Crate) this.containers.stream()
				.filter(c -> c.getAvailableCapacity() >= amount)
				.findFirst()
				.orElseGet(() -> new Crate(SIZE));
		
		try { 
			crate.store(amount); 
			if(!this.containers.contains(crate)) this.containers.add(crate);
		} catch (InsufficientCapacityException ice) {
			System.out.println(ice.getMessage()); 
		}
	}
}
