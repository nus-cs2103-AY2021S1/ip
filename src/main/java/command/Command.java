package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a single Command with or without parameters
 *
 * @author  Ryan Lim
 */
public abstract class Command {
    /** array of strings where each element represents a parameter tagged to the command */
    protected final String[] parameters;
    protected final boolean executedSuccessfully = true;
    protected final boolean executedUnsuccessfully = false;

    /**
     * Instantiate command without parameters
     */
    public Command() {
        this.parameters = new String[0];
    }

    /**
     * Instantiate command with parameters
     * @param parameters Array of parameters represented as strings.
     */
    public Command(String ...parameters) {
        this.parameters = parameters;
    }

    /**
     * Execute the command
     * @return The result of executing the command
     */
    public abstract Result execute(TaskList taskList, Storage storage);
}
