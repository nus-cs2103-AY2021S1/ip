package commands;

import service.DukeResponse;
import service.DukeService;

/**
 * This class represents ListCommand, which lists all tasks in the system.
 * Syntax: list
 */
public class ListCommand extends Command {
    public static final String commandWord = "list";

    /**
     * Constructor.
     * @param raw: raw command input by users
     */
    public ListCommand(String raw) {
        super(raw);
    }

    /**
     * Overriden method, to execute the command given the service
     * @param service: duke service
     * @return a duke response
     * @throws Exception if execution fails
     */
    @Override
    public DukeResponse execute(DukeService service) {
        return service.getAllJobs();
    }

    @Override
    public void parse() throws Exception {
    }
}
