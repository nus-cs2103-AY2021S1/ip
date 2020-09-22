package duke.command;

import duke.logic.Ui;
import duke.task.TaskList;


public class ExitCommand implements Command {
    private TaskList tasks;

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
