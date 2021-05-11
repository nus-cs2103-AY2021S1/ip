package duke.commands;

import duke.Storage;
import duke.Ui;

/**
 * Abstract class to represent a command the Parser issues
 */
public abstract class Command {
    public abstract String execute(Ui ui, Storage storage);
}
