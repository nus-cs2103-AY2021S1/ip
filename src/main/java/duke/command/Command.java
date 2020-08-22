package duke.command;

import duke.component.*;

public abstract class Command {
    protected final String input;

    public Command(String input) {
        this.input = input;
    }

    public boolean isExit() {
        return false;
    }

    abstract public void execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException;
}
