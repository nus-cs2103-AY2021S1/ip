package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * represents a command to display the entire list of tasks
 */
public class ListCommand extends Command {

    /**
     * class constructor
     */
    public ListCommand() {
        super("list");
        this.isExit = false;
    }

    /**
     * returns a string representation of the full list of tasks
     * @param tasks the list of tasks
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return a string representation of the full list of tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        return getTaskListMessage(taskList);
    }

    private String getTaskListMessage(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        if (tasks.size() == 0) {
            sb.append("there are no tasks in your list");
        } else {
            sb.append("here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i+1).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
