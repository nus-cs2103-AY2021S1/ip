package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private int index;

    public DeleteCommand(int index) {
        this.index = index; // check index > 0 and < tasks.size
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.deleteTask(index);
        // rewrite storage
        return new CommandResult("Task has been deleted");
    }
}
