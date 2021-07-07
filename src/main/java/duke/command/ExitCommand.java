package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.logic.Ui;
import duke.task.TaskList;


public class ExitCommand implements Command {
    private TaskList tasks;

    public ExitCommand() {
        this.tasks = tasks;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Storage.save(tasks);
        // do storage stuff
        return Ui.exitMessage();
    }

    @Override
    public boolean setIsFinished() {
        return true;
    }
}
