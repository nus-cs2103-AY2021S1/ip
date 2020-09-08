package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;
import duke.task.Task;

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
    public void execute(TaskListHandler handler, Storage storage) throws DukeException {
        ArrayList<Task> taskList = handler.getTasks();
        handler.getTasks().remove(task);
        Ui.printSuccess("delete", task, taskList);
        storage.saveToFile(taskList);
    }
}
