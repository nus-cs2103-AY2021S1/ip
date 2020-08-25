package duke.tool;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the task list stored in Duke system.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList()  {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks)  {
        taskList = tasks;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void add(Task newTask) {
        this.taskList.add(newTask);
    }

    public Task delete(int index) {
        return this.taskList.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public Task markDone(int index) {
        Task targetTask = taskList.get(index);
        targetTask.markAsDone();
        return targetTask;
    }

    public void clear() {
        taskList = new ArrayList<>();
    }
}
