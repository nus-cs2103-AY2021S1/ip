package duke.command;

import duke.Duke;

/**
 * The super class of all types of commands in Duke, cannot be instantiated.
 */
public abstract class Command {

    protected String[] names;
    protected String description = "no description provided.";
    protected String format = "no format provided.";

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
     * Gets the whole description of this command, containing format.
     * @return command description
     */
    public String getWholeDescription() {
        return description + "\nFormat: " + format;
    }

    public String getName() {
        String names = "";

        for (String name : this.names) {
            names += name + ", ";
        }

        // removes the ", " at the end
        names = names.substring(0, names.length() - 2);

        return names;
    }

    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }
}
