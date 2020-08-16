package duke.Command;

import duke.Message;

public class RandomCommand extends Command {

    @Override
    public String execute(String string) {
        return Message.MESSAGE_RANDOM;
    }
}
