package group1.webshop.api;

import java.util.Map;

/**
 * Tester class
 *
 * @author Emil Bertilsson
 */
public class Tester {

    public static void main(String[] args) {
        Map<String, Object> jsonResult = JsonInterface.fromJson("{\"test\"=15.0, \"test2\"={\"key\"=\"value\"}}");
    }

}
