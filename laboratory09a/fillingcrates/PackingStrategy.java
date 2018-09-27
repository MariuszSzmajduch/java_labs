package laboratory09a.fillingcrates;

 

/**
 * provides an algorithm for adding
 * amounts of storage to FillableContainers
 * Possible strategies include
 * <UL><LI>first-fit
 *     <LI>best-fit
 *     <LI>worst-fit
 * </UL>
 * @author jsinger
 */
interface PackingStrategy {
    
    /**
     * add this amount to a free
     * FillableContainer
     * @param amount amount to add
     */
    void addAmount(int amount);

    /**
     * @return how many containers are
     * being used for storage with this
     * strategy
     */
    int numContainers();
}