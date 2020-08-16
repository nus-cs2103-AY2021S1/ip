package duke.Command;

import duke.Message;

public class AddCommand extends Command {

    @Override
    public String execute(String string) {
        listArray.add(string);
        return Message.ADDED + string;
    }
}
