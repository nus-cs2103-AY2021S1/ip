package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String fullCommand) {
        super(fullCommand);
        this.isExit = false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tasks = taskList.getTaskList();
        return getTaskListMessage(tasks);
    }

    public String getTaskListMessage(ArrayList<Task> tasks) {
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
