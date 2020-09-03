package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {
    TaskList tasks;

    public ExitCommand() {
        this.tasks = tasks;
    }

    @Override
    public String execute(TaskList tasks) {
        return Ui.exitMessage();
        // do storage stuff
    }

    @Override
    public boolean setIsFinished() {
        return true;
    }
}
