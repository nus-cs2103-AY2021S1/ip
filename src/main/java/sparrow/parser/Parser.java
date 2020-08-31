package sparrow.parser;

import sparrow.commands.*;
import sparrow.data.task.Deadline;
import sparrow.data.task.Event;
import sparrow.data.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input and calls the relevant methods.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern TODO_FORMAT = Pattern.compile("(?<description>.+)");

    public static final Pattern DEADLINE_FORMAT =
            Pattern.compile("(?<description>.+)"
                    + " /by (?<deadlineDate>[0-9-]+)");

    public static final Pattern EVENT_FORMAT =
            Pattern.compile("(?<description>.+)"
                    + " /at (?<eventDate>[0-9-]+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keyword>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern FILTER_ARGS_FORMAT=
            Pattern.compile("(?<keywords>\\S+) (?<dateFilter>[0-9-]+)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            // doesn't seem to enter this block
            return new IncorrectCommand("Unknown Command");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddTodoCommand.COMMAND_WORD:
            return prepareAddTodo(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return prepareAddDeadline(arguments);

        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case DoneCommand.COMMAND_WORD:
            return prepareDone(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case FilterCommand.COMMAND_WORD:
            return prepareFilter(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            //todo
            return new HelpCommand();
        }
    }

    private Command prepareAddTodo(String args) {
        final Matcher matcher = TODO_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Description of a Todo can't be empty.");
        }
        Todo todo = new Todo(matcher.group("description"));
        return new AddTodoCommand(todo);
    }

    // TODO: trim whitespace from end of deadline description
    private Command prepareAddDeadline(String args) {
        final Matcher matcher = DEADLINE_FORMAT.matcher((args.trim()));
        if (!matcher.matches()) {
            return new IncorrectCommand("Wrong deadline format.");
        }
        try {
            LocalDate dueDate = LocalDate.parse(matcher.group("deadlineDate"));
            Deadline deadline = new Deadline(matcher.group("description"), dueDate);
            return new AddDeadlineCommand(deadline);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(e.getParsedString());
        }
    }

    // TODO: trim whitespace from end of event description
    private Command prepareAddEvent(String args) {
        final Matcher matcher = EVENT_FORMAT.matcher((args.trim()));
        if (!matcher.matches()) {
            return new IncorrectCommand("Wrong event format.");
        }
        try {
            LocalDate eventDate = LocalDate.parse(matcher.group("eventDate"));
            Event event = new Event(matcher.group("description"), eventDate);
            return new AddEventCommand(event);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(e.getParsedString());
        }
    }

    private Command prepareDelete(String args) {
        try {
            int targetIndex = Integer.parseInt(args.trim());
            System.out.println("TARGET: " + targetIndex);
            return new DeleteCommand(targetIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command prepareDone(String args) {
        try {
            int targetIndex = Integer.parseInt(args.trim());
            return new DoneCommand(targetIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    // TODO: add functionality to search by >1 keyword
    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Please pass a keyword to search for.");
        }
        return new FindCommand(matcher.group("keywords"));
    }

    private Command prepareFilter(String args) {
        final Matcher matcher = FILTER_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            System.out.println("no matches");
            return new IncorrectCommand("Please pass a proper date to filter by.");
        }
        try {
            LocalDate dateFilter = LocalDate.parse(matcher.group("dateFilter"));
            return new FilterCommand(dateFilter);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Please pass a proper date to filter by.");
        }
    }

}
