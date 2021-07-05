package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Encapsulates a Command which marks a task in the list as complete.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> lib = tasks.getTaskList();

        if (index >= lib.size() || lib.size() == 0 || index < 0) {
            return ui.printExceptions(new DukeException(
                    "This task ID does not exist in the database!"));
        } else {
            tasks.completeTask(index);
            return ui.printDoneStatement(lib.get(index).toString());
        }
    }

}
