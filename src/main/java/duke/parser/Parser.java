package duke.parser;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commands.Command;
import commands.DeadlineCommand;
import commands.DoneCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.ToDoCommand;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final String EVENT_DELIMITER = "/at";
    public static final String DEADLINE_DELIMITER = "/by";

    /**
     * Parses user input and performs corresponding actions
     * through ui, taskList and storage.
     * @param userInput The line of text keyed in by the user.
     */
    public Command parseUserInput(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // return incorrect command
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        case DoneCommand.COMMAND_WORD:
            return prepareDone(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    private Command prepareFind(String arguments) {
        try {
            return new FindCommand(arguments.trim());
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    private Command prepareDone(String arguments) {
        try {
            String index = arguments.trim();
            return new DoneCommand(Integer.parseInt(index));
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    private Command prepareDeadline(String arguments) {
        try {
            String description = splitDescription(arguments);
            LocalDate date = parseTime(arguments, DEADLINE_DELIMITER);
            return new DeadlineCommand(description, date);
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    private Command prepareEvent(String arguments) {
        try {
            String description = splitDescription(arguments);
            LocalDate date = parseTime(arguments, EVENT_DELIMITER);
            return new EventCommand(description, date);
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    private Command prepareToDo(String arguments) {
        try {
            return new ToDoCommand(arguments.trim());
        } catch (IllegalArgumentException exception) {
            return new IncorrectCommand(exception.getMessage());
        }
    }

    private String splitDescription(String arguments) {
        String description = arguments.split("/")[0].trim();
        return description;
    }

    private LocalDate parseTime(String arguments, String delimiter) { // throws a wrong dateformatexception
        String time = arguments.split(delimiter)[1].trim();
        return LocalDate.parse(time);
    }
}
