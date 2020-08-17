package duke.Command;

import duke.Command.Command;
import duke.Message;

public class ExitCommand extends Command {

    public String execute() {
        return Message.MESSAGE_EXIT;
    }
}
