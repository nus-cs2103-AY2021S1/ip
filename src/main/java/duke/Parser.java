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
     * @param str   user's input
     * @param tasks current list of tasks
     * @throws DukeException when user input is not a valid command
     */
    public String command(String str, TaskList tasks) throws DukeException {
        assert (!str.equals("bye"));
        if (str.equals("list")) {
            return tasks.displayTasks();
        } else if (str.startsWith("find")) {
            return tasks.find(str);
        } else if (str.startsWith("done")) {
            return tasks.completeTask(str);
        } else if (str.startsWith("delete")) {
            return tasks.deleteTask(str);
        } else if (str.startsWith("todo") | str.startsWith("event") | str.startsWith("deadline")) {
            return tasks.addTask(str);
        } else if (str.equals("clear")) {
            assert tasks.getTasks().size() == 0;
            return tasks.clear();
        } else {
            throw new InvalidCommandException();
        }
    }
}

