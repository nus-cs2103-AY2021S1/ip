package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    /**
     * Executes the command, listing all tasks.
     *
     * @param tasks TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException if there are no tasks in the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getTasks().size() == 0) {
            throw new DukeException("There are no tasks in your list.");
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        for (Task task : tasks.getTasks()) {
            output.append(tasks.getTasks().indexOf(task) + 1).append(".").append(task).append('\n');
        }

        ui.showPrompt(output.toString());
    }
}
