package duck;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerators for different commands for clarity and consistent reference.
 *
 * Solution adapted from Effective Java by Joshua Bloch.
 * https://stackoverflow.com/questions/604424/how-to-get-an-enum-value-from-a-string-value-in-java
 */
public enum Option {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DEADLINE("deadline"),
    TODO("todo"),
    EVENT("event"),
    DELETE("delete"),
    DUE("due"),
    FIND("find"),
    UNRECOGNIZED;

    private static final Map<String, Option> mappings;
    private String name;


    Option() { }

    Option(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    static {
        Map<String, Option> map = new HashMap<String, Option>();
        for (Option option : Option.values()) {
            map.put(option.getName(), option);
        }
        mappings = map;
    }

    /**
     * Parses the string passed in to determine what type of option it is.
     *
     * @param option String representation of the option.
     * @return Option enumerator type.
     */
    public static Option from (String option) {
        if (mappings.containsKey(option.toLowerCase())) {
            return mappings.get(option.toLowerCase());
        } else {
            return Option.UNRECOGNIZED;
        }

    }
}
