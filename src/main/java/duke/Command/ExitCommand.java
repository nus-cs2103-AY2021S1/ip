package duke.Command;

import duke.Ui.Message;

public class ExitCommand extends Command {

    public String execute() {
        return Message.MESSAGE_EXIT;
    }
}