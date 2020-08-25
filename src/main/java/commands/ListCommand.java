package commands;

import data.task.Task;

import java.util.List;

/**
 * Lists all the tasks in the task list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks in the task list as a list with index numbers.\n"
            + "\tExample: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<Task> allTasks = super.taskList.getList();
        return new CommandResult(Command.getMessageForTaskListSummary(allTasks), allTasks);
    }

}
