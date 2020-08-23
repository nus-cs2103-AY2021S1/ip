package duke.command;

import duke.task.*;
import duke.storage.*;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Encapsulates a Command which deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        if (index >= lib.size() || lib.size() == 0 || index < 0) {
            ui.printExceptions(new DukeException(
                    "This task ID does not exist in the database!"));
        } else {
            ui.printDeleteStatement(lib.get(index).toString(), lib.size() - 1);
            tasks.deleteTask(index);
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
