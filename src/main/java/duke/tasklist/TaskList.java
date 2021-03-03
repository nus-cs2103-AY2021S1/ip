package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Contains the duke.task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public void delete(int taskId) {
        this.taskList.remove(taskId);
    }

    public Task get(int taskId) {
        return this.taskList.get(taskId);
    }

    public int size() {
        return this.taskList.size();
    }
}
