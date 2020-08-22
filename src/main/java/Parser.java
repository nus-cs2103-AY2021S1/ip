/**
 * Parser class.
 * Used to parse commands input by user.
 *
 * @author YanCheng
 */

public class Parser {

    public TaskList taskList;
    public Storage storage;

    /**
     * Constructor for Parser class.
     * @param taskList a TaskList object
     * @param storage a Storage object
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Handles an event input.
     * @param input Input from user
     * @throws DukeException If description or date is missing
     */
    // example of event: event meeting /at 2020-08-22 14:00-16:00
    public void handleEvent(String input) throws DukeException {
        // since input is confirmed to have "event",
        // just need to ensure that stripped input > 5 letters
        // input needs to be stripped to prevent trailing whitespaces eg. "deadline    "
        if (input.strip().length() <= 5) {
            throw new DukeException(" ☹ OOPS!!! The description of a Event cannot be empty.");
        } else if (!input.contains("/")) {
            throw new DukeException(" ☹ OOPS!!! Event requires a date.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            String[] arr = taskName.split("/");
            Event task = new Event(arr[0], arr[1].substring(arr[1].indexOf(" ") + 1));
            taskList.add(task);
            storage.save();
        }
    }

    /**
     * Handles a deadline input.
     * @param input Input from user
     * @throws DukeException If description or date is missing
     */
    // example of deadline: deadline return book /by 2020-08-22
    public void handleDeadline(String input) throws DukeException {
        // since input is confirmed to have "deadline",
        // just need to ensure that stripped input > 8 letters
        // input needs to be stripped to prevent trailing whitespaces eg. "deadline    "
        if (input.strip().length() <= 8) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!input.contains("/")) {
            throw new DukeException(" ☹ OOPS!!! Deadline requires a date.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            String[] arr = taskName.split("/");
            Deadline task = new Deadline(arr[0], arr[1].substring(arr[1].indexOf(" ") + 1));
            taskList.add(task);
            storage.save();
        }
    }

    /**
     * Handles a to do input.
     * @param input Input from user
     * @throws DukeException If description is missing
     */
    // example of To do: to do return book
    public void handleToDo(String input) throws DukeException {
        // since input is confirmed to have "to do",
        // just need to ensure that stripped input > 4 letters
        // input needs to be stripped to prevent trailing whitespaces eg. "deadline    "
        if (input.strip().length() <= 4) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            //String taskType = input.substring(0, input.indexOf(" "));
            String taskName = input.substring(input.indexOf(" ") + 1);
            ToDo task = new ToDo(taskName);
            taskList.add(task);
            storage.save();
        }
    }
}
