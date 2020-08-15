package duke.command;

import duke.CommandAgent;
import duke.DukeException;

/**
 * A subclass of Command which sends request to mark a duke.task in list as done.
 */
public class DoneCommand extends Command {
    /**
     * Creates a DoneCommand.
     *
     * @param content the duke.task information supplied by the user.
     * @throws DukeException if the content has no input or inproper input.
     */
    public DoneCommand(String content) throws DukeException {
        if (content.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please enter a value");
        } else if (!(Character.isDigit(content.charAt(0)))) {
            throw new DukeException("☹ OOPS!!! Please enter a numerical vaue");
        } else if (Integer.parseInt(content) >= CommandAgent.listSize()) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index");
        } else {
            this.content = content;
        }
    }

    @Override
    public String sendRequest() {
        return "update";
    }
}
