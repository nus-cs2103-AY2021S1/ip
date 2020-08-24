package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/** Represents the command that lists out all tasks when executed. */
public class ListCommand extends Command {

    /** Displays all tasks to the user.
     *
     * @param taskList The taskList involved.
     * @param ui The ui involved to show messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String response = "\t Here are the tasks in your list:\n";
        ui.show(tasksToString(taskList.getTasks(), response));
    }

    /** Converts an ArrayList of tasks to a string which starts with the initialString
     * and the tasks are numbered starting from 1.
     *
     * @param tasks The ArrayList of tasks to be converted.
     * @param initialString The initial String that should be at the start of the result.
     * @return The String starting with the initialString followed by the numbered list of tasks.
     */
    public static String tasksToString(ArrayList<Task> tasks, String initialString) {
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
