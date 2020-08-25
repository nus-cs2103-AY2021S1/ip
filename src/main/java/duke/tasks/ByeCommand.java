package duke.tasks;

/**
 * Represents a Bye Command. This command handles the bye input from users
 * to exit Duke.
 */
public class ByeCommand extends Command {
    public ByeCommand() { }

    public boolean isExit() {
        return true;
    }
}
