package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.exec.AddCommand;
import duke.exec.DeleteCommand;
import duke.exec.DoneCommand;
import duke.exec.Executable;
import duke.exec.ExitCommand;
import duke.exec.FindCommand;
import duke.exec.ListCommand;
import duke.exec.SortCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class contains functionality that deals with making sense of user input
 */
public class Parser {

    // command constants for the bot
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String SORT_COMMAND = "sort";

    // DateTime format constant
    private static final DateTimeFormatter DATE_TIME_PARSE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a line of user input, creates and returns an Executable object
     * with the necessary information
     *
     * @param input the raw user input
     * @return the Executable obtained from parsing the input
     * @throws DukeException if encountered command has errors
     */
    public static Executable parse(String input) throws DukeException {
        String[] tokens = input.split("\\s+", 2); // the command and remaining details
        String command = tokens[0];
        String remaining = tokens.length == 1 ? null : tokens[1].strip();

        String[] taskItems;
        String desc;
        Task task;
        int index;

        switch (command) {
        case LIST_COMMAND:
            if (remaining != null) {
                throw DukeException.singleWordCommand();
            }
            return new ListCommand();
        case TODO_COMMAND:
            if (remaining == null) {
                throw DukeException.emptyDescription(); // empty description
            }
            task = new Todo(remaining);
            return new AddCommand(task);
        case DEADLINE_COMMAND:
            if (remaining == null) {
                throw DukeException.emptyTimeAndDescription(); // empty time and description
            }

            taskItems = remaining.split(" /by ");
            desc = taskItems[0].strip();

            if (taskItems.length == 1) {
                throw DukeException.emptyTimeOrDescription(); // empty time or description
            }

            try {
                LocalDateTime deadline = LocalDateTime.parse(taskItems[1].strip(), DATE_TIME_PARSE_FORMAT);
                task = new Deadline(desc, deadline);
                return new AddCommand(task);
            } catch (DateTimeParseException dateExcept) {
                throw DukeException.invalidDateFormat();
            }
        case EVENT_COMMAND:
            if (remaining == null) {
                throw DukeException.emptyTimeAndDescription(); // empty time and description
            }

            taskItems = remaining.split(" /at ");
            desc = taskItems[0].strip();

            if (taskItems.length == 1) {
                throw DukeException.emptyTimeOrDescription(); // empty time or description
            }

            try {
                LocalDateTime eventDate = LocalDateTime.parse(taskItems[1].strip(), DATE_TIME_PARSE_FORMAT);
                task = new Event(desc, eventDate);
                return new AddCommand(task);
            } catch (DateTimeParseException dateExcept) {
                throw DukeException.invalidDateFormat();
            }
        case DONE_COMMAND:
            if (remaining == null) {
                throw DukeException.missingParameters();
            }
            try {
                index = Integer.parseInt(remaining) - 1;
                return new DoneCommand(index);
            } catch (NumberFormatException numExcept) {
                throw DukeException.invalidInput();
            }
        case DELETE_COMMAND:
            if (remaining == null) {
                throw DukeException.missingParameters();
            }
            try {
                index = Integer.parseInt(remaining) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException numExcept) {
                throw DukeException.invalidInput();
            }
        case FIND_COMMAND:
            if (remaining == null) {
                throw DukeException.missingParameters();
            }
            desc = remaining;
            return new FindCommand(desc);
        case SORT_COMMAND:
            if (remaining != null) {
                throw DukeException.singleWordCommand();
            }
            return new SortCommand();
        case EXIT_COMMAND:
            if (remaining != null) {
                throw DukeException.singleWordCommand();
            }
            return new ExitCommand();
        default:
            throw DukeException.unknownOperation();
        }
    }
}
