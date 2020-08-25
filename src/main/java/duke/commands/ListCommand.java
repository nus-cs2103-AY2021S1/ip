package duke.commands;

import duke.data.task.Task;

import java.util.List;

public class ListCommand extends Command {

    private static String INDENT = "   ";
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the address book as a list with index numbers.\n" + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<Task> allTasks = duke.getTaskList().immutableListView();
        String result = "Display a list of tasks: \n";
        for (int i = 0; i < allTasks.size(); i++) {
            result += INDENT + (i + 1) + "." + allTasks.get(i) + "\n";
        }
        return new CommandResult(result.trim());
    }
}
