package duke.commands;

import duke.TaskManager;
import duke.Ui;

public abstract class Command {

    String input;
    public boolean isExit;

    public abstract void execute(TaskManager taskManager, Ui ui);
}
