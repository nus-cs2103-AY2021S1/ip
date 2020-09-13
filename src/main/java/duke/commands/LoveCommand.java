package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class LoveCommand extends Command {
    public LoveCommand() {

    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.love();
    }
}
