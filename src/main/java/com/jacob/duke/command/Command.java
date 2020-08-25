package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;


public interface Command {
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException;

    public boolean isComplete();

    public boolean isBye();
}
