package duke.parsers;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.DateCommand;

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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
        if (userInput.equals("bye")) {
            return new ByeCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("done")) {
            if (userInput.substring(4).isEmpty()) {
                throw new MissingTaskIndexException();
            }
            int num = Integer.parseInt(userInput.substring(5));
            if (num <= 0 || num > tasks.size()) {
                throw new InvalidTaskIndexException();
            }
            return new DoneCommand(num - 1);
        } else if (userInput.startsWith("delete")) {
            if (userInput.substring(6).isEmpty()) {
                throw new MissingTaskIndexException();
            }
            int num = Integer.parseInt(userInput.substring(7));
            if (num <= 0 || num > tasks.size()) {
                throw new InvalidTaskIndexException();
            }
            return new DeleteCommand(num - 1);
        } else if (userInput.startsWith("todo")) {
            if (userInput.substring(4).isEmpty()) {
                throw new MissingTaskDescriptionException();
            }
            return new AddCommand(new ToDo(userInput.substring(5)));
        } else if (userInput.startsWith("deadline")) {
            if (userInput.substring(8).isEmpty()) {
                throw new MissingTaskDescriptionException();
            }
            if (!userInput.contains("/by")) {
                throw new MissingDateTimeException();
            }
            int pos = userInput.indexOf("/by");
            LocalDate date = LocalDate.parse(userInput.substring(pos + 4), FORMATTER_INPUT);
            return new AddCommand(new Deadline(userInput.substring(9, pos - 1), date));
        } else if (userInput.startsWith("event")) {
            if (userInput.substring(5).isEmpty()) {
                throw new MissingTaskDescriptionException();
            }
            if (!userInput.contains("/at")) {
                throw new MissingDateTimeException();
            }
            int pos = userInput.indexOf("/at");
            LocalDate date = LocalDate.parse(userInput.substring(pos + 4), FORMATTER_INPUT);
            return new AddCommand(new Event(userInput.substring(6, pos - 1), date));
        } else if (userInput.startsWith("date")) {
            return new DateCommand(LocalDate.parse(userInput.substring(5), FORMATTER_INPUT));
        } else if (userInput.startsWith("find")) {
            return new FindCommand(userInput.substring(5));
        } else {
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
            if (task.startsWith("[T]")) {
                tasks.add(new ToDo(isDone(task.substring(4, 5)), task.substring(7)));
            } else if (task.startsWith("[D]")) {
                int pos = task.indexOf("(by: ");
                tasks.add(new Deadline(isDone(task.substring(4, 5)),
                        task.substring(7, pos - 1),
                        LocalDate.parse(task.substring(pos + 5, task.length() - 1), FORMATTER_DISPLAY)));
            } else if (task.startsWith("[E]")) {
                int pos = task.indexOf("(at: ");
                tasks.add(new Event(isDone(task.substring(4, 5)),
                        task.substring(7, pos - 1),
                        LocalDate.parse(task.substring(pos + 5, task.length() - 1), FORMATTER_DISPLAY)));
            }
        }
        return tasks;
    }

    private boolean isDone(String symbol) {
        String tick = "" + '\u2713';
        return symbol.equals(tick);
    }
}
