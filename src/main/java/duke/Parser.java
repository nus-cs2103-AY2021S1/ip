package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

/**
 * Processes the user's commands.
 */

public class Parser {

    /**
     * Takes in a line of user input and passes it to the TaskList to handle.
     * @param str user's input
     * @param tasks current list of tasks
     * @param storage location of saved tasklist
     * @throws DukeException when user input is not a valid command
     */

    public void command(String str, TaskList tasks, Storage storage) throws DukeException {
        if (str.equals("list")) {
            tasks.displayTasks();
        } else {
            if (str.startsWith("done")) {
                tasks.completeTask(str);
                storage.save(tasks);
            } else if (str.startsWith("delete")) {
                tasks.deleteTask(str);
                storage.save(tasks);
            } else if (str.startsWith("todo") | str.startsWith("event") | str.startsWith("deadline")) {
                tasks.addTask(str);
                storage.save(tasks);
            } else if (str.startsWith("find")) {
                tasks.find(str);
            } else if (str.equals("clear")) {
                tasks.clear();
                storage.save(tasks);
            } else {
                throw new InvalidCommandException();
            }
        }
    }
}
