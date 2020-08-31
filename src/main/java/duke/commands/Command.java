package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Abstract class to define general command processing class.
 */
public abstract class Command {
    protected String attributes;

    public abstract boolean run(TaskList taskList, Storage storage, Ui ui);
}