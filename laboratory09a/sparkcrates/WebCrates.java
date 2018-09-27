package laboratory09a.sparkcrates;

import java.util.ArrayList;
import java.util.List;

import spark.Spark;
import laboratory09a.fillingcrates.AbstractFit;
import laboratory09a.fillingcrates.BestFit;
import laboratory09a.fillingcrates.Crate;
import laboratory09a.fillingcrates.FillableContainer;
import laboratory09a.fillingcrates.FirstFit;
import laboratory09a.fillingcrates.WorstFit;

/**
 * Driver class for WebCrates project.
 * It generates html document visualising
 * operations on container of crates using various
 * fitting-algorithms.<br />
 * Graphic of ship taken from 
 * <a href="http://thumbs.dreamstime.com/x/container-ship-cartoon-
 * illustration-funny-comic-mascot-character-37720104.jpg">
 * thumbs.dreamstime.com</a>.<br />
 * The picture of box taken from
 * <a href="http://www.npt.gov.uk/images/play_playpic04.gif">
 * www.npt.gov/images</a>.
 * <br />
 * Class tested, works as expected. No bugs found.
 * 
 * @author Mariusz Szmajduch<br />
 *         2089488S@student.gla.ac.uk<br>
 *         <center>01 Dec 2014</center>
 */
public class WebCrates {

    /**
     * the internal state of the web application - 
     * the list of crates that we are going to
     * organize using a predefined fitting algorithm.
     * NOTE - this List corresponds to the 'model'
     * in an MVC style design pattern.
     */
    public static List<FillableContainer> crates;

    static {
        crates = new ArrayList<FillableContainer>();
    }

    public static void main(String[] args) {
        registerCallbacks();
    }   
    
    /**
     * Sets up callbacks.
     */
    private static void registerCallbacks() {        
        registerPrintingContainer(); 
        registerAddingCrate();
        registerStoring();
        registerContainerClearing();
    }

    /**
     * Displays container
     */
    private static void registerPrintingContainer() {
        Spark.get("/showcrates", (req, res) -> {
            return printPage(crates.isEmpty() ? "The container is empty at the moment..." : "");
        });
    }

    /**
     * Adds empty crate to container
     */
    private static void registerAddingCrate() {
        // Adds crate with default capacity
        Spark.get("/add", (req, res) -> {
                    crates.add(new Crate());
                    return printPage(
                            "Empty crate with default capacity 100 was added to the container");
                });

        // Adds crate with specified capacity
        Spark.get("/add/:size", (req, res) -> {
            String capacity = req.params(":size");
            try {
                int size = Integer.parseInt(capacity);
                if (size <= 0) 
                    throw new NumberFormatException();
                crates.add(new Crate(size)); 
            } catch (NumberFormatException nfe) {
                return printPage(
                        "<span style=\"color:red\">" + 
                        "Operation aborted. Attempted to add new crate into container " +
                        "using invalid number format.<br />" + 
                        "<b>Only <u>whole positive integers</u> accepted.</b></span>");
            }
            return  printPage(
                    "Empty crate with capacity " + capacity + " was added to the container");
        });
    }

    /**
     * Stores amount in a crate using according
     * fitting algorithm
     */
    private static void registerStoring() {
        Spark.get("/fill/:fit/:amount", (req, res) -> {
            String fit = req.params(":fit");
            AbstractFit storing = 
                    fit.equals("first") ? new FirstFit() : 
                    (fit.equals("best") ? new BestFit() : 
                        new WorstFit());                    
            storing.setContainers(crates);
            
            int amount;
                    try {
                        amount = Integer.parseInt(req.params(":amount"));
                        if(amount < 0 || AbstractFit.SIZE < amount) 
                            throw new NumberFormatException();
                    } catch (NumberFormatException nfe) {
                        return printPage(
                                "<span style=\"color:red\">" + 
                                "Operation aborted. Attempted to store using invalid number format.<br />" + 
                                "<b>This application accepts <u>whole non-negative integers" + 
                                "</u> ( 0..100) only for storing.</b></span>");
                    }           
            
            storing.addAmount(amount);            
            return printPage(
                    "<span style=\"color:red\"><b>"+ amount + "</b></span> " +
                    "was stored in a container using " +
                    "<span style=\"color:green\"><u>" +fit + "-fit</u></span> algorithm.<br />" +
                    "<i>(" + AbstractFit.SIZE + " is max. available capacity to store)</i>");
        });
    }
    
    /**
     * Removes all items from the container.
     */
    private static void registerContainerClearing() {
        Spark.get("/clearContainer", (req, res) -> {
                    crates.clear();
                    return printPage("... and then the container is empty again.");
                });
    }
    
    /**
     * Generates html code of the page.
     * 
     * @param customInfo
     * @return html code
     */
    private static String printPage(String customInfo) {
        String picture = "<img src=\"http://thumbs.dreamstime.com/x/container-ship-cartoon-illustration-" + 
                            "funny-comic-mascot-character-37720104.jpg\" alt=\"Funny Picture\"" + 
                            "align=\"right\" width=\"140\" height=\"100\"/>";
        return "<!DOCTYPE html>" +
                "<html>" + 
                "<head>" + 
                "<title>WebCrates</title>" + 
                "</head>" + 
                "<body>" + 
                "<center><h1 title=\"version 1.0\">Container Simulator" + picture + "</h1>" +
                customInfo + "</center>" +
                "<p>" +
                "<table border=\"0\" width=\"100%\" cellspacing=\"0\">" +
                "<th>Add new crate to the container:</th><th>Fill crates using:</th><th>Remove all creates:</th>" + 
                "<tr align=\"center\">" +
                "<td>" + 
                "<a href=\"/add\">default crate</a><br />" +
                "<a href=\"/add/50\">crate - size 50</a><br />" +
                "</td>" + 
                "<td>" + 
                "first-fit algorithm - " + generateFillLinks(0, 0) + 
                "</td>" +
                "<td>" +
                "<a href=\"/clearContainer\">clear</a><br />" +
                "<i>(this will remove all crates<br />from the container!)</i>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "<p align=\"center\">" + 
                "<span style=\"color:red\">" +
                "Red color means, that the crate has no capacity availabe for storage</span></p>" + 
                printContainer() +
                "</body>" +
                "</html>";
    }
    
    /**
     * Helper method.
     * Generates html links to store predefined amounts
     * using all kinds of fitting-algorithms.
     * 
     * @param fit - index of fit
     * @param i - index of amount
     * @return html links
     */
    private static String generateFillLinks(int fit, int i) {
        String[] fits = { "first", "best", "worst" };
        String[] amounts = { "1", "2", "5", "10", "20", "50", "99", "100" };
        // nested recursive call!
        if(i == amounts.length) 
            return "<br />" + (++fit < fits.length ? 
                    fits[fit] + "-fit algorithm - " + generateFillLinks(fit, 0) : "");
        return "<a href=\"/fill/" + fits[fit] + "/" +amounts[i] + "\">" + amounts[i] + "</a>, " + 
                    generateFillLinks(fit, ++i);
    }
    
    /**
     * Generates textual and graphical
     * representation of container.
     * 
     * @return html code.
     */
    private static String printContainer() {        
        // plain stores html for textual representation.        
        StringBuilder plain = new StringBuilder();
        
        // graphic stores html for graphical representation.
        StringBuilder graphic = new StringBuilder(
                "<table border=\"6\" align=\"center\" cellspacing=\"5\"" +
                        "cellpadding=\"3\" bgcolor=\"#f2efe3\"><tr>");
            
        for (int i = 0; i < crates.size(); i++) {
            int usedCapacity = crates.get(i).getUsedCapacity();
            
            // conditional formatting
            boolean format = (usedCapacity == crates.get(i).getTotalCapacity());  
            String spaceUsed = (format ? " <span style=\"color:red\">" : " ")
                    + usedCapacity + (format ? "</span>" : "");
            
            plain.append(spaceUsed);            
            if (i < crates.size() - 1)
                plain.append(", ");   // no comma after the last item            

            boolean newRow = (i % 4 == 0);  // limits amount of columns to 4
            if (newRow) 
                graphic.append("</tr><tr>");
            
            graphic.append(crateHTML(
                    spaceUsed, crates.get(i).getAvailableCapacity())); 
        }
        
        graphic.append("</tr></table>");
        return "<p align=\"center\">[" + plain + " ]</p>" + 
                (crates.isEmpty() ? "" : graphic);
    }
    
    /**
     * Generates html code for single crate.
     * 
     * @param spaceUsed in html format
     * @param availableCapacity - int
     * @return html code for crate.
     */
    private static String crateHTML(String spaceUsed, int availableCapacity) {
        String box = "<img src=\"http://www.npt.gov.uk/images/play_playpic04.gif\"" + 
                "alt=\"box\" width=\"120\" height=\"90\">";
        boolean format = (availableCapacity == 0);
        String spaceLeft = "available: " + 
                (format ? "<span style=\"color:red\">" : "") + 
                availableCapacity + (format ? "</span>" : "");
        return "<td>" + spaceLeft + "<br />" + 
                box + "<br />" + 
                "used:" + spaceUsed + "</td>";
    }
}
