package duke.command;

import duke.Duke;

/**
 * The super class of all types of commands in Duke, cannot be instantiated.
 */
public abstract class Command {

    public String[] names;

    /**
     * Do something.
     * @param str the info needed for execution
     * @param duke the current Duke
     */
    public abstract void execute(String str, Duke duke);
}
