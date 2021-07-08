package src.main.java.duke.commands;

import static src.main.java.duke.commons.Messages.INDENT;

import java.util.List;

import src.main.java.duke.data.task.Task;


/**
 * Represents an list command. Upon execution, list the task list to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all tasks in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<Task> allTasks = duke.getTaskList().immutableListView();

        String result = "Display a list of tasks: \n";

        if (allTasks.size() == 0) {
            result = "There's nothing in the list :(";
        }

        for (int i = 0; i < allTasks.size(); i++) {
            result += INDENT + (i + 1) + "." + allTasks.get(i) + "\n";
        }

        return new CommandResult(result.trim());
    }
}
