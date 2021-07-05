package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a Command which deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        if (index >= lib.size() || lib.size() == 0 || index < 0) {
            return ui.printExceptions(new DukeException(
                    "This task ID does not exist in the database!"));
        } else {
            String task = lib.get(index).toString();
            int size = lib.size() - 1;
            tasks.deleteTask(index);

            return ui.printDeleteStatement(task, size);
        }
    }

}
