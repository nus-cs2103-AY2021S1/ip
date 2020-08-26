/**
 * This class contains functionality that deals with making sense of user input
 */
package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.exec.Executable;
import duke.exec.Add;
import duke.exec.Exit;
import duke.exec.Delete;
import duke.exec.Done;
import duke.exec.Listing;
import duke.exec.Find;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

public class Parser {

    // Command constants for the bot
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    // DateTime format constant
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a line of user input, creates and returns an Executable object
     * with the necessary information
     * @param input the raw user input
     * @return the Executable obtained from parsing the input
     * @throws DukeException if encountered command is
     */
    public static Executable parse(String input) throws DukeException {
        String[] tokens = input.split("\\s+", 2); // the command and remaining details
        String command = tokens[0];
        String remaining = tokens.length == 1 ? null : tokens[1].trim();

        String[] taskItems;
        String desc;
        Task task;
        int index;

        switch (command) {
        case LIST_COMMAND:
            if (remaining != null) {
                throw DukeException.unspecificCommand();
            }
            return new Listing();
        case TODO_COMMAND:
            if (remaining == null) {
                throw DukeException.emptyDescription(); // empty description
            }
            task = new Todo(remaining);
            return new Add(task);
        case DEADLINE_COMMAND:
            if (remaining == null) {
                throw DukeException.emptyDescription(); // empty description
            }

            taskItems = remaining.split(" /by ");
            desc = taskItems[0].trim();

            if (taskItems.length == 1) {
                throw DukeException.emptyTimeDescription(); // empty time
            }

            LocalDateTime deadline = LocalDateTime.parse(taskItems[1].trim(), format);
            task = new Deadline(desc, deadline);
            return new Add(task);
        case EVENT_COMMAND:
            if (remaining == null) {
                throw DukeException.emptyDescription(); // empty description
            }

            taskItems = remaining.split(" /at ");
            desc = taskItems[0].trim();

            if (taskItems.length == 1) {
                throw DukeException.emptyTimeDescription(); // empty time
            }

            LocalDateTime eventDate = LocalDateTime.parse(taskItems[1].trim(), format);
            task = new Event(desc, eventDate);
            return new Add(task);
        case DONE_COMMAND:
            if (remaining == null) {
                throw DukeException.invalidNumber();
            }
            index = Integer.parseInt(remaining) - 1;
            return new Done(index);
        case DELETE_COMMAND:
            if (remaining == null) {
                throw DukeException.invalidNumber();
            }
            index = Integer.parseInt(remaining) - 1;
            return new Delete(index);
        case FIND_COMMAND:
            if (remaining == null) {
                throw DukeException.missingParameters();
            }
            desc = remaining;
            return new Find(desc);
        case EXIT_COMMAND:
            if (remaining != null) {
                throw DukeException.unspecificCommand();
            }
            return new Exit();
        default:
            throw DukeException.unknownOperation();
        }
    }
}
