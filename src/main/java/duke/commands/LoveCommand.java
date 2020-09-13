package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.Ui;

public class LoveCommand extends Command {
    public LoveCommand() {

    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.love();
    }
}
