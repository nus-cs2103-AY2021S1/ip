package duke.parser;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.task.Task;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse user input for Duke application
 */
public class Parser {

    /**
     * Parse user input into an executable command.
     * The resulting command, when executed, may perform modifications to taskList
     * @param taskList The taskList which the Duke Command will execute on
     * @param input The raw user input
     * @return The corresponding Command, or InvalidCommand if input cannot be parsed
     */
    public static Command parse(List<Task> taskList, String input) {

        // Match the input pattern
        // matcher.group(1) is used to identify the command type
        // matcher.group(2) is used to extract command parameters
        Pattern pattern = Pattern.compile("^\\s*(\\S+)\\s*(.*)$");
        Matcher matcher = pattern.matcher(input);

        // No input received
        if (!matcher.matches()) return new InvalidCommand("Empty input!");

        // Find and generate the matching duke.command
        String firstWord = matcher.group(1).toUpperCase();
        return Arrays.stream(CommandFactory.values()) // parser is an enum of all valid commands
                .filter(commandFactory -> commandFactory.toString().equals(firstWord))
                .findFirst()
                .map(commandFactory -> commandFactory.generate(taskList, matcher.group(2)))
                .orElse(new InvalidCommand());
    }

}
