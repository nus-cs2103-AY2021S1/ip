package duke.cmd;

import duke.command.Command;
import duke.command.CommandEnum;
import duke.command.InvalidCommand;
import duke.task.Task;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Enum of all valid commands in CDuke
 */
public class Parser {

    /**
     * Translates user input to its corresponding Command
     * @param taskList The taskList which the Duke Command will execute on
     * @param input The raw user input
     * @return The corresponding Command, or InvalidCommand if input cannot be parsed
     */
    public static Command parse(List<Task> taskList, String input) {

        Pattern pattern = Pattern.compile("^\\s*(\\S+)\\s*(.*)$");
        Matcher matcher = pattern.matcher(input);

        // No input received
        if (!matcher.matches()) return new InvalidCommand("Empty input!");

        // Find the matching duke.command
        String firstWord = matcher.group(1).toUpperCase();
        return Arrays.stream(CommandEnum.values()) // parser is an enum of all valid commands
                .filter(commandEnum -> commandEnum.toString().equals(firstWord))
                .findFirst()
                .map(commandEnum -> commandEnum.generate(taskList, matcher.group(2)))
                .orElse(new InvalidCommand());
    }

}
