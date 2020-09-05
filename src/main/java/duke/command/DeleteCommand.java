package duke.command;

import java.io.IOException;

import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.handle.TaskNotFoundException;
import duke.task.Task;

/**
 * The DeleteCommand class represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private int count;

    /**
     * Takes in the count of the task to be deleted and returns a delete command.
     *
     * @param count The count of the task.
     */
    public DeleteCommand(int count) {
        this.count = count;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and deletes a task from
     * the task list and updates the local record using storage.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        if (!taskList.has(count - 1)) {
            throw new TaskNotFoundException("There is no such task");
        }

        Task task = taskList.remove(count - 1);

        storage.writeRecord(taskList);

        return new Result(ui.getDeleteMessage(task, count, taskList.getSize()), this.isContinuing());
    }
}
