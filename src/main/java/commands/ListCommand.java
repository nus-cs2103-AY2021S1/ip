package commands;

import data.task.Task;

import java.util.List;

// List all the data.tasks in the data.task list to the user.
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the data.tasks in the task list as a list with index numbers.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<Task> allTasks = taskList.getList();
        return new CommandResult(Command.getMessageForTaskListSummary(taskList), allTasks);
    }
}
