package duke.tasklist;

import java.util.ArrayList;

import duke.tasks.Task;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<Task>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get the task with at the specific index in the array
     * @return a Task
     */
    public Task getTask(int index) {
        return getTasks().get(index);
    }

    /**
     * Add a Task to the TaskList
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Delete the task with at the specific index in the array
     * @param index
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Mark the task with at the specific index in the array as complete
     * @param index
     */
    public void completeTask(int index) {
        Task newTask = tasks.get(index).markAsDone();
        tasks.set(index, newTask);
    }

}
