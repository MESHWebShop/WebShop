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
public class JsonMapValidatorEntry {

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
     * Entry class
     */
    private Class<?> cls;

    /**
     * Entry required
     */
    private boolean required;

    /**
     * Adds an error to this entry tester and returns a new piped instance
     * 
     * @param errInfo Error checker info
     * @return New entry tester
     */
    private JsonMapValidatorEntry addErrCheckAndPipe(ErrCheckInfo errInfo) {
        List<ErrCheckInfo> newList = new ArrayList<>(errCheckers);
        newList.add(errInfo);

        return new JsonMapValidatorEntry(cls, required, newList);
    }

    /**
     * Returns the target class of the entry
     * 
     * @return Target class
     */
    public Class<?> getTargetClass() {
        return cls;
    }

    /**
     * Returns true if this entry is required
     * 
     * @return True if this entry is required, otherwise false
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Adds a test for parameter minimum value (requires a Double instance) and
     * pipes it to a new tester instance
     * 
     * @param min Minimum value
     * @return New entry tester
     */
    public JsonMapValidatorEntry min(final double min) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_MIN",
                String.valueOf(min),
                (obj) -> {
                    double value = (double) obj;
                    return value >= min;
                }));
    }

    /**
     * Adds a test for parameter maximum value (requires a Double instance) and
     * pipes it to a new tester instance
     * 
     * @param max Maximum value
     * @return New entry tester
     */
    public JsonMapValidatorEntry max(final double max) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_MAX",
                String.valueOf(max),
                (obj) -> {
                    double value = (double) obj;
                    return value <= max;
                }));
    }

    /**
     * Adds a test for parameter range (requires a Double instance) and
     * pipes it to a new tester instance
     * 
     * @param min Minimum value
     * @param max Maximum value
     * @return New entry tester
     */
    public JsonMapValidatorEntry range(final double min, final double max) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_RANGE",
                String.valueOf(min) + "," + String.valueOf(max),
                (obj) -> {
                    double value = (double) obj;
                    return value >= min && value <= max;
                }));
    }

    /**
     * Adds a test for parameter absolute length (requires a String instance)
     * and pipes it to a new tester instance
     * 
     * @param length Absolute length
     * @return New entry tester
     */
    public JsonMapValidatorEntry length(final int length) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_LENGTH",
                String.valueOf(length),
                (obj) -> {
                    String value = (String) obj;
                    return value.length() == length;
                }));
    }

    /**
     * Adds a test for parameter minimum length (requires a String instance) and
     * pipes it to a new tester instance
     * 
     * @param minLength Minimum length
     * @return New entry tester
     */
    public JsonMapValidatorEntry minLength(final int minLength) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_MINLENGTH",
                String.valueOf(minLength),
                (obj) -> {
                    String value = (String) obj;
                    return value.length() >= minLength;
                }));
    }

    /**
     * Adds a test for parameter minimum length (requires a String instance) and
     * pipes it to a new tester instance
     * 
     * @param maxLength Maximum length
     * @return New entry tester
     */
    public JsonMapValidatorEntry maxLength(final int maxLength) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_MAXLENGTH",
                String.valueOf(maxLength),
                (obj) -> {
                    String value = (String) obj;
                    return value.length() <= maxLength;
                }));
    }

    /**
     * Adds a test for parameter length range (requires a String instance) and
     * pipes it to a new tester instance
     * 
     * @param minLength Minimum length
     * @param maxLength Maximum length
     * @return New entry tester
     */
    public JsonMapValidatorEntry lengthRange(final int minLength, final int maxLength) {
        return addErrCheckAndPipe(new ErrCheckInfo(
                "ERR_LENGTHRANGE",
                String.valueOf(minLength) + "," + String.valueOf(maxLength),
                (obj) -> {
                    String value = (String) obj;
                    return value.length() >= minLength && value.length() <= maxLength;
                }));
    }

    /**
     * Tests this entry for errors
     * 
     * @param obj Object to test
     * @return Error map
     */
    public Map<String, Object> test(Object obj) {
        final Map<String, Object> errMap = new HashMap<>();

        if (cls.isInstance(obj)) {
            for (ErrCheckInfo errCheck : errCheckers) {
                if (!errCheck.testPredicate(obj)) {
                    errMap.put(errCheck.getType(), errCheck.getMsg());
                }
            }
        } else {
            errMap.put("ERR_TYPE", cls.getName());
        }

        return errMap;
    }

    /**
     * JSON Map entry tester
     * 
     * @param cls Target class
     * @param required Entry required
     */
    public JsonMapValidatorEntry(Class<?> cls, boolean required) {
        this.cls = cls;
        this.required = required;
    }

    /**
     * JSON Map entry tester
     * 
     * @param cls Target class
     * @param required Entry required
     * @param errCheckers Initial error checkers
     */
    private JsonMapValidatorEntry(Class<?> cls, boolean required, List<ErrCheckInfo> errCheckers) {
        this(cls, required);
        this.errCheckers.addAll(errCheckers);
    }

}
