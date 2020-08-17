package duke.Command;

import duke.Command.Command;
import duke.Message;

public class RandomCommand extends Command {

    public String execute() {
        return Message.MESSAGE_RANDOM;
    }
}
