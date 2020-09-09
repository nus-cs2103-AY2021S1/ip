package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
