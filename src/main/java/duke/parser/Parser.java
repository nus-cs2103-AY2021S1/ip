package duke.parser;

import duke.commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Parser that will read User Input from the command
 * line and identify the appropriate command to be executed.
 */
public class Parser {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<body>.*)");

    /**
     * Tries to identify the format of the date input by the user
     * and returns a LocalDate object if it has the correct format.
     *
     * @param date Date input by the user.
     * @return LocalDate object if date has the correct format. Otherwise, null.
     */
    public static LocalDate parseDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            localDate = null;
        }
        return localDate;
    }

    /**
     * Parses the user input and identifies which command
     * to execute.
     *
     * @param s User input through command line.
     * @return Appropriate Command to be executed.
     */
    public static Command parse(String s) {
        Matcher matcher = COMMAND_FORMAT.matcher((s.trim()));
        if (!matcher.matches()) {
            return new InvalidCommand();
        }

        String commandWord = matcher.group("command");
        String commandBody = matcher.group("body");

        switch (commandWord) {
        case TodoCommand.COMMAND_WORD:
            return prepareTodo(commandBody.strip());
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(commandBody.strip());
        case EventCommand.COMMAND_WORD:
            return prepareEvent(commandBody.strip());
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(commandBody.strip());
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(commandBody.strip());
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case GetEventsCommand.COMMAND_WORD:
            return prepareGetEvents(commandBody.strip());
        case SearchCommand.COMMAND_WORD:
            return prepareSearch(commandBody.strip());
        default:
            return new InvalidCommand();

        }
    }

    //Ensures todo command has the correct format and creates a TodoCommand object if it does.
    //Otherwise, returns an InvalidCommand object.
    private static Command prepareTodo(String commandBody) {
        if (commandBody.length() > 0) {
            return new TodoCommand(commandBody.strip());
        } else {
            return new InvalidCommand();
        }
    }

    //Ensures deadline command has the correct format and creates a DeadlineCommand object if it does.
    //Otherwise, returns an InvalidCommand object.
    private static Command prepareDeadline(String commandBody) {
        String[] splitParts = commandBody.split((" /by "));
        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 || splitParts[1].strip().length() == 0) {
            return new InvalidCommand();
        } else {
            LocalDate localDate = Parser.parseDate(splitParts[1].strip());
            if (localDate != null) {
                return new DeadlineCommand(splitParts[0].stripLeading(), localDate);
            } else {
                return new DeadlineCommand(splitParts[0].stripLeading(), splitParts[1].strip());
            }

        }
    }

    //Ensures event command has the correct format and creates a EventCommand object if it does.
    //Otherwise, returns an InvalidCommand object.
    private static Command prepareEvent(String commandBody) {
        String[] splitParts = commandBody.split((" /at "));
        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 || splitParts[1].strip().length() == 0) {
            return new InvalidCommand();
        } else {
            LocalDate localDate = Parser.parseDate(splitParts[1].strip());
            if (localDate != null) {
                return new EventCommand(splitParts[0].stripLeading(), localDate);
            } else {
                return new EventCommand(splitParts[0].stripLeading(), splitParts[1].strip());
            }
        }
    }

    //Ensures getEvents command has the correct format and creates a GetEventsCommand object if it does.
    //Otherwise, returns an InvalidCommand object.
    private static Command prepareGetEvents(String commandBody) {
        String date = commandBody.strip();
        try {
            LocalDate localDate = LocalDate.parse(date);
            return new GetEventsCommand(localDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand();
        }
    }

    //Ensures the search keyword is a non-empty string and creates a SearchCommand object if it is.
    //Otherwise, returns an InvalidCommand object.
    private static Command prepareSearch(String commandBody) {
        if (commandBody.length() == 0) {
            return new InvalidCommand();
        } else {
            return new SearchCommand(commandBody);
        }
    }

}
