package duke.parser;

import duke.commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<body>.*)");

    public static LocalDate parseDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            localDate = null;
        }
        return localDate;
    }

    public static Command parse(String s) {
        Matcher matcher = COMMAND_FORMAT.matcher((s.trim()));
        if (!matcher.matches()) {
            return new InvalidCommand();
        }

        String commandWord = matcher.group("command");
        String commandBody = matcher.group("body");

        switch(commandWord) {
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

    private static Command prepareTodo(String commandBody) {
        if (commandBody.length() > 0) {
            return new TodoCommand(commandBody.strip());
        } else {
            return new InvalidCommand();
        }
    }

    private static Command prepareDeadline(String commandBody) {
        String[] splitParts = commandBody.split((" /by "));
        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 ||
                splitParts[1].strip().length() == 0) {
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

    private static Command prepareEvent(String commandBody) {
        String[] splitParts = commandBody.split((" /at "));
        if (splitParts.length != 2
                || splitParts[0].strip().length() == 0
                || splitParts[1].strip().length() == 0) {
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

    private static Command prepareGetEvents(String commandBody) {
        String date = commandBody.strip();
        try {
            LocalDate localDate = LocalDate.parse(date);
            return new GetEventsCommand(localDate);
        } catch (DateTimeParseException e) {
            return new InvalidCommand();
        }
    }

    private static Command prepareSearch(String commandBody) {
        if (commandBody.length() == 0) {
            return new InvalidCommand();
        } else {
            return new SearchCommand(commandBody);
        }
    }

}
