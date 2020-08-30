package duke.commands;

/**
 * <code>duke.commands.ErrorCommand</code> inherits from the base class <code>duke.commands.Command</code>
 * and will handle the situation when the user gives an invalid command.
 */
public class ErrorCommand extends Command {
    /**
     * Prints out "Oops, you gave an invalid command.
     * @return <code>true</code>
     */
    public boolean execute() {
        // TODO: refactor this to the ui class
        System.out.println("Oops, you gave an invalid command");
        return true;
    }
}