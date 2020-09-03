package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {
    TaskList tasks;

    public ExitCommand() {
        this.tasks = tasks;
    }

    @Override
    public void execute(TaskList tasks) {
        Ui.exitMessage();
        // do storage stuff
    }

    @Override
    public boolean setIsFinished() {
        return true;
    }
}
