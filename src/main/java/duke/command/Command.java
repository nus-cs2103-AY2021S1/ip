package duke.command;

import duke.task.TaskList;
import duke.DukeException;

public abstract class Command {
    TaskList tasks;
    public boolean isExit;

    public Command(TaskList tasks) {
        this.tasks = tasks;
        this.isExit = false;
    }

    public void execute() throws DukeException { }
}
