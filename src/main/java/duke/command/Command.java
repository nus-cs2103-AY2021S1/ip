package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    public static boolean isTerminated = false;
    public abstract void execute(TaskList tasks, Ui ui);
}
