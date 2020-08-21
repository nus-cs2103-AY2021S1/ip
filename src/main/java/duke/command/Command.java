package duke.command;

import duke.component.TaskList;
import duke.component.Storage;
import duke.component.Ui;

public abstract class Command {
    public boolean isExit;
    protected final String input;

    public Command(String input) {
        isExit = false;
        this.input = input;
    }

    abstract public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException;
}
