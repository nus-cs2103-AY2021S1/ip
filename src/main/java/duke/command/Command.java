package duke.command;

import duke.Duke;

/**
 * The super class of all types of commands in Duke, cannot be instantiated.
 */
public abstract class Command {

    protected String[] names;
    protected String description = "no description provided.";

    /**
     * Do something.
     * @param str the info needed for execution
     * @param duke the current Duke
     */
    public abstract void execute(String str, Duke duke);

    /**
     * Gets the names of the command.
     * @return the array of names
     */
    public String[] getNames() {
        return names;
    }

    /**
     * Gets the description of this command.
     * @return command description
     */
    public String getDescription() {
        return description;
    }
}
