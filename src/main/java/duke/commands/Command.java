package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public abstract class Command {
    public abstract CommandResult execute(TaskList taskList, Ui ui, Storage storage);

    public abstract boolean isExit();

    public String taskListToString(ArrayList<Task> todoList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < todoList.size(); i++) {
            int index = i + 1;
            str.append(index).append(". ").append(todoList.get(i).toString());
            if (i != todoList.size() - 1) {
                str.append("\n");
            }
        }
        return str.toString();
    }

    public String addTaskToString(Task targetTask, int size) {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n");
        str.append(targetTask.toString()).append("\n");
        str.append("Now you have ").append(size).append(" tasks in the list.");
        return str.toString();
    }

    public String deleteTaskToString(Task targetTask, int size) {
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task:\n");
        str.append(targetTask.toString()).append("\n");
        str.append("Now you have ").append(size).append(" tasks in the list.");
        return str.toString();
    }
}
