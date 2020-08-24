package duke.parsers;

import duke.commands.*;
import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/** Represents a parser that parses user inputs into the relevant commands. */
public class Parser {

    /** Parses the user input into the relevant command.
     *
     * @param userInput The user input.
     * @return The command that corresponds to the user input.
     * @throws EmptyTaskDescriptionException If the user inputs a task without the description.
     * @throws DukeDateTimeParseException If the user inputs the date or time in a wrong format.
     * @throws EmptyTaskDoneException If the user inputs done without a task.
     * @throws EmptyTaskDeletedException If the user inputs deleted without a task.
     * @throws EmptyDueDateException If the user inputs deadline without a due date.
     * @throws EmptyEventDateException If the user inputs an event without the date and/or time.
     */
    public static Command parse(String userInput) throws EmptyTaskDescriptionException, DukeDateTimeParseException,
            EmptyTaskDoneException, EmptyTaskDeletedException,
            EmptyDueDateException, EmptyEventDateException {

        String[] arr = userInput.strip().split(" ", 2);
        switch (arr[0].strip().toLowerCase()) {
            case "bye":
                return parseBye();
            case "list":
                return parseList();
            case "done":
                if (arr.length < 2) {
                    throw new EmptyTaskDoneException();
                }
                return parseDone(arr[1].strip());
            case "todo":
            case "deadline":
            case "event":
                if (arr.length < 2) {
                    throw new EmptyTaskDescriptionException(arr[0].strip());
                }
                return parseAdd(arr[0].strip(), arr[1].strip());
            case "delete":
                if (arr.length < 2) {
                    throw new EmptyTaskDeletedException();
                }
                return parseDelete(arr[1].strip());
            case "today":
                return parseToday();
            default:
                throw new InvalidCommandException();
        }
    }

    private static Command parseToday() {
        return new TodayCommand();
    }

    private static AddCommand parseAdd(String commandName, String arguments)
            throws EmptyTaskDescriptionException, DukeDateTimeParseException,
            EmptyEventDateException, EmptyDueDateException {
        Task task = null;
        if (arguments.equals("")) {
            throw new EmptyTaskDescriptionException(commandName);
        }
        switch (commandName) {
            case "todo":
                task = new Todo(arguments);
                break;
            case "deadline": {
                String[] parsed = arguments.split(" /by ");
                if (arguments.startsWith("/by")) {
                    throw new EmptyTaskDescriptionException(commandName);
                }
                if (parsed.length < 2) {
                    throw new EmptyDueDateException();
                }
                task = new Deadline(parsed[0], DukeDateTimeParser.parse(parsed[1]));
                break;
            }
            case "event": {
                String[] parsed = arguments.split(" /at ");
                if (arguments.startsWith("/at")) {
                    throw new EmptyTaskDescriptionException(commandName);
                }
                if (parsed.length < 2) {
                    throw new EmptyEventDateException();
                }
                task = new Event(parsed[0], DukeDateTimeParser.parse(parsed[1]));
                break;
            }
            default:
                throw new AssertionError("Invalid command scenario has been handled earlier.");
        }
        return new AddCommand(task);
    }


    private static DoneCommand parseDone(String taskDone) throws InvalidTaskException {
        try {
            int index = Integer.parseInt(taskDone) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static DeleteCommand parseDelete(String taskDeleted) throws InvalidTaskException {
        try {
            int index = Integer.parseInt(taskDeleted) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static ListCommand parseList() {
        return new ListCommand();
    }

    private static ByeCommand parseBye() {
        return new ByeCommand();
    }
}
