package laboratory09a.sparkcrates;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;
import spark.Spark;
import spark.utils.IOUtils;

/**
 * This class tests behaviour of WebCrates class.
 * <p>
 * 
 * @author <li>jsinger
 *         <li>Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>01 Dec 2014</center>
 */
@SuppressWarnings("unused")
public class TestWebCrates {

    private static final int PORT = 4567;

    @BeforeClass
    public static void beforeClass() {
        // initialize spark framework
        // running WebCrates system
        WebCrates.main(null);
        try {
            // wait 1s for spark framework to boot up...
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("interrupted!");
        }
    }

    @AfterClass
    public static void afterClass() {
        // shutdown Spark framework properly
        Spark.stop();
    }

    /**
     * A simple example test - follow this structure
     * for your own tests... 
     * This one checks
     * http://localhost:4567/showcrates returns a non-empty response
     */
    @Test
    public void testShowCrates() {
        UrlResponse response = doMethod("GET", "/showcrates", null);
        assertEquals(200, response.status);
        assertTrue("empty response body", response.body.length() > 0);
    }
    
    /**
     * Test checks
     * http://localhost:4567/add/:size returns response with
     * at least one 0-crate
     */
    @Test
    public void testAddCrates() {
        UrlResponse response1 = doMethod("GET", "/add", null);
        UrlResponse response2 = doMethod("GET", "/add/50", null);
        assertEquals(200, response1.status);
        assertEquals(200, response2.status);
        assertTrue("empty crate with default capacity had not been added",
                response1.body.contains("capacity 100"));
        assertTrue("empty crate with specified capacity had not been added",
                response2.body.contains("capacity 50"));
    }
    
    /** 
     * Test checks 
     * http://localhost:4567/add/size aborts if
     * invalid size was provided.
     */
    @Test
    public void testAddCratesWithInvalidCapacity() {
        UrlResponse response1 =doMethod("GET", "/add/0", null);
        UrlResponse response2 =doMethod("GET", "/add/-1", null);
        UrlResponse response3 =doMethod("GET", "/add/abc", null);
        UrlResponse response4 =doMethod("GET", "/add/1.1", null);
        assertEquals(200, response1.status);
        assertTrue("attempt to add a crate with 0 capacity should be aborted", 
                response1.body.contains("aborted"));
        assertEquals(200, response2.status);
        assertTrue("attempt to add a crate with negative capacity should be aborted", 
                response2.body.contains("aborted"));
        assertEquals(200, response3.status);
        assertTrue("attempt to add a crate with capacity specified not" +
                "by a number should be aborted", 
                response3.body.contains("aborted"));
        assertEquals(200, response4.status);
        assertTrue("attempt to add a crate with capacity specified not" +
                "by an integer should be aborted", 
                response4.body.contains("aborted"));
    }
    
    /** 
     * Test checks
     * http://localhost:4567/fill/:fit/:amount returns page updated
     * with crates filled with specified amount
     * and fitting-algorithm.
     */
    @Test
    public void testStore() {
        String[] arrFit = { "first", "best", "worst" };
        Random rand = new Random();

        for (String fit : arrFit) {
            int amount = rand.nextInt(100) + 1; // rand returns 0..99
            UrlResponse response = doMethod("GET", "/fill/" + fit + "/"
                    + amount, null);
            assertEquals(200, response.status);
            assertTrue("missing information of used fitting-algorithm",
                    response.body.contains(
                            "<span style=\"color:green\"><u>" + fit));
            assertTrue("Stored capacity is not shown",
                    response.body.contains(
                            "<span style=\"color:red\"><b>" + amount));
        }
    }
    
    /** 
     * Test checks 
     * http://localhost:4567/fit/amount aborts if
     * invalid amount was provided.
     */
    @Test
    public void testStoreInvalidCapacity() {
        String [] arrFit = { "first", "best", "worst" };
        
        for (String fit : arrFit) {        
        UrlResponse response1 =doMethod("GET", "/fill/" + fit + "/-1", null);
        UrlResponse response2 =doMethod("GET", "/fill/" + fit + "/abc", null);
        UrlResponse response3 =doMethod("GET", "/fill/" + fit + "/1.2", null);
        UrlResponse response4 =doMethod("GET", "/fill/" + fit + "/101", null);
        assertEquals(200, response1.status);
        assertTrue("attempt to store negative amount should be aborted", 
                response1.body.contains("aborted"));
        assertEquals(200, response2.status);
        assertTrue("attempt to store an amount specified not by a number should be aborted", 
                response2.body.contains("aborted"));
        assertEquals(200, response2.status);
        assertTrue("attempt to store an amount specified not by an integer should be aborted", 
                response2.body.contains("aborted"));
        assertTrue("attempt to store amount too big should be aborted", 
                response4.body.contains("aborted"));
        }
    }
    
    /** 
     * Test checks
     * http://localhost:4567/clearContainer returns 
     * response with empty container.
     */
    @Test
    public void testContainerClearing() {
        UrlResponse response = doMethod("GET", "/clearContainer", null);
        assertEquals(200, response.status);
        assertTrue("container should be empty", response.body.contains("[ ]"));
    }

    /**
     * Helper methods/classes follow...
     */
    private static UrlResponse doMethod(String requestMethod, String path,
            String body) {
        UrlResponse response = new UrlResponse();
        try {
            getResponse(requestMethod, path, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private static void getResponse(String requestMethod, String path,
            UrlResponse response) throws MalformedURLException, IOException,
            ProtocolException {
        URL url = new URL("http://0.0.0.0:" + PORT + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.connect();
        String res = IOUtils.toString(connection.getInputStream());
        response.body = res;
        response.status = connection.getResponseCode();
        response.headers = connection.getHeaderFields();
    }

    private static class UrlResponse {
        public Map<String, List<String>> headers;
        private String body;
        private int status;
    }
}
