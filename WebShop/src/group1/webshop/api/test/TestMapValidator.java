package group1.webshop.api.test;

import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

import group1.webshop.api.JsonInterface;

/**
 * JSON map validator tester
 *
 * @author Emil Bertilsson
 */
public class TestMapValidator {

    /**
     * Test validator instance
     */
    private final TestValidator validator = new TestValidator();

    /**
     * Tests for missing entries
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testMissingEntries() {
        Map<String, Object> errMap = validator.validate(
                JsonInterface.fromJson("{\"map1\"={}}"));

        assertEquals("Missing entry root depth test",
                true,
                errMap.containsKey("number1"));

        assertEquals("Missing entry secondary depth test",
                true,
                ((Map<String, Object>) errMap.get("map1")).containsKey("string1"));
    }

    /**
     * Tests for correct values for every key
     */
    @Test
    public void testCorrectValues() {
        Map<String, Object> errMap = validator.validate(
                JsonInterface.fromJson(
                        "{\"number1\"=25.0,\"number2\"=110.0,\"number3\"=3.0,\"map1\"={\"string1\"=\"55555\",\"string2\"=\"666666\",\"string3\"=\"22\",\"string4\"=\"88888888\"},\"map2\"={}}"));

        assertEquals("Empty error map test", true, errMap.isEmpty());
    }

    /**
     * Tests for incorrect values for every key
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testIncorrectValues() {
        Map<String, Object> errMap = validator.validate(
                JsonInterface.fromJson(
                        "{\"number1\"=15.0,\"number2\"=10.0,\"number3\"=10.0,\"map1\"={\"string1\"=\"4444\",\"string2\"=\"333\",\"string3\"=\"666666\",\"string4\"=\"1\"},\"map2\"={}}"));

        boolean hasAllErrors = errMap.containsKey("number1")
                && errMap.containsKey("number2")
                && errMap.containsKey("number3")
                && errMap.containsKey("map1")
                && ((Map<String, Object>) errMap.get("map1")).containsKey("string1")
                && ((Map<String, Object>) errMap.get("map1")).containsKey("string2")
                && ((Map<String, Object>) errMap.get("map1")).containsKey("string3");

        // Global error test
        assertEquals("Error map test", true, hasAllErrors);

        if (hasAllErrors) {
            // number1 test
            assertEquals("Entry range test",
                    true,
                    ((Map<String, Object>) errMap.get("number1")).containsKey("ERR_RANGE"));

            // number2 test
            assertEquals("Entry min value test",
                    true,
                    ((Map<String, Object>) errMap.get("number2")).containsKey("ERR_MIN"));

            // number3 test
            assertEquals("Entry max value test",
                    true,
                    ((Map<String, Object>) errMap.get("number3")).containsKey("ERR_MAX"));

            Map<String, Object> map1 = (Map<String, Object>) errMap.get("map1");

            // string1 test
            assertEquals("Entry string length test",
                    true,
                    ((Map<String, Object>) map1.get("string1")).containsKey("ERR_LENGTH"));

            // string2 test
            assertEquals("Entry string minimum length test",
                    true,
                    ((Map<String, Object>) map1.get("string2")).containsKey("ERR_MINLENGTH"));

            // string3 test
            assertEquals("Entry string maximum length test",
                    true,
                    ((Map<String, Object>) map1.get("string3")).containsKey("ERR_MAXLENGTH"));

            // string3 test
            assertEquals("Entry string length range test",
                    true,
                    ((Map<String, Object>) map1.get("string4")).containsKey("ERR_LENGTHRANGE"));
        }
    }

}
