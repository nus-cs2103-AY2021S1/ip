package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.tasks.Task;

/**
 * Command sub-type to define marking Tasks as done
 */
public class DoneCommand extends Command {

    /**
     * Creates DoneCommand object.
     *
     * @param attributes input attributes from user
     */
    public DoneCommand(String attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task markedTask = taskList.markDone(Integer.parseInt(attributes));
        ui.writeDone(markedTask);
        storage.storeList(taskList.getList());
        return true;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task markedTask = taskList.markDone(Integer.parseInt(attributes));
        storage.storeList(taskList.getList());
        return ui.writeDone(markedTask);
    }
}
