package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeArgumentException;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Utility class for reading input and files
 */
public final class Parser {

    static final String REGEX_SEPARATOR = ",, ";

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String FIND = "find";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Reads a String and splits it to create a new duke.Tasks.Task based on its type and values.
     * Format: Type | Completed | Name | Time
     * Example: "[D],, 1,, deadline,, 23-08-2020"
     *
     * @param line the String from the csv to be parsed
     * @return A duke.Tasks.Task object
     * @throws DukeException if the line does not follow the given regex.
     */
    public static Task parseLine(String line) throws DukeIoException {
        String[] values = line.split(REGEX_SEPARATOR);
        switch (values[0]) {
        case "[T]":
            return new Todo(values[2], values[1]);
        case "[E]":
            return new Event(values[2], LocalDate.parse(values[3], DateTimeFormatter.ofPattern("MMM d yyyy")),
                    values[1]);
        case "[D]":
            return new Deadline(values[2], LocalDate.parse(values[3], DateTimeFormatter.ofPattern("MMM d yyyy")),
                    values[1]);
        default:
            throw new DukeIoException(String.format("The line '%s' could not be parsed.", line));
        }
    }

    /**
     * Converts a Task into a String format for writing to disk.
     *
     * @param task the Task to be converted
     * @return the String representation of the Task to be used in the data file.
     */
    public static String convertTask(Task task) {
        String[] args = task.toArray();
        String result = "";
        for (String s : args) {
            result = result.concat(String.format("%s%s", s, REGEX_SEPARATOR));
        }
        return result + "\n";
    }

    /**
     * Converts input text into the appropriate command.
     *
     * @param text the input text from the user
     * @return the Command to be executed
     * @throws DukeArgumentException if the input text did not match any existing Command types.
     */
    public static Command parseCommand(String text) throws DukeArgumentException {
        String[] parsedInput = text.split(" ", 2);
        try {
            switch (parsedInput[0].toLowerCase()) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case FIND:
                return new FindCommand(parsedInput[1]);
            case DONE:
                return new DoneCommand(parsedInput[1]);
            case DELETE:
                return new DeleteCommand(parsedInput[1]);
            case TODO:
                return new TodoCommand(parsedInput[1]);
            case EVENT:
                return new EventCommand(parsedInput[1]);
            case DEADLINE:
                return new DeadlineCommand(parsedInput[1]);
            default:
                throw new DukeArgumentException("Command did not match any known commands.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeArgumentException("Insufficient arguments for the command.");
        }
    }


}
