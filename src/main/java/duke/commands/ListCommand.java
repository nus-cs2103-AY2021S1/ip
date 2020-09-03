package duke.commands;

import static duke.utils.Messages.MESSAGE_LIST;

import java.util.List;

import duke.tasklist.TaskList;
import duke.tasks.Task;

/**
 * Represents the command that lists out all tasks when executed.
 */
public class ListCommand extends Command {

    /**
     * Returns a CommandResult containing all tasks to the user.
     *
     * @param taskList The taskList involved.
     * @return The result of the command.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        String response = tasksToString(taskList.getTasks(), MESSAGE_LIST);
        return new CommandResult(response, false);
    }

    /**
     * Converts an ArrayList of tasks to a string which starts with the initialString
     * and the tasks are numbered starting from 1.
     *
     * @param tasks The ArrayList of tasks to be converted.
     * @param initialString The initial String that should be at the start of the result.
     * @return The String starting with the initialString followed by the numbered list of tasks.
     */
    public static String tasksToString(List<Task> tasks, String initialString) {
        StringBuilder str = new StringBuilder(initialString);
        for (int i = 0; i < tasks.size(); i++) {
            str.append("\t ").append(i + 1).append(".")
                    .append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }
}
