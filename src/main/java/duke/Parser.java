package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

/**
 * Processes the user's commands.
 */

public class Parser {

    /**
     * Takes in a line of user input and passes it to the TaskList to handle.
     *
     * @param str user's input
     * @param tasks current list of tasks
     * @throws DukeException when user input is not a valid command
     */
    public String command(String str, TaskList tasks) throws DukeException {
        if (str.equals("list")) {
            return tasks.displayTasks();
        } else if (str.startsWith("find")) {
            return tasks.find(str);
        } else {
            String result;
            if (str.startsWith("done")) {
                result = tasks.completeTask(str);
            } else if (str.startsWith("delete")) {
                result = tasks.deleteTask(str);
            } else if (str.startsWith("todo") | str.startsWith("event") | str.startsWith("deadline")) {
                result = tasks.addTask(str);
            } else if (str.equals("clear")) {
                result = tasks.clear();
            } else {
                throw new InvalidCommandException();
            }
            return result;
        }
    }
}
