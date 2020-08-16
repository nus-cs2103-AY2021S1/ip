package duke.Command;

import duke.Message;

public class ExitCommand extends Command {

    @Override
    public String execute() {
        return Message.MESSAGE_EXIT;
    }
}
