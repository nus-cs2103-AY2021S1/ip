package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EmptyCommand extends Command{
    public EmptyCommand() {
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.accessMissing();
    }

}
