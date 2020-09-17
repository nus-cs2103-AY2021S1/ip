package rogue.model.argument;

import java.util.Map;

/**
 * Represents the arguments for an {@code Executable}. Packages an {@code Action},
 * its options, and their corresponding values.
 */
public class Argument {
    private Action action;
    private Map<String, String> optionValueMap;

    /**
     * Constructs an {@code Argument}.
     *
     * @param action
     * @param optionValueMap
     */
    public Argument(Action action, Map<String, String> optionValueMap) {
        this.action = action;
        this.optionValueMap = optionValueMap;
    }

    public Action getAction() {
        return action;
    }

    /**
     * Gets the value corresponding to an option of an option-value pair.
     *
     * An empty string is returned the option does not exist in the option-value
     * map.
     *
     * @param option The option to query.
     * @return The value if found, otherwise an empty string
     */
    public String getOptionValue(String option) {
        String value = optionValueMap.get(option);

        if (value == null) {
            return "";
        }

        return value;
    }
}
