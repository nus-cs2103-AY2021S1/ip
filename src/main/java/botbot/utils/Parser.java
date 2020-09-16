package botbot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import botbot.commands.Command;
import botbot.commands.CommandValidator;
import botbot.commands.DeadlineCommand;
import botbot.commands.DeleteCommand;
import botbot.commands.EditCommand;
import botbot.commands.EventCommand;
import botbot.commands.ExitCommand;
import botbot.commands.FindCommand;
import botbot.commands.InvalidCommand;
import botbot.commands.ListCommand;
import botbot.commands.MarkAsDoneCommand;
import botbot.commands.TodoCommand;

/**
 * Parser for Botbot commands.
 */
public class Parser {
    private static final Pattern FORMAT_COMMAND = Pattern.compile("(?<commandKeyword>\\S+)(?<args>.*)");
    private static final String FORMAT_DATE_TIME = "d-M-yyyy HHmm[ssn]";
    private static final int DATE_TIME_LENGTH = 13;

    /**
     * Parses the given user input into a command.
     *
     * @param input Input to be parsed.
     * @return Command parsed from input.
     */
    public static Command parseCommand(String input) {
        Matcher matcher = FORMAT_COMMAND.matcher(input.trim());
        if (!matcher.matches()) {
            return new InvalidCommand(CommandValidator.ERROR_MESSAGE_NO_SUCH_COMMAND);
        }

        String commandKeyword = matcher.group("commandKeyword");
        String args = matcher.group("args").trim();

        switch (commandKeyword) {
        case DeadlineCommand.COMMAND_KEYWORD:
            return CommandValidator.tryAddDeadline(args);

        case DeleteCommand.COMMAND_KEYWORD:
            return CommandValidator.tryDelete(args);
            
        case EditCommand.COMMAND_KEYWORD:
            return CommandValidator.tryEdit(args);

        case EventCommand.COMMAND_KEYWORD:
            return CommandValidator.tryAddEvent(args);

        case ExitCommand.COMMAND_KEYWORD:
            return new ExitCommand();

        case FindCommand.COMMAND_KEYWORD:
            return CommandValidator.tryFind(args);

        case ListCommand.COMMAND_KEYWORD:
            return new ListCommand();

        case MarkAsDoneCommand.COMMAND_KEYWORD:
            return CommandValidator.tryMarkAsDone(args);

        case TodoCommand.COMMAND_KEYWORD:
            return CommandValidator.tryAddTodo(args);

        default:
            return new InvalidCommand(CommandValidator.ERROR_MESSAGE_NO_SUCH_COMMAND);
        }
    }

    /**
     * Parses the input for a task ID.
     *
     * @param args Input to be parsed.
     * @return Task ID parsed from input.
     * @throws NumberFormatException If input does not contain a parsable integer.
     */
    public static int parseCommandId(String args) throws NumberFormatException {
        assert !args.isBlank() : "Args provided is blank";
        return Integer.parseInt(args) - 1;
    }

    /**
     * Parses the input for a date (and a time).
     *
     * @param input Input to be parsed.
     * @return Date (and time) parsed from input.
     * @throws DateTimeParseException If input does not follow expected datetime format.
     */
    public static LocalDateTime parseDateTime(String input) throws DateTimeParseException {
        if (input.length() < DATE_TIME_LENGTH) {
            input += BotbotDateTimeFormatter.NO_TIME_FLAG_STR;
        }
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(FORMAT_DATE_TIME));
    }
}
