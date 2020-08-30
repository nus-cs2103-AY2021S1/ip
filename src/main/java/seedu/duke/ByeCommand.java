package seedu.duke;

/**
 * A command that represents shutting down Duke.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    /**
     * Checks if this command shuts down Duke.
     * @return Boolean that represents if Duke should be shut down.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
