package laboratory09a.fillingcrates;

 

import java.util.ArrayList;
import java.util.List;

/**
 * extend this class to implement a particular bin packing strategy - e.g.
 * first-fit, best-fit.
 * 
 * @version 2.0 <br>
 * Setter added for {@link #containers}
 * @author <li> jsinger
 *         <li> Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>25 Nov 2014</center>
 */
public abstract class AbstractFit implements PackingStrategy {

  /**
   * constant size of each container
   */
  public static final int SIZE = 100;
  /**
   * the name of the fitting algorithm e.g. "first-fit" used in toString()
   */
  private String fittingAlgorithm;

  /**
   * backing list of FillableContainer instances
   */
  protected ArrayList<FillableContainer> containers;

  /**
   * accessor method
   * 
   * @return value of fittingAlgorithm instance field
   */
  public String getFittingAlgorithm() {
    return this.fittingAlgorithm;
  }
  
  /**
   * This method allows to operate on external container.
   * 
   * @param crates - container to operate on.
   */
  public void setContainers(List<FillableContainer> crates) {
      this.containers = (ArrayList<FillableContainer>) crates;
  }

  /**
   * invoke constructor as super(String) from subclasses
   */
  public AbstractFit(String fittingAlgorithm) {
    this.fittingAlgorithm = fittingAlgorithm;
    this.containers = new ArrayList<FillableContainer>();
  }

  /**
   * @return number of elements in containers ArrayList
   */
  public int numContainers() {
    return this.containers.size();
  }

  /**
   * dump state of fitting to a String
   */
  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Number of crates using ");
    sb.append(this.getFittingAlgorithm());
    sb.append(" = ");
    sb.append("" + this.containers.size() + "\n");
    for (int i = 0; i < containers.size(); i++) {
      sb.append("Crate " + i + " has load "
          + containers.get(i).getUsedCapacity() + "\n");
    }
    return sb.toString();
  }

}