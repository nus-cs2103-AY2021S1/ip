package duke.command;

import duke.component.*;

public abstract class Command {
    protected final String input;

    /**
     * Creates a command with the given input.
     * @param input the input command
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Tells whether this command is aiming for exiting.
     * @return true if this is a ByeCommand, this is to be overridden in ByeCommand
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command, prints the result on ui and writes to source data file if necessary.
     * @param ui the user interface object that is currently running
     * @param list the current list of tasks
     * @param storage the storage-writing object that is currently running
     * @return the string for test cases
     * @throws InvalidCommandException if the input command doesn't make sense and states why
     */
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
