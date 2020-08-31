package Duke.command;

import Duke.task.TaskList;
import Duke.utils.Ui;

public abstract class Command {
    public abstract  boolean isExit();
    public abstract void execute(TaskList tasks, Ui ui);
}
