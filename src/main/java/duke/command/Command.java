package main.java.duke.command;

import main.java.duke.task.TaskList;
import main.java.duke.dukeexception.DukeException;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    public Command() {}

    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage)
            throws main.java.duke.dukeexception.DukeException;
}
