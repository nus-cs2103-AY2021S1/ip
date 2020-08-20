package duke.Command;

import duke.Duke;
import duke.Message;
import duke.Task;

public class CompleteCommand extends Command {

    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    public String execute() {
        Task task = Duke.listArray.get(index - 1);
        task.markAsDone();
        return Message.DONE + task.toString();
    }
}
