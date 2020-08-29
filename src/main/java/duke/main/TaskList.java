package duke.main;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The class that is responsible of taking care of the user's list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    public int numberOfTasks() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
