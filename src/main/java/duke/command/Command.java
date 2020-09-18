package main.java.duke.command;

import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.DukeException;
import main.java.duke.Ui;
import main.java.duke.Storage;

public abstract class Command {
    public Command() {}

    public abstract boolean isExit();
    public abstract String execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeException;
}
