package duke;

import java.util.ArrayList;

/**
 * Supports finding the task with given keyword.
 */
public class FindCommand extends Command {
    String input;

    FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ArrayList<Task> tasksArrayList = tasks.getTasksList();
        TaskList tasksFound = new TaskList();
        for (Task task : tasksArrayList) {
            if (task.getDescription().contains(input)) {
                tasksFound.addTask(task);
            }
        }
        ui.showTasksFound(tasksFound);
    }
}
