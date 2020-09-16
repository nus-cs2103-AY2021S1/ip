package rogue.logic.parser;

import rogue.logic.parser.exceptions.IncorrectInputException;
import rogue.model.argument.Action;
import rogue.model.argument.Argument;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validates user inputs and separates them into an {@code Action}
 * and its options.
 */
public class ArgumentParser {
    /** A valid input follows the pattern {@code <command> /<option1> <value1> (/<option2> <value2>...)} */
    public static final Pattern PATTERN_VALID_INPUT = Pattern.compile("\\s*([\\w\\s-]+)\\s*(?:/[\\w\\s-]+)*");

    /** An option-value pair follows the pattern {@code /<option> <value>}. */
    public static final Pattern PATTERN_OPTIONS = Pattern.compile("(/[\\w-]+)\\s+([\\w\\s-]+)");

    /** Message for empty or invalid user inputs. */
    private static final String ERROR_INVALID_INPUT = "Do you not know the commands?"
            + "I could consider telling you if you begged for help.";

    /**
     * Parses the input into an {@code Action} and its corresponding options.
     *
     * @param input The user input.
     * @return The arguments for an {@code Executable}
     * @throws IncorrectInputException when the user input is empty or does not conform to the pattern.
     */
    public static Argument parseArgs(String input) throws IncorrectInputException {
        Matcher inputMatcher = PATTERN_VALID_INPUT.matcher(input);

        // Empty or invalid input from user
        if (!inputMatcher.matches()) {
            throw new IncorrectInputException(ERROR_INVALID_INPUT);
        }

        Matcher argsMatcher = PATTERN_OPTIONS.matcher(input);

        assert inputMatcher.group(1) != null : "The action cannot be null!";

        Action action = Action.getAction(inputMatcher.group(1).trim());
        Map<String, String> optionValueMap = new HashMap<>();

        // Capture all option-value pairs
        while (argsMatcher.find()) {
            // Group 1 should contain the option, and group 2 the value
            optionValueMap.put(argsMatcher.group(1).trim(), argsMatcher.group(2).trim());
        }

        return new Argument(action, optionValueMap);
    }
}
