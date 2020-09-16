package duke.exception;

import duke.command.Command;
import duke.command.Response;

/**
 * Consists all exceptions related to Duke input.
 */
public class DukeException extends Exception implements Command {
    /**
     * Constructs a <code>DukeException</code> with a message.
     *
     * @param msg a <code>Exceptions</code> field containing a message.
     */
    public DukeException(Exceptions msg) {
        super(msg.toString());
    }

    @Override
    public Response process() {
        return new Response(this.getMessage());
    }
}
