package duke.Command;

import duke.Duke;
import duke.Message;
import duke.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = Duke.listArray.get(index - 1);
        Duke.listArray.remove(index - 1);
        return Message.DELETE + task.toString() + "\n" +
                "Now you have " + Duke.listArray.size() +
                (Duke.listArray.size() == 1 ? " task " : " tasks ")
                + "in the list";
    }
}
