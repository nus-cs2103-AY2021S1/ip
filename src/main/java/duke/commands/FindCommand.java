package src.main.java.duke.commands;

import java.util.List;

import src.main.java.duke.commons.Messages;
import src.main.java.duke.data.task.Task;

/**
 * Represents a find command. Upon execution, list the tasks that contain the word to user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private static final String INDENT = "   ";

    // Message to add
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find the tasks that contains the words. \n"
            + "Parameters: find WORD\n" + "Example: " + COMMAND_WORD + " workout";

    public FindCommand(String targetVisibleString) {
        super(targetVisibleString);
    }

    @Override
    public CommandResult execute() {
        try {
            final String target = getTargetString();

            List<Task> allTasks = duke.getTaskList().filteredView(target);

            String result = "Display a list of tasks: \n";

            if (allTasks.size() == 0) {
                result = "There's nothing that contains in the list :(";
            }

            for (int i = 0; i < allTasks.size(); i++) {
                result += INDENT + (i + 1) + "." + allTasks.get(i) + "\n";
            }

            return new CommandResult(result.trim());
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        } catch (Exception e) {
            return new CommandResult(Messages.MESSAGE_TASK_NOT_IN_TASKLIST);
        }
    }

}
