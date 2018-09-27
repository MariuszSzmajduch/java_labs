package laboratory06.Submission6_1;

public class Crate implements FillableContainer {
	
	private final int totalCapacity;
	private int availableCapacity, usedCapacity;
	
	public Crate(int totalCapacity) { 
		this.totalCapacity = this.availableCapacity = totalCapacity;
		this.usedCapacity = 0;}

	@Override
	public int getTotalCapacity() { return this.totalCapacity; }

	@Override
	public int getAvailableCapacity() { return this.availableCapacity; }

	@Override
	public int getUsedCapacity() { return this.usedCapacity; }

	@Override
	public boolean isEmpty() { return this.totalCapacity == this.availableCapacity; }

	@Override
	public void store(int size) throws InsufficientCapacityException {
		if(size > this.availableCapacity) 
			throw new InsufficientCapacityException(size, this.availableCapacity);
		this.availableCapacity -= size;
		this.usedCapacity += size;
	}
}
