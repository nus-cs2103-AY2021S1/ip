package duke.Command;

import duke.Message;
import duke.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
        Task task = listArray.get(index - 1);
        listArray.remove(index - 1);
        return Message.DELETE + task.toString() + "\n" +
                "Now you have " + listArray.size() +
                (listArray.size() == 1 ? " task " : " tasks ")
                + "in the list";
    }
}
