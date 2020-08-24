package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui);

    public boolean isExit() {
        return false;
    }
}
