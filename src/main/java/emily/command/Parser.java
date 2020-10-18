package emily.command;

import emily.exception.DukeException;
import emily.task.Deadline;
import emily.task.Event;
import emily.task.Task;
import emily.task.ToDos;

/**
 * Deals with making sense of the user command
 */

public class Parser {

    Parser() {

    }

    /**
     * Convert the user input string to a proper Task
     *
     * @param str of user input
     * @return A new Task with the input details
     * @throws DukeException when the user input is invalid or is not in the proper form
     */
    public Task parse(String str) throws DukeException {
        Task item = new Task("");

        if (str.contains("todo")) {
            String describe = str.substring(5);
            item = new ToDos(describe);
            return item;
        } else if (str.contains("deadline")) {
            try {
                String description = str.substring(9);
                String[] temp = description.split("/by ");
                item = new Deadline(temp[0].trim(), temp[1].trim());
                return item;
            } catch (ArrayIndexOutOfBoundsException | java.time.DateTimeException e) {
                throw new DukeException("Format of deadline should be /by yyyy-mm-dd");
            }
        } else if (str.contains("event")) {
            try {
                String description = str.substring(6);
                String[] temp = description.split("/at ");
                item = new Event(temp[0].trim(), temp[1].trim());
                return item;
            } catch (ArrayIndexOutOfBoundsException | java.time.DateTimeException e) {
                throw new DukeException("Format of event should be /at yyyy-mm-dd HHmm");
            }
        } else {
            assert false : "String passed into parser fails logic";
            throw new DukeException("Unrecognised command");
        }
    }
}
