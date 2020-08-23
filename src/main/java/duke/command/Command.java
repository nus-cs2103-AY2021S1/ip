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

    abstract public String execute(Ui ui, TaskList list, Storage storage) throws InvalidCommandException;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Command) {
            return input.equals(((Command) obj).input);
        } else {
            return false;
        }
    }
}
