package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Deletes a task given by the user.
 * Inherits from generic AbstractModifyTaskCommand class.
 */
public class DeleteCommand extends AbstractModifyTaskCommand {

    public DeleteCommand(Task task) {
        super(task);
    }

    /**
     * Removes the task indicated by number, from the task list,
     * printing success and saving updated list to save file.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(taskListHandler handler, Storage storage) {
        try {
            ArrayList<Task> tasks = handler.getTasks();
            handler.getTasks().remove(task);
            Ui.printSuccess("delete", task, tasks.size());
            storage.saveToFile(tasks);
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
