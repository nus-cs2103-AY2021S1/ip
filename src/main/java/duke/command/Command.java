package main.java.duke.command;

import main.java.duke.exception.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public abstract class Command {

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * @return
     */
    public abstract boolean isExit();

}
