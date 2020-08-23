package duke.command;

public class findCommand extends Command {
    /**
     * Creates a find command.
     * @param input the input command
     */
    public findCommand(String input) {
        super(input);
    }

    /**
     * Checks whether the given command equals this one.
     * @param obj the object to compare
     * @return true if obj is a FindCommand and command input is the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof findCommand) {
            return input.equals(((findCommand) obj).input);
        } else {
            return false;
        }
    }
}
