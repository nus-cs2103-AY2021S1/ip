package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command sub-type to define deleting Tasks
 */
public class DeleteCommand extends Command {

    /**
     * Creates DeleteCommand object.
     *
     * @param attributes input attributes from user
     */
    public DeleteCommand(String attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(Integer.parseInt(attributes));
        ui.writeDelete(deletedTask, taskList.getSize());
        storage.storeList(taskList.getList());
        return true;
    }

    @Override
    public String runNew(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(Integer.parseInt(attributes));
        storage.storeList(taskList.getList());
        return ui.writeDelete(deletedTask, taskList.getSize());
    }
}
