package duke.Command;

import duke.Message;
import duke.Task;

public class AddCommand extends Command {
    private String desc;

    AddCommand(String description) {
        this.desc = description;
    }

    @Override
    public String execute() {
        listArray.add(new Task(desc));
        return Message.ADDED + desc;
    }
}
