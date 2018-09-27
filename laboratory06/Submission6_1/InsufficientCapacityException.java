package laboratory06.Submission6_1;

/**
 * indicates when a container does not have
 * enough spare space to complete a store
 * operation successfully
 */
@SuppressWarnings("serial")
public class InsufficientCapacityException extends Exception {
    
    /**
     * @param amount space required for store
     * @param spare space available in container
     * @invariant amount > spare
     */
    public InsufficientCapacityException(int amount, int spare) {
        super("Tried to store amount " + amount + " in container which only has space for " + spare);
    }
}
