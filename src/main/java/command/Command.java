package command;

import exceptions.DukeException;
import task.TaskList;

public abstract class Command {
    TaskList tasks;
    public boolean isExit;

    public Command(TaskList tasks) {
        this.tasks = tasks;
        this.isExit = false;
    }

    public void execute() throws DukeException { }
}
