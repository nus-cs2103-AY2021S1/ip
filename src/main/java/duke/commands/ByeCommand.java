package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Exits the programme.
 */
public class ByeCommand extends Command {

    public ByeCommand() {
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        storage.write(taskList.getList());
        return Ui.bye();
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
