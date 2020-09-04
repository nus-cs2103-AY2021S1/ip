package duke.command;

import duke.CommandAgent;
import duke.DukeException;

/** A subclass of Command which sends request to mark a task in list as done */
public class DoneCommand extends Command {
    private static final String DONE_REQUEST = "update";
    
    /**
     * Creates a DoneCommand.
     *
     * @param content The task information supplied by the user.
     * @throws DukeException If the content has no input or improper input.
     */
    public DoneCommand(String content) throws DukeException {
        if (content.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please enter a value");
        } else if (!(Character.isDigit(content.charAt(0)))) {
            throw new DukeException("☹ OOPS!!! Please enter a numerical value");
        } else if (Integer.parseInt(content) > CommandAgent.listSize()) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index");
        } else if (Integer.parseInt(content) <= 0) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index");
        } else {
            this.content = content;
        }
    }

    @Override
    public String sendRequest() {
        return DONE_REQUEST;
    }
}
