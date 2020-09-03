package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public Command() {
    }

    public abstract String run(TaskList taskList, Storage storage);

    public boolean isBye() {
        return false;
    }

}
