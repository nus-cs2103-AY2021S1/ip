package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

/**
 * Inherits from generic command class.
 */
public class FindCommand extends Command {
    protected String input;

    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds tasks matching the keyword.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     * @throws DukeException if keyword to search for is empty.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) throws DukeException {
        // Second word is the keyword to search for
        String keyword = input.substring(5);
        ArrayList<Task> foundTasks = handler.findTasksByKeyword(keyword);
        if (!foundTasks.isEmpty()) {
            Ui.printSuccess("find", foundTasks.get(0), foundTasks);
        } else {
            // Unable to find any matching task
            Ui.indent(1);
            System.out.println("I couldn't find any tasks matching " + '"' + keyword + '"' + ".");
        }
    }
}
