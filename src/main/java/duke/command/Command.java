package duke.command;

/**
 * Represents a command the user inputs.
 */

import duke.component.*;

public abstract class Command {
    protected final String input;

    /**
     * Creates a command with the given input.
     * @param input The input command.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Tells whether this command is aiming for exiting.
     * @return True if this is a ByeCommand, this is to be overridden.
     */
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
