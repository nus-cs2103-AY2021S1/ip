package duke.core.parser;

import duke.core.DataStore;
import duke.designpattern.command.Executable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input for Duke application
 */
public class Parser {

    /**
     * Parse user input and convert it into an executable command.
     * The resulting command, when executed, may perform modifications to taskList
     * @param dataStore The taskList which the Duke Command will execute on
     * @param input The raw user input
     * @return The corresponding Command, or InvalidCommand if input cannot be parsed
     * @throws DukeParserException if the input cannot be parsed. Details about the error can be
     * retrieved by the Throwable.getMessage() method
     */
    public static Executable parse(DataStore dataStore, String input) throws DukeParserException {

        // Match the input pattern
        Pattern pattern = Pattern.compile("^\\s*(\\S+)\\s*(.*)$");
        Matcher matcher = pattern.matcher(input);

        // No input received
        if (!matcher.matches()) {
            throw new DukeParserException("Empty input!");
        }

        // Extract commandType and commandParameter
        String commandType = matcher.group(1).toUpperCase();
        String commandParam = matcher.group(2);

        try {
            // Generate duke.core.command with commandType and commandParameter
            return ParseToCommand.valueOf(commandType).parse(dataStore, commandParam);
        } catch (IllegalArgumentException e) {
            throw new DukeParserException("Unrecognised Command!");
        }
    }

}
