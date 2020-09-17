package commands;

import service.DukeResponse;
import service.DukeService;

/**
 * Abstract commands
 */
public abstract class Command {
    protected String raw;
    protected boolean isParsed;

    /**
     * Constructs a new Command
     * @param raw a string
     */
    public Command(String raw) {
        this.raw = raw;
        this.isParsed = false;
    }

    public abstract DukeResponse execute(DukeService service) throws Exception;
    public abstract void parse() throws Exception;
}
