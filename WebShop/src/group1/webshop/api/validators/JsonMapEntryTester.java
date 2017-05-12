package group1.webshop.api.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * JSON Map entry tester class
 * 
 * @author Emil Bertilsson
 */
public class JsonMapEntryTester {

    /**
     * Error checker info
     */
    private class ErrCheckInfo {

        private final String type;
        private final String msg;
        private final Predicate<Object> predicate;

        /**
         * Retrieves the error check type
         * 
         * @return Error check type
         */
        public String getType() {
            return this.type;
        }

        /**
         * Retrieves the displayable error message
         * 
         * @return Error message
         */
        public String getMsg() {
            return this.msg;
        }

        /**
         * Tests the error checker predicate to the given object
         * 
         * @param obj Object to test
         * @return True on success, false on failure
         */
        public boolean testPredicate(Object obj) {
            return predicate.test(obj);
        }

        /**
         * Error checker info
         *
         * @param type Errcheck type
         * @param msg Errcheck content (Displayed message on failure)
         * @param predicate
         */
        public ErrCheckInfo(String type, String msg, Predicate<Object> predicate) {
            this.type = type;
            this.msg = msg;
            this.predicate = predicate;
        }

    }

    /**
     * Entry testing predicate
     */
    private final List<ErrCheckInfo> errCheckers = new ArrayList<>();

    /**
     * Entry key
     */
    private final String key;

    /**
     * Adds the specified error checkers to this entry
     * 
     * @param errCheckers Error checkers
     */
    private void addErrCheckers(List<ErrCheckInfo> errCheckers) {
        this.errCheckers.addAll(errCheckers);
    }

    /**
     * Tests this entry for errors
     * 
     * @param obj Object to test
     * @return Error map
     */
    public Map<String, String> test(Object obj) {
        final Map<String, String> errMap = new HashMap<>();

        for (ErrCheckInfo errCheck : errCheckers) {
            if (!errCheck.testPredicate(obj)) {
                errMap.put(errCheck.getType(), errCheck.getMsg());
                break;
            }
        }

        return errMap;
    }

    /**
     * Adds a test for class compatibility with the given class parameter and
     * pipes it to a new tester instance
     * 
     * @param cls Class to test for
     * @return New piped tester
     */
    public JsonMapEntryTester type(Class<?> cls) {
        List<ErrCheckInfo> newList = new ArrayList<>(errCheckers);
        newList.add(new ErrCheckInfo(
                "ERR_TYPE",
                String.format("Value of %s must be of type %s", key, cls.getName()),
                (obj) -> cls.isInstance(obj)));

        return new JsonMapEntryTester(key, newList);
    }

    /**
     * Adds a test for parameter range (requires a Comparable instance) and
     * pipes it to a new tester instance
     * 
     * @param min Minimum range
     * @param max Maximum range
     * @return New piped tester
     */
    public JsonMapEntryTester range(double min, double max) {
        List<ErrCheckInfo> newList = new ArrayList<>(errCheckers);
        newList.add(new ErrCheckInfo(
                "ERR_RANGE",
                String.format("Value of %s must be in the range (%.2d,%.2d)", key, min, max),
                (obj) -> {
                    double value = (double) obj;
                    return value >= min && value <= max;
                }));

        return new JsonMapEntryTester(key, newList);
    }

    /**
     * Entry tester constructor
     * 
     * @param key Entry key
     */
    public JsonMapEntryTester(String key) {
        this.key = key;
    }

    /**
     * Entry constructor with given error checkers
     * 
     * @param key Entry key
     * @param errCheckers Error checkers
     */
    public JsonMapEntryTester(String key, List<ErrCheckInfo> errCheckers) {
        this(key);
        this.errCheckers.addAll(errCheckers);
    }

}
