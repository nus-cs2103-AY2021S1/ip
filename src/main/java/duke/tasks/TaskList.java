package duke.tasks;

import java.util.ArrayList;

/**
 * TaskList class which contains an ArrayList of Tasks
 * and methods to manipulate the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(TaskList list) {
        this.taskList = list.getTaskList();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public int taskListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
