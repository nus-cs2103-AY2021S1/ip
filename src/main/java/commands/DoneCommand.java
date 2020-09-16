package commands;

import duke.data.task.TaskList;
import duke.storage.Storage;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task in the task list as completed.\n"
            + "Example: " + COMMAND_WORD + " 1\n";
    private final int index;
    public DoneCommand(int index) {
        this.index = index; // check for index > 0 and < tasks.size
    }
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        tasks.getTask(index).markAsDone();
        // rewrite tasks into storage
        return new CommandResult("Task is marked as done");
    }
}
