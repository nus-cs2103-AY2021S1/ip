package duke.commands;

import duke.Storage;
import duke.Ui;

public abstract class Command {
    public abstract String execute(Ui ui, Storage storage);
}
