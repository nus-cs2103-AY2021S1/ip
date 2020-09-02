package duke.command;

import java.io.IOException;

import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.task.Task;

/**
 * The AddCommand class represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Takes in a task to be added and returns a command.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and adds a task to
     * the task list and updates the local record using storage.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws IOException If the storage process needs to be handled
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(task);
        storage.writeRecord(taskList);

        return new Result(ui.showAdd(task, taskList.getSize()), this.isContinuing());
    }
}
