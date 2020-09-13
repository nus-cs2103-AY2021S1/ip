package duke.command;

/** A subclass of Command which sends request to terminate the bot */
public class ExitCommand extends Command {
    protected static final String EXIT_REQUEST = "end";

    public ExitCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String sendRequest() {
        return EXIT_REQUEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
