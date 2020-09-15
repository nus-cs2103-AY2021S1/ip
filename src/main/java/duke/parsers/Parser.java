package duke.parsers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.StatsCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidDukeCommandException;
import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingTaskDescriptionException;
import duke.exceptions.MissingTaskIndexException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/** Represents a parser that parses the user inputs and the list saved in the hard disk into a suitable format
 * for Duke to process. */
public class Parser {

    /** The formatter for user inputs. */
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** The formatter for user inputs that are displayed in the list saved in the hard disk. */
    private static final DateTimeFormatter FORMATTER_DISPLAY = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /** Parses the user inputs into a suitable format for Duke to process.
     *
     * @param userInput The user input fed into Duke.
     * @param tasks The list that stores tasks.
     * @return An appropriate command to be executed.
     * @throws DukeException If the userInput is invalid or missing.
     */
    public Command parse(String userInput, ArrayList<Task> tasks) throws DukeException {
        String[] inputCommandAndArgument = userInput.split(" ", 2);
        String command = inputCommandAndArgument[0];
        String argument = inputCommandAndArgument.length == 2 ? inputCommandAndArgument[1] : "";
        if (userInput.equals("bye")) {
            return new ByeCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done") || command.equals("delete")) {
            return parseDoneDelete(command, argument, tasks.size());
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            return parseAdd(command, argument);
        } else if (command.equals("date")) {
            return new DateCommand(LocalDate.parse(argument, FORMATTER_INPUT));
        } else if (command.equals("find")) {
            return new FindCommand(argument);
        } else if (command.equals("stats")) {
            return new StatsCommand();
        } else {
            throw new InvalidDukeCommandException();
        }
    }

    private Command parseDoneDelete(String command, String argument, int size) {
        if (argument.isEmpty()) {
            throw new MissingTaskIndexException();
        }
        int index = Integer.parseInt(argument);
        if (index <= 0 || index > size) {
            throw new InvalidTaskIndexException();
        }
        if (command.equals("done")) {
            return new DoneCommand(index - 1);
        } else if (command.equals("delete")) {
            return new DeleteCommand(index - 1);
        } else {
            assert false : "Oh no! This invalid Duke Command scenario should be handled earlier.";
            throw new InvalidDukeCommandException();
        }
    }

    private Command parseAdd(String command, String argument) {
        if (argument.isEmpty()) {
            throw new MissingTaskDescriptionException();
        }
        if (command.equals("todo")) {
            return new AddCommand(new ToDo(argument));
        } else if (command.equals("deadline")) {
            if (!argument.contains(" /by ")) {
                throw new MissingDateTimeException();
            }
            String[] argumentDescriptionAndDate = argument.split(" /by ");
            String description = argumentDescriptionAndDate[0];
            String date = argumentDescriptionAndDate[1];
            return new AddCommand(new Deadline(description, LocalDate.parse(date, FORMATTER_INPUT)));
        } else if (command.equals("event")) {
            if (!argument.contains(" /at ")) {
                throw new MissingDateTimeException();
            }
            String[] argumentDescriptionAndDate = argument.split(" /at ");
            String description = argumentDescriptionAndDate[0];
            String date = argumentDescriptionAndDate[1];
            return new AddCommand(new Event(description, LocalDate.parse(date, FORMATTER_INPUT)));
        } else {
            assert false : "Oh no! This invalid Duke Command scenario should be handled earlier.";
            throw new InvalidDukeCommandException();
        }
    }

    /** Parses the user inputs in the list saved in the hard disk into a suitable format for Duke to process.
     *
      * @param savedTaskList The list of user inputs in the list saved in the hard disk.
     * @return A list of tasks.
     */
    public ArrayList<Task> parseSavedTaskList(ArrayList<String> savedTaskList) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String task : savedTaskList) {
            String[] taskParts = task.split(" ", 2);
            String type = "" + taskParts[0].charAt(1);
            String status = "" + taskParts[0].charAt(4);
            String argument = taskParts[1];
            if (type.equals("T")) {
                tasks.add(new ToDo(isDone(status), argument));
            } else if (type.equals("D")) {
                String[] argumentDescriptionAndDate = argument.split(" \\(by: ");
                String description = argumentDescriptionAndDate[0];
                String date = argumentDescriptionAndDate[1];
                tasks.add(new Deadline(isDone(status), description, LocalDate.parse(date, FORMATTER_DISPLAY)));
            } else if (type.equals("E")) {
                String[] argumentDescriptionAndDate = argument.split(" \\(at: ");
                String description = argumentDescriptionAndDate[0];
                String date = argumentDescriptionAndDate[1];
                tasks.add(new Event(isDone(status), description, LocalDate.parse(date, FORMATTER_DISPLAY)));
            } else {
                assert false : "Oh no! An invalid task type has been passed.";
            }
        }
        return tasks;
    }

    private boolean isDone(String symbol) {
        return symbol.equals("O");
    }
}
