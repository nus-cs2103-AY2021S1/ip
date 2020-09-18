package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
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
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        runGUI(taskList, storage, ui);
        return true;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(Integer.parseInt(attributes));
        storage.storeList(taskList.getList());
        return ui.writeDelete(deletedTask, taskList.getSize());
    }
}
