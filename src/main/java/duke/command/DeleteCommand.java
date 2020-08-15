package duke.command;

import duke.CommandAgent;
import duke.DukeException;

/**
 * A subclass of Command which sends request to mark a task in list as done.
 */
public class DeleteCommand extends Command {
    
    /**
     * Creates a DeleteCommand.
     *
     * @param content the ask information supplied by the user.
     * @throws DukeException if the content has no input or improper input.
     */
    public DeleteCommand(String content) throws DukeException {
        if (content.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please enter a value");
        } else if (!(Character.isDigit(content.charAt(0)))) {
            throw new DukeException("☹ OOPS!!! Please enter a numerical value");
        } else if (Integer.parseInt(content) > CommandAgent.listSize() | Integer.parseInt(content) <= 0) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index");
        } else {
            this.content = content;
        }
    }

    @Override
    public String sendRequest() {
        return "delete";
    }
}