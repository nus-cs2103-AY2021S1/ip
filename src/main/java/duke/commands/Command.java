package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui);

    public boolean isExit() {
        return false;
    }
}