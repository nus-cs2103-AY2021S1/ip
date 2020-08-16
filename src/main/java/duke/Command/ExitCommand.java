package duke.Command;

import duke.Message;

public class ExitCommand extends Command {

    @Override
    public String execute(String str) {
        return Message.MESSAGE_EXIT;
    }
}
