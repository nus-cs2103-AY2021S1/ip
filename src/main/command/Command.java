package main.command;

import main.ui.Ui;
import main.task.TaskList;
import main.exception.InvalidTaskException;


public interface Command {
    public void execute(Ui ui, TaskList tasks) throws InvalidTaskException;

    public boolean hasCommand();
}
