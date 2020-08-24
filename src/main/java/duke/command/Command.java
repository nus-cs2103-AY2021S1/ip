package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public interface Command {
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    boolean isDone();
}
