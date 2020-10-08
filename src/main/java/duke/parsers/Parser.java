package duke.parsers;

import static duke.utils.Messages.MESSAGE_HANDLED_INVALID_COMMAND_ASSERTION;

import java.util.Arrays;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.ConfirmCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TodayCommand;
import duke.exceptions.DukeDateTimeParseException;
import duke.exceptions.EmptyDueDateException;
import duke.exceptions.EmptyEventDateException;
import duke.exceptions.EmptySearchWordException;
import duke.exceptions.EmptyTaskDeletedException;
import duke.exceptions.EmptyTaskDescriptionException;
import duke.exceptions.EmptyTaskDoneException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidCommandFormatException;
import duke.exceptions.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.utils.DukeDateTime;

/**
 * Represents a parser that parses user inputs into the relevant commands.
 */
public class Parser {

    /**
     * Parses the user input into the relevant command.
     *
     * @param userInput The user input.
     * @return The command that corresponds to the user input.
     * @throws EmptyTaskDescriptionException If the user inputs a task without the description.
     * @throws DukeDateTimeParseException    If the user inputs the date or time in a wrong format.
     * @throws EmptyTaskDoneException        If the user inputs done without a task.
     * @throws EmptyTaskDeletedException     If the user inputs deleted without a task.
     * @throws EmptyDueDateException         If the user inputs deadline without a due date.
     * @throws EmptyEventDateException       If the user inputs an event without the date and/or time.
     * @throws EmptySearchWordException      If the user inputs find without a search word.
     */
    public static Command parse(String userInput) throws EmptyTaskDescriptionException, DukeDateTimeParseException,
            EmptyTaskDoneException, EmptyTaskDeletedException,
            EmptyDueDateException, EmptyEventDateException, EmptySearchWordException {

        String[] splitByCommand = userInput.strip().split("\\s+", 2);
        switch (splitByCommand[0].strip().toLowerCase()) {
        case "bye":
            return parseBye();
        case "list":
            return parseList();
        case "done":
            if (splitByCommand.length < 2) {
                throw new EmptyTaskDoneException();
            }
            return parseDone(splitByCommand[1].strip());
        case "todo":
        case "deadline":
        case "event":
            if (splitByCommand.length < 2) {
                throw new EmptyTaskDescriptionException(splitByCommand[0].strip());
            }
            return parseAdd(splitByCommand[0].strip(), splitByCommand[1].strip());
        case "delete":
            if (splitByCommand.length < 2) {
                throw new EmptyTaskDeletedException();
            }
            return parseDelete(splitByCommand[1].strip());
        case "today":
            return parseToday();
        case "find":
            if (splitByCommand.length < 2) {
                throw new EmptySearchWordException();
            }
            return parseFind(splitByCommand[1].strip().toLowerCase());
        case "confirm":
            if (splitByCommand.length < 2) {
                throw new InvalidCommandFormatException();
            }
            return parseConfirm(splitByCommand[1].strip());
        case "help":
            return parseHelp();
        default:
            throw new InvalidCommandException();
        }
    }

    private static Command parseHelp() {
        return new HelpCommand();
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
            String[] splitByTaskAndDate = arguments.split(" /by ");
            if (arguments.startsWith("/by")) {
                throw new EmptyTaskDescriptionException(commandName);
            }
            if (splitByTaskAndDate.length < 2) {
                throw new EmptyDueDateException();
            }
            task = new Deadline(splitByTaskAndDate[0], DukeDateTimeParser.parse(splitByTaskAndDate[1]));
            break;
        }
        case "event": {
            String[] splitByTaskAndDate = arguments.split(" /at ");
            if (arguments.startsWith("/at")) {
                throw new EmptyTaskDescriptionException(commandName);
            }
            if (splitByTaskAndDate.length < 2) {
                throw new EmptyEventDateException();
            }

            DukeDateTime[] multipleDates = Arrays.stream(splitByTaskAndDate[1].split(", "))
                    .map(DukeDateTimeParser::parse)
                    .toArray(DukeDateTime[]::new);
            task = new Event(splitByTaskAndDate[0], multipleDates);
            break;
        }
        default:
            assert false : MESSAGE_HANDLED_INVALID_COMMAND_ASSERTION;
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

    private static FindCommand parseFind(String searchWords) {
        return new FindCommand(searchWords.split("\\s+"));
    }

    private static ListCommand parseList() {
        return new ListCommand();
    }

    private static ByeCommand parseBye() {
        return new ByeCommand();
    }

    private static ConfirmCommand parseConfirm(String arguments) throws InvalidCommandFormatException {
        String[] argumentArray = arguments.split(" ");
        try {
            int taskIndex = Integer.parseInt(argumentArray[0]) - 1;
            int tentativeIndex = Integer.parseInt(argumentArray[1]) - 1;
            return new ConfirmCommand(taskIndex, tentativeIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException();
        }
    }
}
